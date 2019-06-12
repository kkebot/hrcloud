package hr.web.converter.enums;

import hr.domain.organization.PositionType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PositionTypeConverter implements Converter<String, PositionType> {
    @Override
    public PositionType convert(String s) {
        return PositionType.forValue(s);
    }
}
