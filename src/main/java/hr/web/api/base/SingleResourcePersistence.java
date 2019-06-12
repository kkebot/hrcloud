package hr.web.api.base;

import hr.domain.SingleResource;
import hr.web.service.utils.PendingResource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public abstract class SingleResourcePersistence<E extends SingleResource, I, R extends CrudRepository<E, I>> extends SingleEntityQuery<E, I, R> {
    public SingleResourcePersistence(R entityRepo) {
        super(entityRepo);
    }

    protected abstract PendingResource preparePendingResource();

    protected E persistWithResource(MultipartFile file, E entity) throws IOException {
        PendingResource pending = preparePendingResource();
        if (file != null)
            entity.setResource(pending.predictByTimestamp(file));
        CrudRepository<E, I> repository = getRepo();
        E ret = repository.save(entity);
        pending.writeResources();
        return ret;
    }
}
