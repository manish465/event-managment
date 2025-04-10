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
public class UserAccessRequestDTO {
    @NotNull(message = "User role is required")
    @NotBlank(message = "User role is required")
    private String role;
    @NotNull(message = "path is required")
    @NotBlank(message = "path is required")
    private String path;
}
