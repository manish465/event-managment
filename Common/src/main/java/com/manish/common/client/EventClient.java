package com.manish.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "event-service", url = "http://localhost:8081")
public interface EventClient {
    @GetMapping("/api/v1/event/check-event-exist")
    ResponseEntity<Boolean> checkEventsExist(List<String> eventIds);
}
