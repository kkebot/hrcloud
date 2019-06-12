package hr.web.api.organization;

import hr.data.employee.EmployeeRepository;
import hr.data.organization.PositionRepository;
import hr.domain.organization.Department;
import hr.domain.organization.Position;
import hr.web.api.base.SingleMonthlyResourcePersistence;
import hr.web.api.exception.Conflict;
import hr.web.service.FileSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/position")
public class PositionChangeController extends SingleMonthlyResourcePersistence<Position, Long, PositionRepository> {
    private DepartmentQueryController departmentQuery;
    private EmployeeRepository employeeRepo;

    public PositionChangeController(PositionRepository positionRepo, DepartmentQueryController departmentQuery, EmployeeRepository employeeRepo, PositionQueryController positionQuery, FileSettings fileSettings) {
        super(positionRepo, fileSettings.rootResolve("position"));

        this.departmentQuery = departmentQuery;
        this.employeeRepo = employeeRepo;
    }

    private boolean isDuplicateUnder(String name, Department department) {
        return getRepo().existsByAdministrationAndNameAndStatusIsTrue(department, name);
    }

    // TODO SUPER
    @Transactional
    @PostMapping
    public Position setupPositionUnder(
            @RequestParam long departmentId,
            @Validated Position model, // @RequestBody leads to 415
            @RequestParam(required = false) MultipartFile file) throws IOException {

        Department admin = departmentQuery.getEx(departmentId);
        if (isDuplicateUnder(model.getName(), admin)) {
            throw new Conflict("命名冲突");
        }

        model.setAdministration(admin);
        return persistWithResource(file, model);
    }

    // TODO SUPER
    @PostMapping("/cancel")
    public void cancelPosition(@RequestParam long positionId) {
        if (employeeRepo.existsByMainPosition_IdAndStatusIsTrue(positionId))
            throw new Conflict("仍然存在员工隶属该岗位");

        Position position = getEx(positionId);
        position.setStatus(false);
        getRepo().save(position);
    }

    // TODO SUPER
    @Transactional
    @PostMapping("/update")
    public Position updatePosition(
            @RequestParam long positionId,
            @Validated Position model,
            @RequestParam(required = false) MultipartFile file) throws IOException {

        Position old = getEx(positionId);
        if (!old.getName().equals(model.getName()) && isDuplicateUnder(model.getName(), old.getAdministration()))
            throw new Conflict("命名冲突");

        old.setCreatedAt(model.getCreatedAt());
        old.setName(model.getName());
        int actualCount = old.getEmployees().size();
        if ((actualCount < old.getExpectedCount() && actualCount >= model.getExpectedCount())
                || (actualCount > old.getExpectedCount() && actualCount <= model.getExpectedCount())) {
            old.setLastNormal(new Date());
        }
        old.setExpectedCount(model.getExpectedCount());
        old.setDescription(model.getDescription());

        return persistWithResource(file, old);
    }
}
