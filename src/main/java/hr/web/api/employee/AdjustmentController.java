package hr.web.api.employee;

import hr.data.employee.AdjustmentRepository;
import hr.data.employee.ContractRepository;
import hr.domain.employee.Adjustment;
import hr.domain.employee.Employee;
import hr.domain.organization.Position;
import hr.web.api.base.SingleMonthlyResourcePersistence;
import hr.web.api.exception.BadRequest;
import hr.web.api.exception.Conflict;
import hr.web.api.organization.PositionQueryController;
import hr.web.api.wage.PayScaleQueryController;
import hr.web.service.AdjustmentBuilder;
import hr.web.service.FileSettings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/api/adjustment")
public class AdjustmentController extends SingleMonthlyResourcePersistence<Adjustment, Long, AdjustmentRepository> {
    private EmployeeQueryController employeeQuery;
    private PositionQueryController positionQuery;
    private PayScaleQueryController payScaleQuery;

    private ContractRepository contractRepo;


    public AdjustmentController(AdjustmentRepository adjustmentRepo, FileSettings fileSettings, EmployeeQueryController employeeQuery, PositionQueryController positionQuery, PayScaleQueryController payScaleQuery, ContractRepository contractRepo) {
        super(adjustmentRepo, fileSettings.rootResolve("adjustment"));
        this.employeeQuery = employeeQuery;
        this.positionQuery = positionQuery;
        this.payScaleQuery = payScaleQuery;
        this.contractRepo = contractRepo;
    }

    // Discharge request should not go directly here
    @Transactional
    @PostMapping
    public void adjust(@RequestParam long employeeId,
            Adjustment formData,
            @RequestParam(required = false) Long toId,
            @RequestParam(required = false) Long afterId,
            @RequestParam(required = false) MultipartFile file) throws IOException {

        Employee employee = employeeQuery.getEx(employeeId);
        if (!employee.getStatus())
            throw new BadRequest("员工已离职");
        AdjustmentBuilder builder = new AdjustmentBuilder(employee);
        if (toId != null) {
            Position dest = positionQuery.getEx(toId);
            if (!dest.getStatus())
                throw new BadRequest("岗位已撤销");
            builder.appoint(dest);
        }

        if (afterId != null)
            builder.change(payScaleQuery.getEx(afterId));

        if (builder.isUnchanged() || (toId == null && afterId == null))
            throw new BadRequest("岗位和薪级无变化");

        builder.describe(formData.getDescription());
        builder.since(formData.getEffectiveOn());

        persistWithResource(file, builder.getAdjustment());
    }

    @PostMapping("/discharge")
    public void discharge(@RequestParam long employeeId,
                          @RequestParam String description,
                          @RequestParam Date effectiveOn,
                          @RequestParam(required = false) MultipartFile file) throws IOException {
        if (contractRepo.existsByEmployee_IdAndStatusIsTrue(employeeId))
            throw new Conflict("仍然存在生效合同");

        AdjustmentBuilder builder = new AdjustmentBuilder(employeeQuery.getEx(employeeId));

        builder.discharge();
        builder.describe(description);
        builder.since(effectiveOn);

        if (builder.isUnchanged())
            throw new BadRequest("岗位和薪级无变化");

        persistWithResource(file, builder.getAdjustment());
    }

    @GetMapping
    public Page<Adjustment> getAdjustmentHistory(Pageable pageable, @RequestParam String employeeId) {
        return getRepo().findByEmployee_IdOrderByEffectiveOnDesc(EmployeeGuard.validateSelfOnly(employeeId), pageable);
    }

    @Transactional
    @PostMapping("/{adjustmentId}")
    public void updateAdjustment(Adjustment formData, @PathVariable long adjustmentId,
            MultipartFile file) throws IOException {

        Adjustment adjustment = getEx(adjustmentId);
        adjustment.setEffectiveOn(formData.getEffectiveOn());
        adjustment.setDescription(formData.getDescription());

        persistWithResource(file, adjustment);
    }
}
