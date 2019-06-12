package hr.web.service;

import hr.data.employee.ContractRepository;
import hr.domain.employee.Contract;
import hr.domain.employee.ContractRenew;
import hr.web.api.exception.NotFound;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContractRenewManager {

    private ContractRepository contractRepo;

    public ContractRenewManager(ContractRepository contractRepo) {
        this.contractRepo = contractRepo;
    }

    public class Builder {
        @Getter
        private ContractRenew renew = new ContractRenew();

        Builder(Contract contract) {
            renew.setContract(contract);
            renew.setOriginalTerm(contract.getEffectiveUntil());
        }

        public boolean renew(Date date) {
            if (date.before(renew.getContract().getEffectiveUntil()))
                return false;
            renew.getContract().setEffectiveUntil(date);
            return true;
        }

        public void cancel() {
            renew.getContract().setStatus(false);
        }

        public void describe(String description) {
            renew.setDescription(description);
        }

        public void since(Date date) {
            renew.setEffectiveOn(date);
        }
    }

    public Contract get(long contractId) {
        return contractRepo.findByIdAndStatusIsTrue(contractId).orElseThrow(() -> new NotFound(contractId));
    }

    public Builder getBuilder(Long contractId) {
        return new Builder(get(contractId));
    }

}
