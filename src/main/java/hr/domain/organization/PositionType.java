package hr.domain.organization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;


public enum PositionType {
    @JsonProperty("行政")
    SUPER_ADMIN,

    @JsonProperty("管理")
    ADMINISTRATION,

    @JsonProperty("技术")
    TECHNOLOGY,

    @JsonProperty("后勤")
    BACK_OFFICE;

    PositionType() {
    }

    private static final Map<String, PositionType> NAMES_LOOKUP = new HashMap<>();

    static {
        NAMES_LOOKUP.put("行政", SUPER_ADMIN);
        NAMES_LOOKUP.put("管理", ADMINISTRATION);
        NAMES_LOOKUP.put("技术", TECHNOLOGY);
        NAMES_LOOKUP.put("后勤", BACK_OFFICE);
    }

    @JsonCreator
    public static PositionType forValue(String string) {
        return NAMES_LOOKUP.get(string);
    }

}
