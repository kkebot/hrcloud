package hr.web.api.organization;

import hr.data.employee.AdjustmentRepository;
import hr.data.employee.EmployeeRepository;
import hr.data.organization.DepartmentRepository;
import hr.data.organization.PositionRepository;
import hr.domain.MetaEntity;
import hr.domain.employee.Adjustment;
import hr.domain.organization.Department;
import hr.domain.organization.Position;
import hr.domain.organization.PositionDetails;
import hr.domain.organization.PositionType;
import hr.web.api.base.SingleEntityQuery;
import hr.web.api.exception.BadRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/position")
public class PositionQueryController extends SingleEntityQuery<Position, Long, PositionRepository> {
    private DepartmentRepository departmentRepo;
    private EmployeeRepository employeeRepo;
    private AdjustmentRepository adjustmentRepo;

    public PositionQueryController(PositionRepository positionRepo, DepartmentRepository departmentRepo, EmployeeRepository employeeRepo, AdjustmentRepository adjustmentRepo) {
        super(positionRepo);
        this.departmentRepo = departmentRepo;
        this.employeeRepo = employeeRepo;
        this.adjustmentRepo = adjustmentRepo;
    }

    @GetMapping("/{positionId}/details")
    public PositionDetails getPositionDetails(@PathVariable Long positionId) {
        Position p = getEx(positionId);

        Department department = p.getAdministration();
        Collection<Department> ascendants = departmentRepo.findAllById(department.getHierarchyIds());
        String fullname = ascendants.stream().map(Department::getName).collect(Collectors.joining("/"));

        PositionDetails details = new PositionDetails();

        details.setAdministration(new MetaEntity(department.getId(), fullname));
        details.setId(positionId);
        details.setActualCount(employeeRepo.countByMainPosition_Id(positionId));
        details.setCreatedAt(p.getCreatedAt());
        details.setDescription(p.getDescription());
        details.setExpectedCount(p.getExpectedCount());
        details.setLastNormal(p.getLastNormal());
        details.setName(p.getName());
        details.setStatus(p.getStatus());
        details.setType(p.getType());
        details.setResource(p.getResource());

        return details;
    }

    @GetMapping("/{positionId}/adjustments")
    public Page<Adjustment> getPositionAdjustments(@PathVariable Long positionId, Pageable pageable) {
        return adjustmentRepo.queryByPosition(positionId, pageable);
    }

    @GetMapping("/types")
    public PositionType[] getPositionTypes() {
        return PositionType.values();
    }

    @GetMapping("/search")
    public Page<Position> searchDepartment(@RequestParam String keyword, @RequestParam String value, Pageable pageable) {
        switch (keyword) {
            case "id": {
                Position position = get(Long.valueOf(value));
                if (position == null) {
                    return new PageImpl<>(Collections.emptyList());
                } else
                    return new PageImpl<>(Collections.singletonList(position));
            } case "name":
                return getRepo().findByNameContaining(value, pageable);
            default:
                throw new BadRequest();
        }
    }

    @GetMapping("/{positionId}")
    public Position getPosition(@PathVariable long positionId) {
        return getEx(positionId);
    }
}
