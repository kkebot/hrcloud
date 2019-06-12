package hr.web.api.notify;

import hr.data.notify.NotificationRepository;
import hr.data.notify.NotifyRuleRepository;
import hr.domain.notify.Notification;
import hr.domain.notify.NotifyContent;
import hr.domain.notify.NotifyRule;
import hr.web.api.exception.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping("/api/notify")
public class NotificationController {
    private NotificationRepository notificationRepo;
    private NotifyRuleRepository notifyRuleRepo;

    private EntityManager entityManager;

    public NotificationController(NotificationRepository notificationRepo, NotifyRuleRepository notifyRuleRepo, EntityManager entityManager) {
        this.notificationRepo = notificationRepo;
        this.notifyRuleRepo = notifyRuleRepo;
        this.entityManager = entityManager;

    }

    @GetMapping
    public Page<Notification> getNotifications(
            @RequestParam(required = false) Boolean isRead,
            @RequestParam(required = false) Long ruleId,
            Pageable pageable) {
        return notificationRepo.findByIsReadAndRule_IdOrderByCreatedAtDesc(isRead, ruleId, pageable);
    }

    @Transactional
    @PostMapping("/read")
    public void markAsRead(@RequestParam Long notificationId) {
        if (notificationRepo.markNotificationAsRead(notificationId) == 0)
            throw new NotFound(notificationId);
    }

    public Stream<Notification> scan(NotifyRule rule) {
        Query query = entityManager.createNativeQuery(rule.getSqlStatement());
        List<BigInteger> results;
        if (rule.isMultiple()) {
            results = ((List<Object[]>)query.getResultList()).stream()
                    .map(objects -> (BigInteger)objects[0]).collect(Collectors.toList());
        } else {
            results = query.getResultList();
        }
        return results.stream().map(bigInteger -> new Notification(bigInteger.longValue(), rule));
    }

//    https://blog.csdn.net/Mr_Smile2014/article/details/49455483
//    @Transactional
    @PostMapping("/scan")
    public void scanAll() {
        Collection<Notification> old = notificationRepo.findByIsReadIsFalse();
        Collection<NotifyRule> rules = notifyRuleRepo.findByStatusIsTrue();

        Collection<Notification> newlyDiscovered = rules.stream().map(this::scan)
                .flatMap(s -> s).collect(Collectors.toList());

        Set<NotifyContent> alreadyExist = old.stream()
                .map(Notification::toContent)
                .collect(Collectors.toSet());

        Set<NotifyContent> intersect = newlyDiscovered.stream()
                .map(Notification::toContent)
                .filter(alreadyExist::contains)
                .collect(Collectors.toSet());

        Collection<Notification> toDelete = old.stream()
                .filter(n -> !intersect.contains(n.toContent()))
                .collect(Collectors.toList());
        Collection<Notification> toAdd = newlyDiscovered.stream()
                .filter(n -> !intersect.contains(n.toContent()))
                .collect(Collectors.toList());

        log.debug("{} unread notifications to be deleted, while {} new notifications to be added.", toDelete.size(), toAdd.size());

        notificationRepo.deleteAll(toDelete);
        long ignored = toAdd.stream().filter(notification -> {
            try {
                notificationRepo.save(notification);
            } catch (DataIntegrityViolationException e) {
                return true;
            }
            return false;
        }).count();

        log.debug("{} new notifications muted.", ignored);
    }



}
