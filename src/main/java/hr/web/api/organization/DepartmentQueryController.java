package hr.web.api.organization;

import hr.data.employee.EmployeeRepository;
import hr.data.organization.DepartmentRepository;
import hr.data.organization.PositionRepository;
import hr.data.wage.WageStatisticRepository;
import hr.domain.MetaEntity;
import hr.domain.organization.Department;
import hr.domain.organization.DepartmentDetails;
import hr.domain.organization.Position;
import hr.domain.wage.WageStatistic;
import hr.web.api.base.SingleEntityQuery;
import hr.web.api.exception.BadRequest;
import hr.web.service.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/department")
public class DepartmentQueryController extends SingleEntityQuery<Department, Long, DepartmentRepository> {

    private PositionRepository positionRepo;
    private EmployeeRepository employeeRepo;
    private WageStatisticRepository wageStatisticRepo;

    public DepartmentQueryController(DepartmentRepository departmentRepo, PositionRepository positionRepo, EmployeeRepository employeeRepo, WageStatisticRepository wageStatisticRepo) {
        super(departmentRepo);

        this.positionRepo = positionRepo;
        this.employeeRepo = employeeRepo;
        this.wageStatisticRepo = wageStatisticRepo;
    }

    @GetMapping
    public Department getHeadOffice() {
        return getRepo().findTopByOrderByIdAsc();
    }

    @GetMapping("/{departmentId}/details")
    public DepartmentDetails getDepartmentDetails(@PathVariable long departmentId) {
        Department department = getEx(departmentId);
        DepartmentDetails details = new DepartmentDetails();

        details.setCreatedAt(department.getCreatedAt());
        details.setDescription(department.getDescription());
        details.setId(department.getId());
        details.setName(department.getName());
        details.setResource(department.getResource());
        details.setPositions(department.getPositions());
        details.setStatus(department.getStatus());

        Department admin = department.getAdministration();
        if (admin != null)
            details.setAdministration(new MetaEntity(admin.getId(), admin.getName()));
        details.setCountEmployees(employeeRepo.countByAdministrationOf(departmentId));
        details.setCountPositions(positionRepo.countByAdministrationOf(departmentId));

        details.setSubordinates(
                department.getSubordinates()
                        .stream()
                        .map(Department::meta)
                        .collect(Collectors.toList()));

        Collection<Department> ascendants = getRepo().findAllById(department.getHierarchyIds());
        details.setFullname(ascendants.stream().map(Department::getName).collect(Collectors.joining("/")));

        return details;
    }

    // TODO Filter disabled departments ?
    @GetMapping("/{departmentId}/subordinates")
    public Collection<Department> getSubordinates(@PathVariable long departmentId) {
        return getRepo().findByAdministration_IdAndStatusIsTrue(departmentId);
    }

    // TODO Filter disabled positions?
    @GetMapping("/{departmentId}/positions")
    public Collection<Position> getPositions(@PathVariable long departmentId) {
        return positionRepo.findByAdministration_IdAndStatusIsTrue(departmentId);
    }


    @GetMapping("/{departmentId}/statistics")
    public WageStatistic getWageStatistics(@PathVariable long departmentId, @RequestParam(required = false) Date period) {
        return wageStatisticRepo.findByDepartment_IdAndPeriod(departmentId, DateUtils.firstDayOfMonth(period));
    }

    @GetMapping("/statistics/query")
    public Page<WageStatistic> queryWageStatistics(@RequestParam long departmentId, @RequestParam(required = false) Date from, @RequestParam(required = false) Date to, Pageable pageable) {
        return wageStatisticRepo.findByDepartment_IdAndPeriodBetweenOrderByPeriod(departmentId, from, to, pageable);
    }

    @GetMapping("/admin")
    public Department getAdministration(@RequestParam String entityType, @RequestParam Long entityId) {
        switch (entityType) {
            case "department":
                return getRepo().queryAdministrationOf(entityId);
            case "position":
                return positionRepo.queryAdministrationOf(entityId);
            case "employee":
                return employeeRepo.queryAdministrationOf(entityId);
            default:
                throw new BadRequest();
        }
    }

    @GetMapping("/search")
    public Page<Department> searchDepartment(@RequestParam String keyword, @RequestParam String value, Pageable pageable) {
        switch (keyword) {
            case "id": {
                Department department = get(Long.valueOf(value));
                if (department == null) {
                    return new PageImpl<>(Collections.emptyList());
                } else
                    return new PageImpl<>(Collections.singletonList(department));
            } case "name":
                return getRepo().findByNameContaining(value, pageable);
            default:
                throw new BadRequest();
        }
    }

    @GetMapping("/{departmentId}")
    public Department getDepartment(@PathVariable long departmentId) {
        return getEx(departmentId);
    }


}
