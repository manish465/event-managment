package com.manish.content.mapper;

import com.manish.common.response.GetEventReviewResponseDTO;
import com.manish.content.entity.EventReview;

public class EventReviewMapper {
    public static GetEventReviewResponseDTO toDto(EventReview eventReview) {
        return GetEventReviewResponseDTO.builder()
                .id(eventReview.getId())
                .eventId(eventReview.getEventId())
                .userId(eventReview.getUserId())
                .rating(eventReview.getRating())
                .reviewText(eventReview.getReviewText())
                .createdAt(eventReview.getCreatedAt())
                .updatedAt(eventReview.getUpdatedAt())
                .build();
    }
}
