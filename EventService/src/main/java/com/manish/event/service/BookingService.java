package com.manish.event.service;

import com.manish.event.dto.AddBookingEventDTO;
import com.manish.event.dto.GeneralMessageResponseDTO;
import com.manish.event.dto.GetBookingResponseDTO;
import com.manish.event.entity.BookingEntity;
import com.manish.event.entity.BookingStatus;
import com.manish.event.exception.ApplicationException;
import com.manish.event.repository.BookingRepository;
import com.manish.event.utils.CompareStringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;

    public GeneralMessageResponseDTO addEventBooking(AddBookingEventDTO addBookingEventDTO) {
        log.info("Book Event request received for user-data {}", addBookingEventDTO);

        if(CompareStringUtils.isStingEmpty(addBookingEventDTO.getUserId()))
            throw new ApplicationException("User id is required");

        if(CompareStringUtils.isStingEmpty(addBookingEventDTO.getEventId()))
            throw new ApplicationException("Event id is required");

        bookingRepository.save(
                BookingEntity.builder()
                        .eventId(addBookingEventDTO.getEventId())
                        .userId(addBookingEventDTO.getUserId())
                        .bookingStatus(BookingStatus.CONFIRMED)
                        .build()
        );

        return new GeneralMessageResponseDTO("Event booked successfully");
    }

    public GeneralMessageResponseDTO cancelEventBooking(String bookingId) {
        log.info("Cancel Event request received for booking-id {}", bookingId);

        bookingRepository.deleteById(bookingId);

        return new GeneralMessageResponseDTO("Event booking cancelled successfully");
    }

    public List<GetBookingResponseDTO> getEventBooking(String eventId) {
        log.info("Get Event booking request received for event-id {}", eventId);

        return bookingRepository.findAllByEventId(eventId).stream().map(
                booking -> GetBookingResponseDTO.builder()
                        .id(booking.getId())
                        .eventId(booking.getEventId())
                        .userId(booking.getUserId())
                        .bookingStatus(booking.getBookingStatus())
                        .createdAt(booking.getCreatedAt())
                        .updatedAt(booking.getUpdatedAt())
                        .build()
        ).toList();
    }

    public GeneralMessageResponseDTO deleteAllEventsByEventId(String eventId) {
        log.info("Delete All Event booking request received for event-id {}", eventId);

        bookingRepository.deleteByEventId(eventId);

        return new GeneralMessageResponseDTO("All event booking deleted successfully");
    }

    public GeneralMessageResponseDTO deleteAllEventBooking() {
        log.info("Delete All Event booking request received");

        bookingRepository.deleteAll();

        return new GeneralMessageResponseDTO("All event booking deleted successfully");
    }

}
