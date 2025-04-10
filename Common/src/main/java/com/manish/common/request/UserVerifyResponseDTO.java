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
public class UserVerifyResponseDTO {
    @NotNull(message = "User id is required")
    @NotBlank(message = "User id is required")
    private String userID;
}
