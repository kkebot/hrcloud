package hr.domain.notify;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "rule")
@ToString(exclude = "rule")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"rule_id", "entityId"}))
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt = new Date();
    @NonNull
    private Long entityId;
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private NotifyRule rule;

    @JsonProperty("ruleId")
    public Long getRuleId() {
        return rule != null ? rule.getId() : null;
    }

    private String entityType;

    private Boolean isRead = false;

    public Notification(Long entityId, NotifyRule rule) {
        this.entityId = entityId;
        this.rule = rule;
        this.entityType = rule.getEntityType();
    }

    @JsonIgnore
    public NotifyContent toContent() {
        return new NotifyContent(getEntityId(), getRule().getId());
    }

}
