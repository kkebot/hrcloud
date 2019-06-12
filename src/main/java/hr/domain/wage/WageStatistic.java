package hr.domain.wage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import hr.domain.MetaEntity;
import hr.domain.organization.Department;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"period", "department_id"}))
@EqualsAndHashCode(exclude = "department", callSuper = false)
@ToString(exclude = "department")
public class WageStatistic extends WageTerms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // https://thoughts-on-java.org/5-things-you-need-to-know-when-using-hibernate-with-mysql/
    private long id;

    @JsonIgnore
    @NonNull
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @JsonProperty("department")
    public MetaEntity metaDepartment() {
        return department.meta();
    }

    @NonNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date period; // Only the year and month make sense

    private long countEmployee = 0;
    private boolean blur = false;

    public void compoundAdd(WageRecord record) {
        super.compoundAdd(record);
        countEmployee += 1;
    }

    @Override
    public void clear() {
        super.clear();
        countEmployee = 0;
        blur = false;
    }

    public void compoundAdd(WageStatistic wageStatistic) {
        super.compoundAdd(wageStatistic);
        countEmployee += wageStatistic.countEmployee;
    }
}
