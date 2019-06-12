package hr.domain.notify;


import lombok.Value;

@Value
public class NotifyContent {
    private Long entityId;
    private Long ruleId;
}
