package hr.web.converter.enums;

import hr.domain.employee.enums.Education;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EducationConverter implements Converter<String, Education> {
    @Override
    public Education convert(String source) {
        return Education.forValue(source);
    }
}
