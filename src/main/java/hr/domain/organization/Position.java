package hr.domain.organization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hr.domain.FileResource;
import hr.domain.MetaEntity;
import hr.domain.SingleResource;
import hr.domain.employee.Employee;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"administration", "employees", "resource"}) // Prevent stack overflow error
@ToString(exclude = {"administration", "employees", "resource"}) // Prevent stack overflow error
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Prevent "no serializer found for" hibernate's hibernateLazyInitializer
public class Position implements SingleResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private PositionType type;

    private Date createdAt = new Date();
    private Date lastNormal = new Date();
    private Boolean status = true;

    @Min(1)
    private long expectedCount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private FileResource resource;

    private String description;

    // Entity mapping
    @OneToMany(mappedBy = "mainPosition", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Employee> employees = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(nullable = false)
    private Department administration;

    @JsonIgnore
    public MetaEntity meta() {
        return new MetaEntity(getId(), getName());
    }
}
