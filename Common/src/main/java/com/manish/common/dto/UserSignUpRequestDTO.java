package com.manish.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
    private MultipartFile profilePicture;
}
