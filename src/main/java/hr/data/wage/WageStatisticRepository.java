package hr.data.wage;

import hr.domain.organization.Department;
import hr.domain.wage.WageStatistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.parameters.P;

import java.util.Collection;
import java.util.Date;

public interface WageStatisticRepository extends CrudRepository<WageStatistic, Long> {

    WageStatistic findByDepartmentAndPeriod(Department department, Date period);

    WageStatistic findByDepartment_IdAndPeriod(long departmentId, Date period);

    @Modifying
    @Query("UPDATE WageStatistic ws SET ws.blur=true WHERE ws.department.id IN ?1")
    int markWageStatisticAsBlur(Collection<Long> ids);

    Page<WageStatistic> findByDepartment_IdAndPeriodBetweenOrderByPeriod(long departmentId, Date from, Date to, Pageable pageable);
}
