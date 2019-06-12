package hr.domain.employee.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
    @JsonProperty("男")
    MALE,
    @JsonProperty("女")
    FEMALE;

    private static final Map<String, Gender> NAMES_LOOKUP = new HashMap<>();
    static {
        NAMES_LOOKUP.put("男", MALE);
        NAMES_LOOKUP.put("女", FEMALE);
    }

    @JsonCreator
    public static Gender forValue(String string) {
        return NAMES_LOOKUP.get(string);
    }
}
