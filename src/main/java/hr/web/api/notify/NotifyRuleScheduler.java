package hr.web.api.notify;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotifyRuleScheduler {

    private NotificationController notificationController;

    public NotifyRuleScheduler(NotificationController notificationController) {
        this.notificationController = notificationController;
    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void task() {
        log.debug("Scanning notifications...");
        this.notificationController.scanAll();
    }
}
