package hr.domain.employee.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

// http://www.moe.gov.cn/s78/A03/moe_560/jytjsj_2016/2016_qg/201708/t20170823_311668.html
public enum Education {
    // Primary Education
    @JsonProperty("其它")
    OTHER,
    @JsonProperty("小学")
    PRIMARY_SCHOOL,
    // Secondary Education
    @JsonProperty("初中")
    JUNIOR_SECONDARY_SCHOOL,
    @JsonProperty("中专")
    SECONDARY_VOCATIONAL_EDUCATION,
    @JsonProperty("高中")
    SENIOR_SECONDARY_SCHOOL,

    //  Higher Education
    @JsonProperty("专科")
    UNDERGRADUATE_SHORT_CYCLE,
    @JsonProperty("本科")
    UNDERGRADUATE_NORMAL,
    @JsonProperty("硕士研究生")
    POSTGRADUATE_MASTER,
    @JsonProperty("博士研究生")
    POSTGRADUATE_DOCTOR;

    private static final Map<String, Education> NAMES_LOOKUP = new HashMap<>();
    static {
        NAMES_LOOKUP.put("其它", OTHER);
        NAMES_LOOKUP.put("小学", PRIMARY_SCHOOL);
        NAMES_LOOKUP.put("初中", JUNIOR_SECONDARY_SCHOOL);
        NAMES_LOOKUP.put("中专", SECONDARY_VOCATIONAL_EDUCATION);
        NAMES_LOOKUP.put("高中", SENIOR_SECONDARY_SCHOOL);
        NAMES_LOOKUP.put("专科", UNDERGRADUATE_SHORT_CYCLE);
        NAMES_LOOKUP.put("本科", UNDERGRADUATE_NORMAL);
        NAMES_LOOKUP.put("硕士研究生", POSTGRADUATE_MASTER);
        NAMES_LOOKUP.put("博士研究生", POSTGRADUATE_DOCTOR);
    }

    @JsonCreator
    public static Education forValue(String string) {
        return NAMES_LOOKUP.get(string);
    }
}
