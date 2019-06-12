package hr.data.wage;

import hr.domain.wage.PayScale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayScaleRepository extends JpaRepository<PayScale, Long> {
}
