package hr.web.api.wage;

import hr.data.wage.WageStatisticRepository;
import hr.domain.organization.Department;
import hr.domain.wage.WageStatistic;
import hr.web.api.organization.DepartmentQueryController;
import hr.web.service.WageStatisticCalculator;
import hr.web.service.utils.DateUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/api/wage/statistic")
public class WageStatisticController {
    private DepartmentQueryController departmentQuery;
    private WageStatisticCalculator wageStatisticCalculator;

    public WageStatisticController(DepartmentQueryController departmentQuery, WageStatisticCalculator wageStatisticCalculator, WageStatisticRepository wageStatisticRepo) {
        this.departmentQuery = departmentQuery;
        this.wageStatisticCalculator = wageStatisticCalculator;
    }

    @PostMapping("/update")
    public WageStatistic calculateDepartment(@RequestParam long departmentId, @RequestParam Date period) {
        Department department = departmentQuery.getEx(departmentId);
        Date date = DateUtils.firstDayOfMonth(period);
        return wageStatisticCalculator.calculate(department, date);
    }

}
