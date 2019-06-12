package hr.domain.organization;

import hr.domain.FileResource;
import hr.domain.MetaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PositionDetails {
    private long id;
    private String name;
    private PositionType type;
    private Date createdAt;
    private Date lastNormal;
    private boolean status;
    private long expectedCount;
    private long actualCount;
    private FileResource resource;
    private String description;
    private MetaEntity administration;
}
