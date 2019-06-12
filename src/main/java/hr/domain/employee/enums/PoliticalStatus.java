package hr.domain.employee.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public enum PoliticalStatus {
    @JsonProperty("群众")
    PUBLIC_PEOPLE,
    @JsonProperty("共青团员")
    LEAGUE_MEMBER,
    @JsonProperty("党员")
    PARTY_MEMBER;

    private static final Map<String, PoliticalStatus> NAMES_LOOKUP = new HashMap<>();
    static {
        NAMES_LOOKUP.put("其它", PUBLIC_PEOPLE);
        NAMES_LOOKUP.put("共青团员", LEAGUE_MEMBER);
        NAMES_LOOKUP.put("党员", PARTY_MEMBER);
    }

    public static PoliticalStatus forValue(String source) {
        return NAMES_LOOKUP.get(source);
    }
}
