package com.manish.event.mapper;

import com.manish.common.dto.AddBookingEventDTO;
import com.manish.common.dto.GetBookingResponseDTO;
import com.manish.common.enums.BookingStatus;
import com.manish.event.entity.BookingEntity;

public class BookingMapper {
    public static BookingEntity toEntity(AddBookingEventDTO addBookingEventDTO) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setEventId(addBookingEventDTO.getEventId());
        bookingEntity.setUserId(addBookingEventDTO.getUserId());
        bookingEntity.setBookingStatus(BookingStatus.CONFIRMED);

        return bookingEntity;
    }

    public static GetBookingResponseDTO toDto(BookingEntity bookingEntity) {
        GetBookingResponseDTO getBookingResponseDTO = new GetBookingResponseDTO();
        getBookingResponseDTO.setId(bookingEntity.getId());
        getBookingResponseDTO.setEventId(bookingEntity.getEventId());
        getBookingResponseDTO.setUserId(bookingEntity.getUserId());
        getBookingResponseDTO.setBookingStatus(bookingEntity.getBookingStatus());
        getBookingResponseDTO.setCreatedAt(bookingEntity.getCreatedAt());
        getBookingResponseDTO.setUpdatedAt(bookingEntity.getUpdatedAt());

        return getBookingResponseDTO;
    }
}
