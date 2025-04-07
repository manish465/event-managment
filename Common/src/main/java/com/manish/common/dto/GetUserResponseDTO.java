package com.manish.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserResponseDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private String profilePicture;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> roles;
}
