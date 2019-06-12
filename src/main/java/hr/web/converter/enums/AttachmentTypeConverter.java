package hr.web.converter.enums;

import hr.domain.employee.enums.AttachmentType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AttachmentTypeConverter implements Converter<String, AttachmentType> {
    @Override
    public AttachmentType convert(String source) {
        // TODO
        return AttachmentType.forValue(source);
    }
}
