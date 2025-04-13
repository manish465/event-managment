package com.manish.common.request;

import jakarta.validation.constraints.Min;
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
public class AddEventReviewRequestDTO {
    @NotNull(message = "Event id is required")
    @NotBlank(message = "Event id is required")
    private String eventId;
    @NotNull(message = "User id is required")
    @NotBlank(message = "User id is required")
    private String userId;
    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating should be more then zero")
    @Min(value = 11, message = "Rating should be less then or equal to eleven")
    private Integer rating;
    private String reviewText;
}
