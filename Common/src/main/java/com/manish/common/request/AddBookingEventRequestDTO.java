package com.manish.common.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddBookingEventRequestDTO {
    @NotNull(message = "Event id is required")
    @NotBlank(message = "Event id is required")
    private String eventId;
    @NotNull(message = "Event id is required")
    @NotBlank(message = "Event id is required")
    private String userId;
}
