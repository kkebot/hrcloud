package hr.domain.wage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import hr.domain.MetaEntity;
import hr.domain.employee.Employee;
import hr.domain.organization.Position;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {
        "employee", "currentPosition", "currentScale"})
@EqualsAndHashCode(exclude = {
        "employee", "currentPosition", "currentScale"}, callSuper = false)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"period", "employee_id"}))
public class WageRecord extends WageTerms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // https://thoughts-on-java.org/5-things-you-need-to-know-when-using-hibernate-with-mysql/
    private Long id;
    // can not be named "month"
    @NonNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date period; // Only the year and month make sense

    @JsonIgnore
    private Date lastModified = new Date();

    // The order of following fields matters in excel
    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    @JsonIgnore
    @JoinColumn(nullable = false)
    private Employee employee;
    @JsonProperty("employee")
    public MetaEntity metaEmployee() {
        return employee.meta();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    @JsonIgnore
    private Position currentPosition;
    @JsonProperty("currentPosition")
    public MetaEntity metaCurrentPosition() {
        return currentPosition.meta();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    private PayScale currentScale;

    @NonNull
    private Date importTimestamp;

}
