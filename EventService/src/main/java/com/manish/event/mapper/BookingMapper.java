package com.manish.event.mapper;

import com.manish.common.request.AddBookingEventRequestDTO;
import com.manish.common.response.GetBookingResponseDTO;
import com.manish.common.enums.BookingStatus;
import com.manish.event.entity.BookingEntity;

public class BookingMapper {
    public static BookingEntity toEntity(AddBookingEventRequestDTO addBookingEventRequestDTO) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setEventId(addBookingEventRequestDTO.getEventId());
        bookingEntity.setUserId(addBookingEventRequestDTO.getUserId());
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
