package com.manish.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetEventResponseDTO {
    private String id;
    private String organizerId;
    private String eventName;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDatetime;
    private LocalDateTime bookingStartDatetime;
    private LocalDateTime bookingEndDatetime;
    private Integer maxCapacity;
    private List<String> eventImages;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
