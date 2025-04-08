package com.manish.common.request;

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
    private String userID;
    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private List<String> roles;
    private MultipartFile profilePicture;
}
