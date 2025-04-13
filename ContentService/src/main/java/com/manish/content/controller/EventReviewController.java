package com.manish.content.controller;

import com.manish.common.request.AddEventReviewRequestDTO;
import com.manish.common.request.UpdateEventReviewRequestDTO;
import com.manish.common.response.GeneralMessageResponseDTO;
import com.manish.common.response.GetEventReviewResponseDTO;
import com.manish.content.service.EventReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
public class EventReviewController {
    private final EventReviewService eventReviewService;

    @PostMapping("/api/v1/event-review/add-event-review")
    public ResponseEntity<GeneralMessageResponseDTO> addEventReview(@RequestBody @Valid AddEventReviewRequestDTO addEventReviewRequestDTO) {
        log.info("Add Event review request received for event-data {}", addEventReviewRequestDTO);
        return ResponseEntity.ok(eventReviewService.addEventReview(addEventReviewRequestDTO));
    }

    @GetMapping("api/v1/event-review/get-event-review")
    public ResponseEntity<List<GetEventReviewResponseDTO>> getEventReview(@RequestParam String eventId) {
        log.info("Get Event review request received for event-id {}", eventId);
        return ResponseEntity.ok(eventReviewService.getEventReview(eventId));
    }

    @PutMapping("api/v1/event-review/update-event-review")
    public ResponseEntity<GeneralMessageResponseDTO> updateEventReview(@RequestBody @Valid UpdateEventReviewRequestDTO updateEventReviewRequestDTO) {
        log.info("Update Event review request received for event-data {}", updateEventReviewRequestDTO);
        return ResponseEntity.ok(eventReviewService.updateEventReview(updateEventReviewRequestDTO));
    }

    @DeleteMapping("api/v1/event-review/delete-event-review")
    public ResponseEntity<GeneralMessageResponseDTO> deleteEventReview(@RequestParam String reviewId) {
        log.info("Delete Event review request received for review-id {}", reviewId);
        return ResponseEntity.ok(eventReviewService.deleteEventReview(reviewId));
    }

    @DeleteMapping("api/v1/event-review/delete-all-event-review")
    public ResponseEntity<GeneralMessageResponseDTO> deleteAllEventReview() {
        log.info("Delete All Event review request received");
        return ResponseEntity.ok(eventReviewService.deleteAllEventReview());
    }

    @GetMapping("api/v1/event-review/health-check")
    public ResponseEntity<GeneralMessageResponseDTO> healthCheck() {
        log.info("Event Review health check request received");
        return new ResponseEntity<>(new GeneralMessageResponseDTO("Event Review service is up and running"), HttpStatus.OK);
    }
}
