package hr.data.wage;

import hr.domain.organization.Department;
import hr.domain.wage.WageRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface WageRecordRepository extends JpaRepository<WageRecord, Long> {

    @Query("SELECT MIN(w.earnings), MAX(w.earnings) FROM WageRecord w " +
            "INNER JOIN w.currentPosition p " +
            "INNER JOIN p.administration d " +
            "WHERE w.period=?2 " +
            "AND (d.previous LIKE CONCAT('%/', ?1, '/%') OR d.id=?1) ")
    List<Object[]> queryMinMaxUnder(Long departmentId, Date period);

    Page<WageRecord> findByImportTimestampBetween(Date before,Date after, Pageable pageable);

    @Query("SELECT COUNT(w) FROM WageRecord w " +
            "INNER JOIN w.currentPosition p " +
            "INNER JOIN p.administration d " +
            "WHERE w.period=?2 " +
            "AND w.earnings BETWEEN ?3 AND ?4 " +
            "AND (d.previous LIKE CONCAT('%/', ?1, '/%') OR d.id=?1) ")
    int queryCountByEarningsRangeUnder(Long departmentId, Date period, double start, double end);

    @Query("SELECT w FROM WageRecord w " +
            "INNER JOIN w.currentPosition p " +
            "INNER JOIN p.administration d " +
            "WHERE w.period BETWEEN ?1 AND ?2 " +
            "AND (d.previous LIKE CONCAT('%/', ?3, '/%') OR d.id=?3) " +
            "ORDER BY w.earnings DESC ")
    Page<WageRecord> queryByPeriodAndAdministrationOrdered(Date from, Date to, Long departmentId, Pageable pageable);

    Page<WageRecord> findByPeriodBetweenAndCurrentPosition_IdOrderByPeriodAsc(Date from, Date to, long positionId, Pageable pageable);
    Page<WageRecord> findByPeriodBetweenAndEmployee_IdOrderByPeriodAsc(Date from, Date to, long employeeId, Pageable pageable);

    Collection<WageRecord> findByPeriodAndCurrentPosition_Administration(Date month, Department department);

    @Query("SELECT p.administration FROM WageRecord wr INNER JOIN wr.currentPosition p WHERE wr.id=?1")
    Department queryDepartmentOf(long wageId);

    /**
     * The caller of this method must be annotated with {@link org.springframework.transaction.annotation.Transactional}.
     *
     * Otherwise, an exception will be thrown -> {@link javax.persistence.TransactionRequiredException}: Executing an update/delete query
     */
    @Modifying
    @Query(value = "UPDATE wage_record w INNER JOIN employee e " +
            "ON w.employee_id=e.id SET " +
            "w.current_position_id=e.main_position_id, " +
            "w.current_scale_id=e.scale_id " +
            "WHERE w.current_position_id IS NULL " +
            "AND w.current_scale_id IS NULL ", nativeQuery = true)
    int fixEmployeeColumns();

}
