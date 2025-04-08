package com.manish.common.request;

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
    private String organizerId;
    private String eventName;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDatetime;
    private LocalDateTime bookingStartDatetime;
    private LocalDateTime bookingEndDatetime;
    private Integer maxCapacity;
    private List<MultipartFile> eventImages;
}
