package com.manish.auth.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class GeneralFailResponseDTO {
    private String error;
}
