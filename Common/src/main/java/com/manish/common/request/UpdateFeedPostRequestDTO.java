package com.manish.common.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateFeedPostRequestDTO {
    @NotNull(message = "Feed post id is required")
    @NotBlank(message = "Feed post id is required")
    private String id;
    @NotNull(message = "User id is required")
    @NotBlank(message = "User id is required")
    private String userId;
    @NotNull(message = "Post Text is required")
    @NotBlank(message = "Post Text is required")
    private String postText;
}
