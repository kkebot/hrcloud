package hr.web.api.employee;

import hr.data.employee.ContractRepository;
import hr.domain.employee.Contract;
import hr.web.api.base.SingleMonthlyResourcePersistence;
import hr.web.api.exception.BadRequest;
import hr.web.service.FileSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/contract")
public class ContractController extends SingleMonthlyResourcePersistence<Contract, Long, ContractRepository> {
    private EmployeeQueryController employeeQuery;

    public ContractController(EmployeeQueryController employeeQuery, FileSettings fileSettings, ContractRepository contractRepo) {
        super(contractRepo, fileSettings.rootResolve("contract_sign"));
        this.employeeQuery = employeeQuery;
    }

    /**
     * curl -isX POST http://localhost:8080/api/contract/sign
     */
    @Transactional
    @PostMapping("/sign")
    public Contract signContract(@RequestParam(value = "file") MultipartFile file,
                                 @RequestParam long employeeId,
                                 Contract model) throws IOException {
        if (file == null ||
                model.getEffectiveOn() == null ||
                model.getEffectiveUntil() == null ||
                model.getEffectiveOn().after(model.getEffectiveUntil())) {
            throw new BadRequest("合约生效时间/到期时间无效");
        }

        model.setEmployee(employeeQuery.getEx(employeeId));

        return persistWithResource(file, model);
    }

    @GetMapping("/{contractId}")
    public Contract getContract(@PathVariable long contractId) {
        Contract contract = getEx(contractId);
        EmployeeGuard.validateSelfOnly(contract.getEmployee());

        return contract;
    }

    @GetMapping
    public Page<Contract> getContracts(@RequestParam String employeeId, Pageable pageable) {
        return getRepo().findByEmployee_IdOrderByStatusDescEffectiveUntilAsc(EmployeeGuard.validateSelfOnly(employeeId), pageable);
    }

}
