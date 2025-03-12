package com.manish.auth.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class UserTokenRefreshRequestDTO {
    private String refreshToken;
}
