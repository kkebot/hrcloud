package hr.domain.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import hr.domain.FileResource;
import hr.domain.SingleResource;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = {"contract","resource"})
@EqualsAndHashCode(exclude = {"contract","resource"}, callSuper = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Prevent "no serializer found for" hibernate's hibernateLazyInitializer
public class ContractRenew implements SingleResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @JoinColumn(nullable = false) // https://stackoverflow.com/a/4121529
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Contract contract;

    @JsonProperty
    public Long getContractId() {
        return contract.getId();
    }

    private Date originalTerm;
    private Date effectiveOn = new Date();   // when this change takes effect
    private String description; // Additional information

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private FileResource resource;

}
