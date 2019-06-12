package hr.data.notify;

import hr.domain.notify.NotifyRule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NotifyRuleRepository extends CrudRepository<NotifyRule, Long> {

    Collection<NotifyRule> findByStatusIsTrue();
    @Query("SELECT nr FROM NotifyRule nr WHERE (?1 IS NULL or nr.status=?1) ORDER BY nr.createdAt DESC")
    Page<NotifyRule> findByStatusOrderByCreatedAtDesc(Boolean status, Pageable pageable);
    boolean existsBySqlStatementAndStatus(String sqlStatement, boolean status);

}
