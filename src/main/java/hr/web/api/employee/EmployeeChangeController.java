package hr.web.api.employee;

import hr.data.employee.AttachmentRepository;
import hr.data.employee.EmployeeRepository;
import hr.domain.FileResource;
import hr.domain.employee.Attachment;
import hr.domain.employee.enums.AttachmentType;
import hr.domain.employee.Authority;
import hr.domain.employee.Employee;
import hr.web.api.base.SingleEntityQuery;
import hr.web.api.exception.BadRequest;
import hr.web.api.exception.Conflict;
import hr.web.service.FileSettings;
import hr.web.service.utils.PendingResource;
import hr.web.service.utils.PendingResourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/employee")
public class EmployeeChangeController extends SingleEntityQuery<Employee, Long, EmployeeRepository> {

    private PendingResourceBuilder builder;
    private AttachmentRepository attachmentRepo;

    public EmployeeChangeController(EmployeeRepository employeeRepo, FileSettings fileSettings, AttachmentRepository attachmentRepo, AdjustmentController adjustmentController) {
        super(employeeRepo);
        this.builder = fileSettings.rootResolve("employee");
        this.attachmentRepo = attachmentRepo;
    }

    // TODO files
    @Transactional
    @PostMapping
    public Employee importEmployee(@Validated Employee model,
            @RequestParam(required = false) AttachmentType[] attachmentTypes,
            @RequestParam(required = false) MultipartFile[] files) throws IOException {

        if (getRepo().existsByCredential(model.getCredential()))
            throw new Conflict("证件号码已存在");

        String credential = model.getCredential();
        model.setPassword(credential.substring(credential.length() - 6));
        model.setAuthorities(Collections.singletonList(Authority.USER));

        return persistWithResources(attachmentTypes, files, model);
    }

    private Employee persistWithResources(AttachmentType[] attachmentTypes, MultipartFile[] files, Employee employee) throws IOException {
        if (attachmentTypes == null || files == null)
            return getRepo().save(employee);

        if (attachmentTypes.length != files.length) {
            throw new BadRequest("附件参数错误");
        }

        PendingResource pending = builder.prepareResourceUnder(employee.getCredential());
        Collection<Attachment> attachments = attachmentRepo.findByEmployee_Id(employee.getId());

        for (int i = 0; i < attachmentTypes.length; i++) {
            AttachmentType type = attachmentTypes[i];
            MultipartFile file = files[i];
            if (type == null || file == null)
                throw new BadRequest("附件无效");

            Optional<Attachment> result = attachments.stream()
                    .filter(attachment -> type.equals(attachment.getType()))
                    .findFirst();

            if (result.isPresent()) {
                FileResource resource = result.get().getResource();
                pending.waitResource(Paths.get(resource.getPath()), file);
            } else {
                FileResource resource = pending.predict(type.name(), file);
                attachments.add(new Attachment(employee, type, resource));
            }
        }

        Employee ret = getRepo().save(employee);
        attachmentRepo.saveAll(attachments);
        pending.writeResources();

        return ret;
    }

    @Transactional
    @PostMapping("/update")
    public void updateEmployee(@RequestParam String employeeId, @Validated Employee model,
                               @RequestParam(required = false) AttachmentType[] attachmentTypes,
                               @RequestParam(required = false) MultipartFile[] files) throws IOException {

        Long id = EmployeeGuard.validateSelfOnly(employeeId);
        if (getRepo().existsByCredentialAndIdNot(model.getCredential(), id))
            throw new Conflict("证件号码已存在");

        Employee old = getEx(id);

        old.setEntryTime(model.getEntryTime());

        // Basic
        old.setName(model.getName());
        old.setCredential(model.getCredential());
        old.setGender(model.getGender());
        old.setBirth(model.getBirth());

        // College
        old.setCollege(model.getCollege());
        old.setDegree(model.getDegree());
        old.setGraduateTime(model.getGraduateTime());
        old.setEducation(model.getEducation());
        old.setMajor(model.getMajor());

        // Contact
        old.setPhoneNumbers(model.getPhoneNumbers());
        old.setEmail(model.getEmail());
        old.setAddress(model.getAddress());

        // Misc
        old.setMarriage(model.getMarriage());
        old.setPoliticalStatus(model.getPoliticalStatus());

        persistWithResources(attachmentTypes, files, old);
    }

    @PostMapping("/restore")
    public void restore(@RequestParam long employeeId) {
        Employee employee = getEx(employeeId);
        employee.setStatus(true);

        getRepo().save(employee);
    }


    @PostMapping("/password")
    public void resetPassword(@RequestParam String employeeId, @RequestParam @Min(6) String update) {
        Employee employee = getEx(EmployeeGuard.validateSelfOnly(employeeId));
        employee.setPassword(update);
        getRepo().save(employee);
    }
}
