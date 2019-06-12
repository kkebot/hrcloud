package hr.web.api.wage;

import hr.data.wage.PayScaleRepository;
import hr.domain.wage.PayScale;
import hr.web.api.base.SingleEntityQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/scale")
public class PayScaleQueryController extends SingleEntityQuery<PayScale, Long, PayScaleRepository> {

    public PayScaleQueryController(PayScaleRepository payScaleRepo) {
        super(payScaleRepo);
    }

    @GetMapping
    public Collection<PayScale> getPayScale() {
        return getRepo().findAll();
    }
}
