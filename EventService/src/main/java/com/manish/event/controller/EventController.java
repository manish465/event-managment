package com.manish.event.controller;

import com.manish.common.response.GeneralMessageResponseDTO;
import com.manish.common.request.AddEventRequestDTO;
import com.manish.common.response.GetEventResponseDTO;
import com.manish.common.request.UpdateEventRequestDTO;
import com.manish.event.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EventController {
    private final EventService eventService;

    @PostMapping("/api/v1/event/add-event")
    public ResponseEntity<GeneralMessageResponseDTO> addEvent(@ModelAttribute AddEventRequestDTO addEventRequestDTO) {
        log.info("Add Event request received for user-data {}", addEventRequestDTO);
        return new ResponseEntity<>(eventService.addEvent(addEventRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/event/get-event")
    public ResponseEntity<List<GetEventResponseDTO>> getEvent(@RequestBody List<String> eventIds) {
        log.info("Get Event request received for event-ids {}", eventIds);
        return new ResponseEntity<>(eventService.getEvent(eventIds), HttpStatus.OK);
    }

    @PutMapping("/api/v1/event/update-event")
    public ResponseEntity<GeneralMessageResponseDTO> updateEvent(@ModelAttribute UpdateEventRequestDTO updateEventRequestDTO) {
        log.info("Update Event request received for user-data {}", updateEventRequestDTO);
        return new ResponseEntity<>(eventService.updateEvent(updateEventRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/event/delete-event")
    public ResponseEntity<GeneralMessageResponseDTO> deleteEvent(@RequestBody List<String> eventIds) {
        log.info("Delete Event request received for event-ids {}", eventIds);
        return new ResponseEntity<>(eventService.deleteEvent(eventIds), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/event/delete-all-event")
    public ResponseEntity<GeneralMessageResponseDTO> deleteAllEvent() {
        log.info("Delete All Event request received");
        return new ResponseEntity<>(eventService.deleteAllEvent(), HttpStatus.OK);
    }

    @GetMapping("/api/v1/event/check-event-exist")
    public ResponseEntity<Boolean> checkEventsExist(List<String> eventIds) {
        log.info("Check Event exist request received for event-ids {}", eventIds);
        return new ResponseEntity<>(eventService.checkEventsExist(eventIds), HttpStatus.OK);
    }

    @GetMapping("/api/v1/event/health-check")
    public ResponseEntity<GeneralMessageResponseDTO> healthCheck() {
        log.info("Event health check request received");
        return new ResponseEntity<>(new GeneralMessageResponseDTO("Event service is up and running"), HttpStatus.OK);
    }
}
