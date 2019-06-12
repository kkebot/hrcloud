package hr.web.converter.ignore;

import hr.domain.FileResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToResourceConverter implements Converter<String, FileResource> {
    @Override
    public FileResource convert(String source) {
        return null;
    }
}
