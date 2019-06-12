package hr.domain.notify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NotifyRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String description;
    @NotNull
    @Column(length = 1000)
    private String sqlStatement;
    @NotNull
    private String entityType;

    private boolean multiple = false;

    private String overview;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt = new Date();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean status = true;
}
