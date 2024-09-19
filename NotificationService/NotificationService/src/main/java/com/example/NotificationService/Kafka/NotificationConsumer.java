package com.example.NotificationService.Kafka;


import com.example.base_domain1.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);


    @KafkaListener(topics = "order_notifications", groupId = "notification-group")
    public void consume(OrderEvent orderEvent) {
        LOGGER.info(String.format("Received order event: %s", orderEvent.toString()));

        if (orderEvent.getOrder().getRegion().equalsIgnoreCase("North")) {
            LOGGER.info("Sending notification: Chapati-Bhaji is ordered for you");
        } else if (orderEvent.getOrder().getRegion().equalsIgnoreCase("South")) {
            LOGGER.info("Sending notification: Biryani is ordered for you");
        } else {
            LOGGER.info("Sending default notification for the order");
        }
    }
}
