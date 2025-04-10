package com.manish.common.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddEventRequestDTO {
    @NotNull(message = "Organizer id is required")
    @NotBlank(message = "Organizer id is required")
    private String organizerId;
    @NotNull(message = "Event name is required")
    @NotBlank(message = "Event name is required")
    private String eventName;
    private String description;
    @NotNull(message = "Start date time is required")
    private LocalDateTime startDateTime;
    @NotNull(message = "End date time is required")
    private LocalDateTime endDatetime;
    @NotNull(message = "Booking start date time is required")
    private LocalDateTime bookingStartDatetime;
    @NotNull(message = "Booking end date time is required")
    private LocalDateTime bookingEndDatetime;
    @NotNull(message = "Max capacity is required")
    @Positive(message = "Max Capacity should be more then zero")
    private Integer maxCapacity;
    private List<MultipartFile> eventImages;
}
