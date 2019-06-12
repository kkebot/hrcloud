package hr.web.converter.ignore;

import hr.domain.wage.PayScale;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToPayScaleConverter implements Converter<String, PayScale> {
    @Override
    public PayScale convert(String source) {
        return null;
    }
}
