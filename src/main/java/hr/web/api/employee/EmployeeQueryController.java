package hr.web.api.employee;

import hr.data.employee.AttachmentRepository;
import hr.data.employee.EmployeeRepository;
import hr.data.wage.WageRecordRepository;
import hr.domain.employee.Attachment;
import hr.domain.employee.enums.AttachmentType;
import hr.domain.employee.Employee;
import hr.web.api.base.SingleEntityQuery;
import hr.web.api.exception.BadRequest;
import hr.web.api.exception.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/employee")
public class EmployeeQueryController extends SingleEntityQuery<Employee,Long,EmployeeRepository> {

    private EntityManager entityManager;
    private AttachmentRepository attachmentRepo;

    public EmployeeQueryController(EmployeeRepository employeeRepo, EntityManager entityManager, AttachmentRepository attachmentRepo, WageRecordRepository wageRecordRepo) {
        super(employeeRepo);
        this.entityManager = entityManager;
        this.attachmentRepo = attachmentRepo;
    }

    @GetMapping("/query")
    public Page<Employee> queryEmployees(@RequestParam String entityType, @RequestParam Long entityId, Pageable pageable) {
        switch (entityType) {
            case "employee":
                return getRepo().findAllById(entityId, pageable);
            case "department":
                return getRepo().queryByAdministration(entityId, pageable);
            case "position":
                return getRepo().findByMainPosition_Id(entityId, pageable);
            default:
                throw new BadRequest();
        }
    }

    @GetMapping("/search")
    public Page<Employee> searchEmployees(@RequestParam String keyword, @RequestParam String value, Pageable pageable) {
        switch (keyword) {
            case "id":
            case "employeeId":
                return getRepo().findAllById(Long.valueOf(value), pageable);
            case "name":
                return getRepo().findByNameContaining(value, pageable);
            case "credential":
                return getRepo().findByCredentialContaining(value, pageable);
            case "departmentId":
            case "positionId":
                return queryEmployees(keyword.substring(0, keyword.length() - 2), Long.valueOf(value), pageable);
            default:
                throw new BadRequest();
        }
    }

    @GetMapping("/owner")
    public long queryOwner(@RequestParam String entityType, @RequestParam long entityId) {
        String sql = String.format("SELECT t.employee_id FROM %s t WHERE t.id=%d", entityType, entityId);
        Query query = entityManager.createNativeQuery(sql);
        try {
            return ((BigInteger) query.getSingleResult()).longValue();
        } catch (NoResultException e) {
            throw new NotFound(entityId);
        } catch (PersistenceException e) {
            log.error("Exception happened when querying owner of an entity - {}", e.getMessage());
            throw new BadRequest();
        }
    }

    @GetMapping("/{employeeId}")
    public Employee get(@PathVariable String employeeId) {
        return getEx(EmployeeGuard.validateSelfOnly(employeeId));
    }

    @GetMapping("/{employeeId}/attachments")
    public Collection<Attachment> getAttachments(@PathVariable String employeeId, @RequestParam(required = false) AttachmentType type) {
        return attachmentRepo.findByEmployee_IdAndType(EmployeeGuard.validateSelfOnly(employeeId), type);
    }
}
