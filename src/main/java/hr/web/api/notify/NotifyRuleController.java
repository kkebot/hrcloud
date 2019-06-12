package hr.web.api.notify;

import hr.data.notify.NotificationRepository;
import hr.data.notify.NotifyRuleRepository;
import hr.domain.notify.Notification;
import hr.domain.notify.NotifyRule;
import hr.web.api.base.SingleEntityQuery;
import hr.web.api.exception.BadRequest;
import hr.web.api.exception.Conflict;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/rule")
public class NotifyRuleController extends SingleEntityQuery<NotifyRule, Long, NotifyRuleRepository> {
    private Pattern findSelectExpression = Pattern.compile("SELECT\\s+(DISTINCT\\s+|)(.*?)\\s+FROM");

    private NotificationController notificationController;
    private NotificationRepository notificationRepo;

    public NotifyRuleController(NotifyRuleRepository notifyRuleRepo, NotificationController notificationController, NotificationRepository notificationRepo) {
        super(notifyRuleRepo);
        this.notificationController = notificationController;
        this.notificationRepo = notificationRepo;
    }

    @PostMapping("/register")
    public NotifyRule register(@Validated NotifyRule model) {
        String sql = model.getSqlStatement();
        if (!sql.startsWith("SELECT"))
            throw new BadRequest("无效规则");
        if (getRepo().existsBySqlStatementAndStatus(sql, true))
            throw new Conflict("规则已存在");
//        Matcher matcher = findSelectExpression.matcher(sql);
//        matcher.groupCount()
        NotifyRule rule;
        try {
            Collection<Notification> notifications = notificationController.scan(model).collect(Collectors.toList());
            rule = getRepo().save(model);
            notificationRepo.saveAll(notifications);
            return rule;
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new BadRequest("请检查规则所包含的 SQL 语句");
        }

    }

    @GetMapping
    public Page<NotifyRule> getRules(@RequestParam(required = false) Boolean status, Pageable pageable) {
        return getRepo().findByStatusOrderByCreatedAtDesc(status, pageable);
    }

    @Transactional
    @PostMapping("/unregister")
    public void unregister(@RequestParam Long ruleId) {
        NotifyRule rule = getEx(ruleId);
        rule.setStatus(false);
        getRepo().save(rule);
//        int count = notificationRepo.deleteAllByIsReadIsFalseAndRule(rule);
//        log.debug("{} notifications deleted.", count);
    }

    @GetMapping("/{ruleId}")
    public NotifyRule getRule(@PathVariable Long ruleId) {
        return get(ruleId);
    }

}
