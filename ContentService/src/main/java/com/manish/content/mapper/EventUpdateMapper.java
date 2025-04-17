package com.manish.content.mapper;

import com.manish.common.request.AddEventUpdateRequestDTO;
import com.manish.common.response.GetEventUpdateResponseDTO;
import com.manish.content.entity.EventUpdateEntity;

public class EventUpdateMapper {
    public static EventUpdateEntity toEntity(AddEventUpdateRequestDTO addEventUpdateRequestDTO) {
        return EventUpdateEntity.builder()
                .eventId(addEventUpdateRequestDTO.getEventId())
                .userId(addEventUpdateRequestDTO.getUserId())
                .updateText(addEventUpdateRequestDTO.getUpdateText())
                .build();
    }

    public static GetEventUpdateResponseDTO toDto(EventUpdateEntity eventUpdateEntity) {
        return GetEventUpdateResponseDTO.builder()
                .id(eventUpdateEntity.getId())
                .eventId(eventUpdateEntity.getEventId())
                .userId(eventUpdateEntity.getUserId())
                .updateText(eventUpdateEntity.getUpdateText())
                .createdAt(eventUpdateEntity.getCreatedAt())
                .updatedAt(eventUpdateEntity.getUpdatedAt())
                .build();
    }
}
