package com.apps.service;

import com.apps.dto.OrderMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "messaging.broker", havingValue = "activemq")
public class ActiveMqMessagingService implements MessagingService {

    private final JmsTemplate jmsTemplate;

    public ActiveMqMessagingService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void publish(String destination, String message) {

        System.out.println("<< working 1 >>");

        OrderMessage msg = new OrderMessage();
        msg.setOrderId("ORD-101");
        msg.setProduct("Laptop");
        msg.setQuantity(2);

        jmsTemplate.convertAndSend(destination, message);
    }

}
