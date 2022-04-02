package pl.put.CinemaManagement.order.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {
    private final KafkaTemplate<String, NotificationRequest> template;

    @Value("${kafka.notification.topic}")
    private String notificationTopic;

    public void sendNotification(NotificationRequest request) {
        template.send(notificationTopic, request);
    }
}
