package hr.data.employee;

import hr.domain.employee.ContractRenew;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface ContractRenewRepository extends JpaRepository<ContractRenew, Long> {
    Page<ContractRenew> findByContract_IdOrderByOriginalTermDesc(Long contract_id, Pageable pageable);
}
