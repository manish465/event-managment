package com.manish.content.mapper;

import com.manish.common.request.AddEventReviewRequestDTO;
import com.manish.common.response.GetEventReviewResponseDTO;
import com.manish.content.entity.EventReviewEntity;

public class EventReviewMapper {
    public static EventReviewEntity toEntity(AddEventReviewRequestDTO addEventReviewRequestDTO) {
        return EventReviewEntity.builder()
                .eventId(addEventReviewRequestDTO.getEventId())
                .userId(addEventReviewRequestDTO.getUserId())
                .rating(addEventReviewRequestDTO.getRating())
                .reviewText(addEventReviewRequestDTO.getReviewText())
                .build();
    }

    public static GetEventReviewResponseDTO toDto(EventReviewEntity eventReviewEntity) {
        return GetEventReviewResponseDTO.builder()
                .id(eventReviewEntity.getId())
                .eventId(eventReviewEntity.getEventId())
                .userId(eventReviewEntity.getUserId())
                .rating(eventReviewEntity.getRating())
                .reviewText(eventReviewEntity.getReviewText())
                .createdAt(eventReviewEntity.getCreatedAt())
                .updatedAt(eventReviewEntity.getUpdatedAt())
                .build();
    }


}
