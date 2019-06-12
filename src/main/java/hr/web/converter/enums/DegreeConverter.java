package hr.web.converter.enums;

import hr.domain.employee.enums.Degree;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DegreeConverter implements Converter<String, Degree> {
    @Override
    public Degree convert(String source) {
        return Degree.forValue(source);
    }
}
