package hr.domain.employee.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public enum Degree {
    @JsonProperty("其它")
    OTHER,
    @JsonProperty("学士")
    BACHELOR,
    @JsonProperty("硕士")
    MASTER,
    @JsonProperty("博士")
    DOCTOR;

    private static final Map<String, Degree> NAMES_LOOKUP = new HashMap<>();
    static {
        NAMES_LOOKUP.put("其它", OTHER);
        NAMES_LOOKUP.put("学士", BACHELOR);
        NAMES_LOOKUP.put("硕士", MASTER);
        NAMES_LOOKUP.put("博士", DOCTOR);
    }

    public static Degree forValue(String source) {
        return NAMES_LOOKUP.get(source);
    }
}
