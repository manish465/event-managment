package com.manish.event.service;

import com.manish.common.dto.GeneralMessageResponseDTO;
import com.manish.common.dto.AddBookingEventDTO;
import com.manish.common.dto.GetBookingResponseDTO;
import com.manish.event.entity.BookingEntity;
import com.manish.common.enums.BookingStatus;
import com.manish.event.exception.ApplicationException;
import com.manish.event.mapper.BookingMapper;
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

        bookingRepository.save(BookingMapper.toEntity(addBookingEventDTO));

        return new GeneralMessageResponseDTO("Event booked successfully");
    }

    public GeneralMessageResponseDTO cancelEventBooking(String bookingId) {
        log.info("Cancel Event request received for booking-id {}", bookingId);

        bookingRepository.deleteById(bookingId);

        return new GeneralMessageResponseDTO("Event booking cancelled successfully");
    }

    public List<GetBookingResponseDTO> getEventBooking(String eventId) {
        log.info("Get Event booking request received for event-id {}", eventId);

        return bookingRepository.findAllByEventId(eventId).stream().map(BookingMapper::toDto).toList();
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
