package com.manish.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignUpRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String bio;
    private List<String> roles;
}
