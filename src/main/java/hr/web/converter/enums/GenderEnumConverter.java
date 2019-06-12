package hr.web.converter.enums;

import hr.domain.employee.enums.Gender;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GenderEnumConverter implements Converter<String, Gender> {
    @Override
    public Gender convert(String source) {
        return Gender.forValue(source);
    }
}
