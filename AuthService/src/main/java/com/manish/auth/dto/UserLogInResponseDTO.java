package com.manish.auth.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class UserLogInResponseDTO {
    private String accessToken;
    private String refreshToken;
}
