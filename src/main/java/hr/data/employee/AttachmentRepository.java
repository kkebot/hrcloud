package hr.data.employee;

import hr.domain.employee.Attachment;
import hr.domain.employee.enums.AttachmentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, Long> {

    Collection<Attachment> findByEmployee_Id(Long employeeId);

    @Query("SELECT a FROM Attachment a WHERE a.employee.id=?1 AND (?2 IS NULL OR a.type=?2)")
    Collection<Attachment> findByEmployee_IdAndType(Long employeeId, AttachmentType type);
}
