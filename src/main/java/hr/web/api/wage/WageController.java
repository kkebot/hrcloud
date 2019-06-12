package hr.web.api.wage;

import hr.data.employee.EmployeeRepository;
import hr.data.wage.WageRecordRepository;
import hr.data.wage.WageStatisticRepository;
import hr.domain.employee.Employee;
import hr.domain.organization.Department;
import hr.domain.organization.Position;
import hr.domain.wage.*;
import hr.web.api.base.SingleEntityQuery;
import hr.web.api.employee.EmployeeGuard;
import hr.web.api.exception.BadRequest;
import hr.web.api.exception.ServerFault;
import hr.web.converter.StringToDateConverter;
import hr.web.service.utils.DateUtils;
import hr.web.service.utils.ExcelImportUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Consumer;

@Slf4j
@RestController
@RequestMapping("/api/wage")
public class WageController extends SingleEntityQuery<WageRecord, Long, WageRecordRepository> {

    private WageStatisticRepository wageStatisticRepo;
    private EmployeeRepository employeeRepo;
    private StringToDateConverter stringToDateConverter;

    public WageController(WageRecordRepository wageRecordRepo, WageStatisticRepository wageStatisticRepo, EmployeeRepository employeeRepo, StringToDateConverter stringToDateConverter) {
        super(wageRecordRepo);
        this.wageStatisticRepo = wageStatisticRepo;
        this.employeeRepo = employeeRepo;
        this.stringToDateConverter = stringToDateConverter;
    }

    private static WageRecord assignBasic(WageRecord wageRecord, Row row, int offset) {
        wageRecord.setSalary(row.getCell(offset).getNumericCellValue());
        wageRecord.setBonus(row.getCell(offset + 1).getNumericCellValue());
        wageRecord.setAllowance(row.getCell(offset + 2).getNumericCellValue());
        wageRecord.setOvertimePay(row.getCell(offset + 3).getNumericCellValue());
        wageRecord.setBackPay(row.getCell(offset + 4).getNumericCellValue());
        wageRecord.setPayRaise(row.getCell(offset + 5).getNumericCellValue());
        wageRecord.setPayCut(row.getCell(offset + 6).getNumericCellValue());
        wageRecord.sumUp();
        return wageRecord;
    }

    private static void extractWagesRecord(MultipartFile file, Consumer<Row> iterateRow) {
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ServerFault();
        }

        String filename = file.getOriginalFilename();

        if (filename == null) {
            throw new BadRequest("缺少文件名");
        }

