package hr.data.notify;

import hr.domain.notify.Notification;
import hr.domain.notify.NotifyRule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    int deleteAllByIsReadIsFalseAndRule(NotifyRule rule);
    Collection<Notification> findByIsReadIsFalse();

    // https://www.baeldung.com/spring-data-jpa-null-parameters
    @Modifying
    @Query("UPDATE Notification n SET n.isRead=true WHERE n.id=?1")
    int markNotificationAsRead(Long notificationId);
    // https://www.baeldung.com/spring-data-jpa-null-parameters
    @Query("SELECT n FROM Notification n WHERE (?1 IS NULL OR n.isRead=?1) AND (?2 IS NULL OR n.rule.id=?2)")
    Page<Notification> findByIsReadAndRule_IdOrderByCreatedAtDesc(Boolean isRead, Long ruleId, Pageable pageable);

}
