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
public class AddEventUpdateRequestDTO {
    @NotNull(message = "Event id is required")
    @NotBlank(message = "Event id is required")
    private String eventId;
    @NotNull(message = "User id is required")
    @NotBlank(message = "User id is required")
    private String userId;
    @NotNull(message = "Update text is required")
    @NotBlank(message = "Update text is required")
    private String updateText;
}
