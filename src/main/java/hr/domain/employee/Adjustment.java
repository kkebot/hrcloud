package hr.domain.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import hr.domain.FileResource;
import hr.domain.MetaEntity;
import hr.domain.SingleResource;
import hr.domain.organization.Position;
import hr.domain.wage.PayScale;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"resource", "employee", "to", "after"})
@ToString(exclude = {"resource", "employee", "to", "after"})
public class Adjustment implements SingleResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Employee employee;

    @JsonProperty("employee")
    public MetaEntity metaEmployee() {
        return employee.meta();
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Position to; // null indicate a discharge

    @JsonProperty("to")
    public MetaEntity metaTo() {
        return to != null ? to.meta() : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private PayScale after;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private FileResource resource;

    public boolean isPositionUnchanged() {
        Position from = employee.getMainPosition();
        if (from != null && to != null)
            return from.getId().equals(to.getId());
        else
            return to == null;
    }

    private boolean isScaleUnchanged() {
        PayScale before = employee.getScale();
        if (before != null && after != null)
            return before.getId().equals(after.getId());
        else
            return before == after;
    }

    private Date effectiveOn = new Date();   // when this change takes effect
    private String description; // Additional information
}
