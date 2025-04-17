package com.manish.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetFeedPostResponseDTO {
    private String id;
    private String userId;
    private String postText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
