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
public class GetEventUpdateResponseDTO {
    private String id;
    private String eventId;
    private String userId;
    private String updateText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
