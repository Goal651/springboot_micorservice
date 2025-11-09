package com.tutorial.notificationService.consumer;

import com.tutorial.notificationService.dto.UserEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserEventConsumer {

    @KafkaListener(topics = "user-events", groupId = "notification-service-group")
    public void consumeUserEvent(UserEvent event) {
        log.info("Received user event: {}", event);
        
        // Process the event based on type
        switch (event.getEventType()) {
            case "USER_CREATED":
                log.info("Processing USER_CREATED event for user: {}", event.getUserName());
                // Send welcome email, create notification, etc.
                break;
            case "USER_UPDATED":
                log.info("Processing USER_UPDATED event for user: {}", event.getUserName());
                // Send update notification
                break;
            case "USER_DELETED":
                log.info("Processing USER_DELETED event for user ID: {}", event.getUserId());
                // Clean up user data, send goodbye email, etc.
                break;
            default:
                log.warn("Unknown event type: {}", event.getEventType());
        }
    }
}