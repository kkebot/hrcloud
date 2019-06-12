package hr.data.employee;

import hr.domain.employee.Employee;
import hr.domain.organization.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Collection<Employee> findByStatusIsTrue();

    @Query("SELECT e.id FROM Employee e INNER JOIN e.mainPosition p " +
            "INNER JOIN p.administration d " +
            "WHERE (d.previous LIKE CONCAT('%/', ?1, '/%') OR d.id=?1) " +
            "AND e.id IN ?2")
    Set<Long> queryIdsByAdministration(Long id, Collection<Long> ids);
    boolean existsByCredential(String credential);
    boolean existsByCredentialAndIdNot(String credential, Long id);
    boolean existsByMainPosition_IdAndStatusIsTrue(long positionId);

    Page<Employee> findAllById(Long id, Pageable pageable);
    Page<Employee> findByNameContaining(String string, Pageable pageable);
    Page<Employee> findByCredentialContaining(String string, Pageable pageable);
    Page<Employee> findByMainPosition_Id(Long positionId, Pageable pageable);


    @Query("SELECT e FROM Employee e " +
            "INNER JOIN e.mainPosition p " +
            "INNER JOIN p.administration d " +
            "WHERE (d.previous LIKE CONCAT('%/', ?1, '/%') OR d.id=?1)")
    Page<Employee> queryByAdministration(Long id, Pageable pageable);

    @Query("SELECT p.administration FROM Employee e INNER JOIN e.mainPosition p WHERE e.id=?1")
    Department queryAdministrationOf(Long id);

    @Query("SELECT COUNT(e) FROM Employee e " +
            "INNER JOIN e.mainPosition p " +
            "INNER JOIN p.administration d " +
            "WHERE (d.previous LIKE CONCAT('%/', ?1, '/%') OR d.id=?1)")
    int countByAdministrationOf(Long id);

    int countByMainPosition_Id(Long id);


}
