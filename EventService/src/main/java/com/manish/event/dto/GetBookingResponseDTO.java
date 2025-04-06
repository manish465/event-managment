package com.manish.event.dto;

import com.manish.event.entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetBookingResponseDTO {
    private String id;
    private String eventId;
    private String userId;
    private BookingStatus bookingStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
