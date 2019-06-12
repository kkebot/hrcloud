package hr.web.api.organization;

import hr.data.organization.DepartmentRepository;
import hr.data.organization.PositionRepository;
import hr.domain.organization.Department;
import hr.web.api.base.SingleMonthlyResourcePersistence;
import hr.web.api.exception.Conflict;
import hr.web.service.FileSettings;
import hr.web.service.utils.PendingResource;
import hr.web.service.utils.PendingResourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/department")
public class DepartmentChangeController extends SingleMonthlyResourcePersistence<Department, Long, DepartmentRepository> {
    private PositionRepository positionRepo;

    public DepartmentChangeController(DepartmentRepository departmentRepo, PositionRepository positionRepo, FileSettings fileSettings) {
        super(departmentRepo, fileSettings.rootResolve("department"));
        this.positionRepo = positionRepo;
    }

    private boolean isDuplicateUnder(String name, Department administration) {
        return getRepo().existsByAdministrationAndNameAndStatusIsTrue(administration, name);
    }

    private boolean isAnySubordinateOn(long departmentId) {
        return getRepo().existsByAdministration_IdAndStatusIsTrue(departmentId);
    }

    private boolean hasAnyPositionEnabled(long departmentId) {
        return positionRepo.existsByAdministration_IdAndStatusIsTrue(departmentId);
    }


    // TODO SUPER
    @Transactional
    @PostMapping
    public Department setupDepartmentUnder(
            @Validated Department model,
            @RequestParam long departmentId,
            @RequestParam(required = false) MultipartFile file) throws IOException {

        Department administration = getEx(departmentId);
        if (isDuplicateUnder(model.getName(), administration))
            throw new Conflict("命名冲突");

        model.setPreviousFrom(administration);
        return persistWithResource(file, model);
    }

    // TODO SUPER
    @PostMapping("/shutdown")
    public void shutdownDepartment(@RequestParam long departmentId) {
        if (isAnySubordinateOn(departmentId) || hasAnyPositionEnabled(departmentId)) {
            throw new Conflict("仍然存在下级部门或下设岗位");
        }

        Department department = getEx(departmentId);
        department.setStatus(false);
        getRepo().save(department);
    }

    // TODO SUPER
    @Transactional
    @PostMapping("/update")
    public Department updateDepartment(@Validated Department model, @RequestParam long departmentId,
                                 @RequestParam(required = false) MultipartFile file) throws IOException {

        Department old = getEx(departmentId); // It is ensured to be enabled.
        Department admin = old.getAdministration();

        if (!old.getName().equals(model.getName()) && isDuplicateUnder(model.getName(), admin))
            throw new Conflict("命名冲突");

        old.setCreatedAt(model.getCreatedAt());
        old.setDescription(model.getDescription());
        old.setName(model.getName());

        return persistWithResource(file, old);
    }
}
