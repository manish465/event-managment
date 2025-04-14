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
public class UpdateEventUpdateRequestDTO {
    @NotNull(message = "Event update id is required")
    @NotBlank(message = "Event update id is required")
    private String id;
    @NotNull(message = "User text is required")
    @NotBlank(message = "User text is required")
    private String updateText;
}
