package hr.domain.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.domain.FileResource;
import hr.domain.employee.enums.AttachmentType;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"employee", "resource"})
@EqualsAndHashCode(exclude = {"employee", "resource"})
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @NonNull
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @NonNull
    @Column(nullable = false)
    private AttachmentType type;

    @NonNull
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private FileResource resource;

}
