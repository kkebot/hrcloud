package hr.data.employee;

import hr.domain.employee.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    Page<Contract> findByEmployee_IdOrderByStatusDescEffectiveUntilAsc(long employeeId, Pageable pageable);

    Optional<Contract> findByIdAndStatusIsTrue(long contractId);

    boolean existsByEmployee_IdAndStatusIsTrue(Long employeeId);
}
