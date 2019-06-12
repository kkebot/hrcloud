package hr.data.organization;

import hr.domain.organization.Department;
import hr.domain.organization.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    Collection<Position> findByStatusIsTrue();

    Collection<Position> findByAdministration_IdAndStatusIsTrue(long id);

    Page<Position> findByNameContaining(String name, Pageable pageable);

    boolean existsByAdministration_IdAndStatusIsTrue(long adminId);

    boolean existsByAdministrationAndNameAndStatusIsTrue(Department admin, String name);

    @Query("SELECT p.administration FROM Position p WHERE p.id=?1")
    Department queryAdministrationOf(Long id);

    @Query("SELECT COUNT(p) FROM Position p " +
            "INNER JOIN p.administration d " +
            "WHERE d.previous LIKE CONCAT('%/', ?1, '/%') OR d.id=?1")
    int countByAdministrationOf(Long id);

}
