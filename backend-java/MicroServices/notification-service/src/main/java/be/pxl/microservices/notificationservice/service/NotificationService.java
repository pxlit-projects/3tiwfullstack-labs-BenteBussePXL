package be.pxl.microservices.notificationservice.service;

import be.pxl.microservices.notificationservice.domain.Notification;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class NotificationService {

    public void sendMessage(Notification notification) {
        log.info("Receiving Message...");
        log.info("Sending... {}", notification.getMessage());
        log.info("TO... {}", notification.getSender());
    }
}
