package hr.data.employee;

import hr.domain.employee.Adjustment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdjustmentRepository extends CrudRepository<Adjustment, Long> {

    Page<Adjustment> findByEmployee_IdOrderByEffectiveOnDesc(Long employeeId, Pageable pageable);

    @Query("SELECT a FROM Adjustment a " +
            "WHERE a.to.id=?1 " +
            "ORDER BY a.effectiveOn DESC ")
    Page<Adjustment> queryByPosition(Long positionId, Pageable pageable);
}
