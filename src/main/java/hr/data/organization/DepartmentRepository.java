package hr.data.organization;

import hr.domain.organization.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findTopByOrderByIdAsc();

    Collection<Department> findByStatusIsTrue();

    Page<Department> findByNameContaining(String name, Pageable pageable);

    @Query("SELECT d.administration FROM Department d WHERE d.id=?1")
//    @Query("SELECT d.administration FROM Department d INNER JOIN d.administration a WHERE d.id=?1")
    Department queryAdministrationOf(Long id);

    Collection<Department> findByAdministration_IdAndStatusIsTrue(long id);

    boolean existsByAdministration_IdAndStatusIsTrue(long departmentId);

    boolean existsByAdministrationAndNameAndStatusIsTrue(Department admin, String name);
}
