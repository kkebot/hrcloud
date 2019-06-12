package hr.web.converter.ignore;

import hr.domain.organization.Position;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToPositionConverter implements Converter<String, Position> {
    @Override
    public Position convert(String source) {
        return null;
    }
}
