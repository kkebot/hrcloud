package hr.web.converter.enums;

import hr.domain.employee.enums.Marriage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MarriageEnumConverter implements Converter<String, Marriage> {
    @Override
    public Marriage convert(String s) {
        return Marriage.forValue(s);
    }
}
