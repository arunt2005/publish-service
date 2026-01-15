package com.apps.controller;

import com.apps.dto.ApiResponse;
import com.apps.dto.OrderMessage;
import com.apps.messaging.MessagePublisher;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class PublishController {

    private final MessagePublisher publisher;

    public PublishController(MessagePublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping("/publish")
    public ResponseEntity<ApiResponse> publish(
            @Valid @RequestBody OrderMessage request) {
        // publisher.publish(request.getMessage());
        publisher.publish(request);
        return ResponseEntity.ok(
                new ApiResponse("SUCCESS", "Order sent to queue")
        );
    }

    @PostMapping("/test/publish")
    public ResponseEntity<ApiResponse> publish(@RequestBody String message) {
        System.out.println("<<< calling..... incoming message :: " + message);
        publisher.publish(message);
        return ResponseEntity.ok(
                new ApiResponse("SUCCESS", "For testing purpose.")
        );
    }
}