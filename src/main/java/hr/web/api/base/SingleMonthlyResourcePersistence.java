package hr.web.api.base;

import hr.domain.SingleResource;
import hr.web.service.utils.PendingResource;
import hr.web.service.utils.PendingResourceBuilder;
import org.springframework.data.repository.CrudRepository;

public class SingleMonthlyResourcePersistence<E extends SingleResource, I, R extends CrudRepository<E, I>>
        extends SingleResourcePersistence<E, I, R> {
    private PendingResourceBuilder builder;

    public SingleMonthlyResourcePersistence(R entityRepo, PendingResourceBuilder builder) {
        super(entityRepo);
        this.builder = builder;
    }

    @Override
    protected PendingResource preparePendingResource() {
        return builder.prepareMonthlyResource();
    }
}
