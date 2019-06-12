package hr.domain.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import hr.domain.FileResource;
import hr.domain.MetaEntity;
import hr.domain.SingleResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"employee", "resource"}) // Prevent stack overflow error
@ToString(exclude = {"employee", "resource"}) // Prevent stack overflow error
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Prevent "no serializer found for" hibernate's hibernateLazyInitializer
public class Contract implements SingleResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @JsonProperty("employee")
    public MetaEntity metaEmployee() {
        return employee.meta();
    }

    private String description;

    private boolean status = true;

    private Date effectiveOn = new Date();
    @NotNull
    @Column(nullable = false)
    private Date effectiveUntil;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private FileResource resource;
}
