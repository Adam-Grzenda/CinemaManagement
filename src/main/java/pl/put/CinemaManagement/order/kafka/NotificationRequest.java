package pl.put.CinemaManagement.order.kafka;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationRequest {
    private NotificationType type;

    private String recipient;
    private String subject;
    private String body;

    public enum NotificationType {
        email
    }
}
