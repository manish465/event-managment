package com.manish.content.mapper;

import com.manish.common.request.AddEventUpdateRequestDTO;
import com.manish.common.response.GetEventUpdateResponseDTO;
import com.manish.content.entity.EventUpdate;

public class EventUpdateMapper {
    public static EventUpdate toEntity(AddEventUpdateRequestDTO addEventUpdateRequestDTO) {
        return EventUpdate.builder()
                .eventId(addEventUpdateRequestDTO.getEventId())
                .userId(addEventUpdateRequestDTO.getUserId())
                .updateText(addEventUpdateRequestDTO.getUpdateText())
                .build();
    }

    public static GetEventUpdateResponseDTO toDto(EventUpdate eventUpdate) {
        return GetEventUpdateResponseDTO.builder()
                .id(eventUpdate.getId())
                .eventId(eventUpdate.getEventId())
                .userId(eventUpdate.getUserId())
                .updateText(eventUpdate.getUpdateText())
                .createdAt(eventUpdate.getCreatedAt())
                .updatedAt(eventUpdate.getUpdatedAt())
                .build();
    }
}
