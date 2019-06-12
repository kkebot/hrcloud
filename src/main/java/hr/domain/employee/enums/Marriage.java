package hr.domain.employee.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public enum Marriage {
    @JsonProperty("未婚")
    UNMARRIED,
    @JsonProperty("已婚")
    MARRIED,
    @JsonProperty("丧偶")
    WINDOWED,
    @JsonProperty("离异")
    DIVORCE;

    private static final Map<String, Marriage> NAMES_LOOKUP = new HashMap<>();
    static {
        NAMES_LOOKUP.put("未婚", UNMARRIED);
        NAMES_LOOKUP.put("已婚", MARRIED);
        NAMES_LOOKUP.put("丧偶", WINDOWED);
        NAMES_LOOKUP.put("离异", DIVORCE);
    }

    @JsonCreator
    public static Marriage forValue(String string) {
        return NAMES_LOOKUP.get(string);
    }
}
