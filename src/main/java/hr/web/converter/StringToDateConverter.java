package hr.web.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class StringToDateConverter implements Converter<String, Date> {
    private SimpleDateFormat dateFormat;

    public StringToDateConverter(@Value("${spring.jackson.date-format}") String jacksonDefaultDateFormat) {
        log.debug("Jackson default date format - {}", jacksonDefaultDateFormat);
        this.dateFormat = new SimpleDateFormat(jacksonDefaultDateFormat);
    }

    @Override
    public Date convert(String source) {
        if (source.equals(""))
            return null;
        try {
            return dateFormat.parse(source.replaceAll("Z$", "+0000"));
        } catch (ParseException e) {
            log.debug("Unable to parse string \"{}\" to date using date format - {}", source, dateFormat.toPattern());
            return new Date(source);
        }
    }
}
