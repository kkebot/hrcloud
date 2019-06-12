package hr.domain.organization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import hr.domain.FileResource;
import hr.domain.MetaEntity;
import hr.domain.SingleResource;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Entity
@ToString(exclude = {
        "administration", "subordinates", "positions", "resource"}) // Prevent stack overflow error
@EqualsAndHashCode(exclude = {
        "administration", "subordinates", "positions", "resource"}) // Prevent stack overflow error
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Prevent "no serializer found for" hibernate's hibernateLazyInitializer
public class Department implements SingleResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Uniqueness is ensured by the API controller which prevents duplicate names under a department
     */
    @NonNull
    @NotNull
    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @Column(nullable = false)
    private String previous;

//    public void setHierarchyFrom(Department department) {
//        setHierarchy(department.getHierarchy() + "/" + department.getId());
//    }

    @JsonProperty("hierarchy")
    public String getHierarchy() {
        return getPrevious() + getId() + "/";
    }

    @JsonProperty("hierarchy")
    public void setHierarchy(String s) {

    }

    public void setPreviousFrom(Department department) {
        setAdministration(department);
        setPrevious(department.getHierarchy());
    }

    @JsonIgnore
    public Collection<Long> getHierarchyIds() {
        return Arrays.stream(getHierarchy().split("/"))
                .filter(s -> !s.equals(""))
                .map(Long::valueOf).collect(Collectors.toList());
    }

    private Date createdAt = new Date();
    private Boolean status = true;


    private String description;

    // Here comes the entity mappings
    /**
     * administration -> subordinates
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Department administration;
    /**
     * administration -> subordinates
     */
    @OneToMany(mappedBy = "administration")
    @JsonIgnore
    private List<Department> subordinates = new ArrayList<>();
    /**
     * administration -> positions
     */
    @OneToMany(mappedBy = "administration")
    @JsonIgnore
    private List<Position> positions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties(allowGetters = true)
    private FileResource resource;

    @JsonIgnore
    public MetaEntity meta() {
        return new MetaEntity(getId(), getName());
    }

}
