package hr.web.api.employee;

import hr.data.employee.ContractRenewRepository;
import hr.domain.employee.ContractRenew;
import hr.web.api.base.SingleMonthlyResourcePersistence;
import hr.web.api.exception.BadRequest;
import hr.web.service.ContractRenewManager;
import hr.web.service.FileSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/contract/renew")
public class ContractRenewController extends SingleMonthlyResourcePersistence<ContractRenew, Long, ContractRenewRepository> {
    private ContractRenewManager contractRenewManager;

    public ContractRenewController(ContractRenewManager contractRenewManager, FileSettings fileSettings, ContractRenewRepository contractRenewRepo) {
        super(contractRenewRepo, fileSettings.rootResolve("contract_renew"));

        this.contractRenewManager = contractRenewManager;
    }

    @Transactional
    @PostMapping
    public void renewContract(
            @RequestParam long contractId,
            @RequestParam(required = false) Date renewTerm,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Date effectiveOn,
            @RequestParam(required = false) MultipartFile file) throws IOException {

        ContractRenewManager.Builder builder = contractRenewManager.getBuilder(contractId);
        if (renewTerm == null)
            builder.cancel();
        else if (!builder.renew(renewTerm))
            throw new BadRequest("合约到期时间无效");

        builder.describe(description);
        builder.since(effectiveOn);

        persistWithResource(file, builder.getRenew());
    }

    @Transactional
    @PostMapping("/update")
    public void updateContractRenew(@RequestParam long contractRenewId, ContractRenew model, @RequestParam(required = false) MultipartFile file) throws IOException {
        ContractRenew renew = getEx(contractRenewId);

        renew.setDescription(model.getDescription());
        renew.setEffectiveOn(model.getEffectiveOn());

        persistWithResource(file, renew);
    }

    @GetMapping
    public Page<ContractRenew> getRenewHistory(@RequestParam long contractId, Pageable pageable) {
        return getRepo().findByContract_IdOrderByOriginalTermDesc(contractId, pageable);
    }
}
