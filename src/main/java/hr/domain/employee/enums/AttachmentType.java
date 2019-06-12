package hr.domain.employee.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public enum AttachmentType {
    @JsonProperty("入职通知书")
    OFFER_LETTER,
    @JsonProperty("入职审批表")
    ENTRY_FORM,
    @JsonProperty("毕业证书")
    GRADUATION_CERTIFICATE,
    @JsonProperty("户口本")
    HOUSEHOLD_REGISTER,
    @JsonProperty("结婚证")
    MARRIAGE_CERTIFICATE,
    @JsonProperty("资格证书")
    QUALIFICATION_CERTIFICATE,
    @JsonProperty("身份证")
    IDENTITY_CARD,
    @JsonProperty("个人照片")
    AVATAR;


    private static final Map<String, AttachmentType> NAMES_LOOKUP = new HashMap<>();
    static {
        NAMES_LOOKUP.put("入职通知书", OFFER_LETTER);
        NAMES_LOOKUP.put("入职审批表", ENTRY_FORM);
        NAMES_LOOKUP.put("毕业证书", GRADUATION_CERTIFICATE);
        NAMES_LOOKUP.put("户口本", HOUSEHOLD_REGISTER);
        NAMES_LOOKUP.put("结婚证", MARRIAGE_CERTIFICATE);
        NAMES_LOOKUP.put("资格证书", QUALIFICATION_CERTIFICATE);
        NAMES_LOOKUP.put("身份证", IDENTITY_CARD);
        NAMES_LOOKUP.put("个人照片", AVATAR);
    }

    @JsonCreator
    public static AttachmentType forValue(String string) {
        return NAMES_LOOKUP.get(string);
    }
}
