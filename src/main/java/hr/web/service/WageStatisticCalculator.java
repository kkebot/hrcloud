package hr.web.service;

import hr.data.wage.WageRecordRepository;
import hr.data.wage.WageStatisticRepository;
import hr.domain.organization.Department;
import hr.domain.wage.WageStatistic;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class WageStatisticCalculator {
    private WageRecordRepository wageRecordRepo;
    private WageStatisticRepository wageStatisticRepo;

    public WageStatisticCalculator(WageRecordRepository wageRecordRepo, WageStatisticRepository wageStatisticRepo) {
        this.wageRecordRepo = wageRecordRepo;
        this.wageStatisticRepo = wageStatisticRepo;
    }

    private WageStatistic sumUpTo(Department department, Date period) {
        WageStatistic existed = wageStatisticRepo.findByDepartmentAndPeriod(department, period);
        WageStatistic result;
        if (existed == null) {
            result = new WageStatistic(department, period);
        } else if (existed.isBlur()) {
            result = existed;
            result.clear(); // set blur to be false, countEmployee to zero and reset all wage terms.
        } else {
            return existed;
        }

        wageRecordRepo.findByPeriodAndCurrentPosition_Administration(period, department)
                .forEach(result::compoundAdd);

        Collection<WageStatistic> subWages = department.getSubordinates()
                        .stream()
                        .map(subordinate -> sumUpTo(subordinate, period))
                        .peek(result::compoundAdd)
                        .collect(Collectors.toList());

        wageStatisticRepo.saveAll(subWages);
        return result;
    }

    public WageStatistic calculate(Department department, Date period) {
        return wageStatisticRepo.save(sumUpTo(department, period));
    }
}
