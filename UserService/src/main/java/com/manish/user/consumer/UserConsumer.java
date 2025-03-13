package com.manish.user.consumer;

import com.manish.user.dto.UserSignUpRequestDTO;
import com.manish.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserConsumer {
    private final UserService userService;

    @KafkaListener(topics = "user-signup-topic", groupId = "user-signup-group")
    public void consumeUserSignUp(ConsumerRecord<String, UserSignUpRequestDTO> record) {
        try {
            log.info("Received User Sign-Up Request: {}", record.value());
            userService.addUsers(record.value());
            log.info("User sign-up processed successfully.");
        } catch (Exception e) {
            log.error("Error processing user sign-up: {}", e.getMessage(), e);
        }
    }
}
