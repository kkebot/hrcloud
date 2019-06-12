package hr.domain.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import hr.domain.MetaEntity;
import hr.domain.employee.enums.*;
import hr.domain.organization.Position;
import hr.domain.wage.PayScale;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@ToString(exclude = {"mainPosition"}) // Writing [java.lang.StackOverflowError]
@EqualsAndHashCode(exclude = {"mainPosition"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Prevent "no serializer found for" hibernate's hibernateLazyInitializer
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(unique = true, nullable = false)
    private String credential;
    @NonNull
    @Column(nullable = false)
    private String name;
    @NonNull
    private Gender gender;
    private Date birth;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Position mainPosition;

    @JsonProperty("mainPosition")
    public MetaEntity metaMainPosition() {
        return mainPosition == null ? null : mainPosition.meta();
    }

    private Boolean status = true;
    @ManyToOne(fetch = FetchType.LAZY)
    private PayScale scale;
    private Date entryTime;


    private String address;
    private String email;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> phoneNumbers = new ArrayList<>();

    private String college;
    private String major;
    private Date graduateTime;
    private Degree degree;
    private Education education;

    private PoliticalStatus politicalStatus;
    private Marriage marriage;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Authority> authorities = new ArrayList<>();

    @JsonIgnore
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities.stream().map(authority -> authority.granted).collect(Collectors.toList());
    }

    @JsonIgnore
    private String password;

    // TODO More fields

    @JsonIgnore
    public MetaEntity meta() {
        return new MetaEntity(id, name);
    }

}
