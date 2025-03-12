package com.manish.auth.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class UserLogInRequestDTO {
    private String email;
    private String password;
}