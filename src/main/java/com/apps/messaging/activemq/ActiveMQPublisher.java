package com.apps.messaging.activemq;

import com.apps.dto.OrderMessage;
import com.apps.messaging.MessagePublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("activemq")
public class ActiveMQPublisher implements MessagePublisher {

    @Value("${messaging.queue}")
    private String destQueueName;

    private final JmsTemplate jmsTemplate;

    public ActiveMQPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void publish(String message) {
        jmsTemplate.convertAndSend(destQueueName, message);
    }

    @Override
    public void publish(OrderMessage order) {
        // No need to manually convert to String!
        jmsTemplate.convertAndSend("order-queue", order);
        System.out.println("<<<<< PUBLISHED ORDER: " + order);
    }
}
