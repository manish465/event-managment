package com.manish.common.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequestDTO {
    @NotNull(message = "User id is required")
    @NotBlank(message = "User id is required")
    private String userID;
    @NotNull(message = "First name is required")
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    private String bio;
    @Valid
    @NotEmpty(message = "Roles are required")
    private List<String> roles;
    private MultipartFile profilePicture;
}
