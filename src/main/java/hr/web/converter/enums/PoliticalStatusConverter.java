package hr.web.converter.enums;

import hr.domain.employee.enums.PoliticalStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PoliticalStatusConverter implements Converter<String, PoliticalStatus> {
    @Override
    public PoliticalStatus convert(String source) {
        return PoliticalStatus.forValue(source);
    }
}
