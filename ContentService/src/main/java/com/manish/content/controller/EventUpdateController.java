package com.manish.content.controller;

import com.manish.common.request.AddEventUpdateRequestDTO;
import com.manish.common.request.UpdateEventUpdateRequestDTO;
import com.manish.common.response.GeneralMessageResponseDTO;
import com.manish.common.response.GetEventUpdateResponseDTO;
import com.manish.content.service.EventUpdateService;
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
public class EventUpdateController {
    private final EventUpdateService eventUpdateService;

    @PostMapping("/api/v1/event-update/add-event-update")
    public ResponseEntity<GeneralMessageResponseDTO> addEventUpdate(@RequestBody @Valid AddEventUpdateRequestDTO addEventUpdateRequestDTO) {
        log.info("Add Event update request received for event-data {}", addEventUpdateRequestDTO);
        return new ResponseEntity<>(eventUpdateService.addEventUpdate(addEventUpdateRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("api/v1/event-update/get-event-update")
    public ResponseEntity<List<GetEventUpdateResponseDTO>> getEventUpdate(@RequestParam String eventId) {
        log.info("Get Event update request received for event-id {}", eventId);
        return ResponseEntity.ok(eventUpdateService.getEventUpdate(eventId));
    }

    @PutMapping("/api/v1/event-update/update-event-update")
    public ResponseEntity<GeneralMessageResponseDTO> updateEventUpdate(@RequestBody @Valid UpdateEventUpdateRequestDTO updateEventUpdateRequestDTO) {
        log.info("Update Event update request received for event-data {}", updateEventUpdateRequestDTO);
        return ResponseEntity.ok(eventUpdateService.updateEventUpdate(updateEventUpdateRequestDTO));
    }

    @DeleteMapping("api/v1/event-update/delete-event-update")
    public ResponseEntity<GeneralMessageResponseDTO> deleteEventUpdate(@RequestParam String updateId) {
        log.info("Delete Event update request received for update-id {}", updateId);
        return ResponseEntity.ok(eventUpdateService.deleteEventUpdate(updateId));
    }

    @DeleteMapping("api/v1/event-update/delete-event-update")
    public ResponseEntity<GeneralMessageResponseDTO> deleteEventUpdateByEventId(@RequestParam String eventId) {
        log.info("Delete Event update request received for event-id {}", eventId);
        return ResponseEntity.ok(eventUpdateService.deleteEventUpdateByEventId(eventId));
    }

    @DeleteMapping("api/v1/event-update/delete-all-event-update")
    public ResponseEntity<GeneralMessageResponseDTO> deleteAllEventUpdate() {
        log.info("Delete All Event update request received");
        return ResponseEntity.ok(eventUpdateService.deleteAllEventUpdate());
    }

    @GetMapping("api/v1/event-update/health-check")
    public ResponseEntity<GeneralMessageResponseDTO> healthCheck() {
        log.info("Event Update health check request received");
        return new ResponseEntity<>(new GeneralMessageResponseDTO("Event Update service is up and running"), HttpStatus.OK);
    }
}
