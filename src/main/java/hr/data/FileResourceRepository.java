package hr.data;

import hr.domain.FileResource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileResourceRepository extends CrudRepository<FileResource, Long> {
}
