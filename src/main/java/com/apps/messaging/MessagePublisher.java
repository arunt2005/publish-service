package com.apps.messaging;

import com.apps.dto.OrderMessage;

public interface MessagePublisher {
    void publish(String message);

    void publish(OrderMessage message);
}


