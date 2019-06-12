package hr.web.api.base;

import hr.web.api.exception.NotFound;
import org.springframework.data.repository.CrudRepository;

public class SingleEntityQuery<E,I, R extends CrudRepository<E,I>> {

    private R entityRepo;

    public SingleEntityQuery(R entityRepo) {
        this.entityRepo = entityRepo;
    }

    public E getEx(I id) {
        return this.entityRepo.findById(id).orElseThrow(NotFound::new);
    }

    public E get(I id) {
        return this.entityRepo.findById(id).orElse(null);
    }

    public R getRepo() {
        return entityRepo;
    }
}
