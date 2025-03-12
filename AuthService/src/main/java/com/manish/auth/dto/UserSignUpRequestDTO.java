package com.manish.auth.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserSignUpRequestDTO {
    private String name;
    private String email;
    private String password;
    private List<String> roles;
}