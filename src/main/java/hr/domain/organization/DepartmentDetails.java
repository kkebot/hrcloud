package hr.domain.organization;

import hr.domain.FileResource;
import hr.domain.MetaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentDetails {
    private Long id;
    private String name;
    private String fullname;
    private Date createdAt;
    private Boolean status;
    private FileResource resource;
    private String description;
    private List<MetaEntity> subordinates;
    private List<Position> positions;
    private MetaEntity administration;
    private int countEmployees;
    private int countPositions;
    private int countDepartments;
}