        Workbook workbook;
        try {
            if (ExcelImportUtils.isExcel2007(filename)) {
                workbook = new XSSFWorkbook(inputStream);
            } else {
                workbook = new HSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ServerFault();
        }
        try {
            Sheet sheetAt = workbook.getSheetAt(0);
            sheetAt.removeRow(sheetAt.getRow(0)); // Remove the first row

            sheetAt.forEach(iterateRow);
        } catch (Exception e) {
            log.warn("Exception in processing excel:" + e.toString() + ";" + e.getMessage());
            throw new BadRequest("Excel内容错误");
        }

        try {
            workbook.close();
            inputStream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Upload raw data along with additional fields including position id and pay scale id
     */
    @Transactional
    @PostMapping("/upload/raw")
    public Date uploadRawWageRecords(@RequestParam(required = false) MultipartFile file) {
        if (file == null) {
            // If filename is set to be required and not presented, spring returns 500 instead of 400
            throw new BadRequest("缺少文件");
        }

        List<WageRecord> wageRecordList = new ArrayList<>();
        Date importTimestamp = new Date();

        extractWagesRecord(file, row -> {
            long employeeId = (long) row.getCell(0).getNumericCellValue();
            Employee employee = new Employee();
            employee.setId(employeeId);

            long positionId = (long) row.getCell(1).getNumericCellValue();
            Position position = new Position();
            position.setId(positionId);

            long scaleId = (long) row.getCell(2).getNumericCellValue();
            PayScale scale = new PayScale();
            scale.setId(scaleId);

            Date month = DateUtils.firstDayOfMonth(stringToDateConverter.convert(row.getCell(3).getStringCellValue()));

            WageRecord wageRecord = new WageRecord(month, employee, position, scale, importTimestamp);
            wageRecordList.add(assignBasic(wageRecord, row, 4));
        });

        try {

            getRepo().saveAll(wageRecordList);
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new BadRequest("部分记录已存在");
        }
        return importTimestamp;
    }

    @Transactional
    @PostMapping("/upload/current")
    public Date uploadCurrentWageRecords(
            @RequestParam Long departmentId,
            @RequestParam(required = false) MultipartFile file,
            @RequestParam(required = false) Date period) {
        if (file == null) {
            // If filename is set to be required and not presented, spring returns 500 instead of 400
            throw new BadRequest("缺少文件");
        }

        List<WageRecord> wageRecordList = new ArrayList<>();
        List<Long> employeeIds = new ArrayList<>();
        Date month = DateUtils.firstDayOfMonth(Optional.ofNullable(period).orElse(new Date()));
        Date importTimestamp = new Date();

        extractWagesRecord(file, row -> {
            long employeeId = (long) row.getCell(0).getNumericCellValue();
            Employee employee = new Employee();
            employee.setId(employeeId);

            WageRecord wageRecord = new WageRecord();
            wageRecord.setEmployee(employee);
            wageRecord.setPeriod(month);
            wageRecord.setImportTimestamp(importTimestamp);

            assignBasic(wageRecord, row, 1);

            wageRecordList.add(wageRecord);
            employeeIds.add(employeeId);
        });

        Set<Long> filters = employeeRepo.queryIdsByAdministration(departmentId, employeeIds);
        wageRecordList.removeIf(wageRecord -> !filters.contains(wageRecord.getEmployee().getId()));

        try {
            getRepo().saveAll(wageRecordList);
            int rows = getRepo().fixEmployeeColumns();
            log.debug("Fix {} rows for current positions, administrations and pay scales.", rows);
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new BadRequest("部分记录已存在");
        }

        return importTimestamp;
    }

    @GetMapping("/upload/result")
    public Page<WageRecord> uploadResult(@RequestParam Date importTimestamp, Pageable pageable) {
        return getRepo().findByImportTimestampBetween(importTimestamp, importTimestamp, pageable);
    }

    @GetMapping
    public Page<WageRecord> getWages(@RequestParam String entityType, @RequestParam Long entityId,
                                     @RequestParam Date from, @RequestParam Date to, Pageable pageable) {
        if (from.after(to))
            throw new BadRequest("错误的时间范围");

        Page<WageRecord> wageRecords;
        switch (entityType) {
            case "department":
                wageRecords = getRepo()
                        .queryByPeriodAndAdministrationOrdered(from, to, entityId, pageable);
                break;
            case "position":
                wageRecords = getRepo()
                        .findByPeriodBetweenAndCurrentPosition_IdOrderByPeriodAsc(from, to, entityId, pageable);
                break;
            case "employee":
                EmployeeGuard.validateSelfOnly(entityId);
                wageRecords = getRepo()
                        .findByPeriodBetweenAndEmployee_IdOrderByPeriodAsc(from, to, entityId, pageable);
                break;
            default:
                throw new BadRequest();
        }

        return wageRecords;
    }

    @Transactional
    @PostMapping("/update")
    public WageRecord updateWageRecord(@RequestBody WageTerms terms, @RequestParam long wageId) {
        WageRecord wageRecord = getEx(wageId);
        wageRecord.assign(terms);
        wageRecord.sumUp();
        wageRecord.setLastModified(new Date());

        Department department = getRepo().queryDepartmentOf(wageId);
        if (department != null) {
            int count = wageStatisticRepo.markWageStatisticAsBlur(department.getHierarchyIds());
            log.debug("{} statistic records affected.", count);
        }

        return getRepo().save(wageRecord);
    }

    @GetMapping("/distribute")
    public EarningsDistribution getEarningsDistribution(@RequestParam Long departmentId, @RequestParam Date period) {
        Date month = DateUtils.firstDayOfMonth(period);
        // List<Double[]> doesn't work
        List<Object[]> minMax = getRepo().queryMinMaxUnder(departmentId, month);

        Double min = (Double) minMax.get(0)[0];
        Double max = (Double) minMax.get(0)[1];
        if (min == null || max == null)
            return null;

        double interval = (max - min) / 10.0;
        int[] distribution = new int[10];

        double start = min;
        for (int i = 0; i < 10; i++) {
            distribution[i] = getRepo().queryCountByEarningsRangeUnder(departmentId, month, start, start + interval);
            start += interval;
        }

        return new EarningsDistribution(min, max, distribution);
    }
}
