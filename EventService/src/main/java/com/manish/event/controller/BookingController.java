package com.manish.event.controller;

import com.manish.event.dto.AddBookingEventDTO;
import com.manish.event.dto.GeneralMessageResponseDTO;
import com.manish.event.dto.GetBookingResponseDTO;
import com.manish.event.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/api/v1/booking/add-event-booking")
    public ResponseEntity<GeneralMessageResponseDTO> addEventBooking(@RequestBody AddBookingEventDTO addBookingEventDTO) {
        log.info("Book Event request received for user-data {}", addBookingEventDTO);
        return ResponseEntity.ok(bookingService.addEventBooking(addBookingEventDTO));
    }

    @PutMapping("/api/v1/booking/cancel-event-booking")
    public ResponseEntity<GeneralMessageResponseDTO> cancelEventBooking(@RequestParam(name = "booking-id") String bookingId) {
        log.info("Cancel Event request received for booking-id {}", bookingId);
        return ResponseEntity.ok(bookingService.cancelEventBooking(bookingId));
    }

    @GetMapping("/api/v1/booking/get-event-booking")
    public ResponseEntity<List<GetBookingResponseDTO>> getEventBooking(@RequestParam(name = "event-id") String eventId) {
        log.info("Get Event booking request received for event-id {}", eventId);
        return new ResponseEntity<>(bookingService.getEventBooking(eventId), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/booking/delete-all-event-booking")
    public ResponseEntity<GeneralMessageResponseDTO> deleteAllEventsByEventId(@RequestParam(name = "event-id") String eventId) {
        log.info("Delete All Event booking request received for event-id {}", eventId);
        return new ResponseEntity<>(bookingService.deleteAllEventsByEventId(eventId), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/booking/delete-all-event-booking")
    public ResponseEntity<GeneralMessageResponseDTO> deleteAllEventBooking() {
        log.info("Delete All Event booking request received");
        return new ResponseEntity<>(bookingService.deleteAllEventBooking(), HttpStatus.OK);
    }

    public ResponseEntity<GeneralMessageResponseDTO> healthCheck() {
        log.info("Booking health check request received");
        return new ResponseEntity<>(new GeneralMessageResponseDTO("Booking service is up and running"), HttpStatus.OK);
    }
}
