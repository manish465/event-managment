package com.manish.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserSignUpDTO {
    private String name;
    private String email;
    private String password;
    private List<String> roles;
}