package com.manish.event.mapper;

import com.manish.common.dto.AddEventRequestDTO;
import com.manish.common.dto.GetEventResponseDTO;
import com.manish.common.dto.UpdateEventRequestDTO;
import com.manish.event.entity.EventEntity;

import java.util.ArrayList;
import java.util.List;

public class EventMapper {
    public static EventEntity toEntity(AddEventRequestDTO addEventRequestDTO) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setOrganizerId(addEventRequestDTO.getOrganizerId());
        eventEntity.setEventName(addEventRequestDTO.getEventName());
        eventEntity.setDescription(addEventRequestDTO.getDescription());
        eventEntity.setStartDateTime(addEventRequestDTO.getStartDateTime());
        eventEntity.setEndDateTime(addEventRequestDTO.getEndDatetime());
        eventEntity.setMaxCapacity(addEventRequestDTO.getMaxCapacity());
        eventEntity.setBookingStartDateTime(addEventRequestDTO.getBookingStartDatetime());
        eventEntity.setBookingEndDateTime(addEventRequestDTO.getBookingEndDatetime());
        eventEntity.setEventImages(new ArrayList<>());

        return eventEntity;
    }

    public static EventEntity toEntity(AddEventRequestDTO addEventRequestDTO, List<String> eventImagesURL) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setOrganizerId(addEventRequestDTO.getOrganizerId());
        eventEntity.setEventName(addEventRequestDTO.getEventName());
        eventEntity.setDescription(addEventRequestDTO.getDescription());
        eventEntity.setStartDateTime(addEventRequestDTO.getStartDateTime());
        eventEntity.setEndDateTime(addEventRequestDTO.getEndDatetime());
        eventEntity.setMaxCapacity(addEventRequestDTO.getMaxCapacity());
        eventEntity.setBookingStartDateTime(addEventRequestDTO.getBookingStartDatetime());
        eventEntity.setBookingEndDateTime(addEventRequestDTO.getBookingEndDatetime());
        eventEntity.setEventImages(eventImagesURL);

        return eventEntity;
    }

    public static EventEntity toEntity(
            EventEntity eventEntity,
            UpdateEventRequestDTO updateEventRequestDTO,
            List<String> eventImagesURL
    ) {
        eventEntity.setEventName(updateEventRequestDTO.getEventName());
        eventEntity.setDescription(updateEventRequestDTO.getDescription());
        eventEntity.setStartDateTime(updateEventRequestDTO.getStartDateTime());
        eventEntity.setEndDateTime(updateEventRequestDTO.getEndDateTime());
        eventEntity.setMaxCapacity(updateEventRequestDTO.getMaxCapacity());
        eventEntity.setBookingStartDateTime(updateEventRequestDTO.getBookingStartDateTime());
        eventEntity.setBookingEndDateTime(updateEventRequestDTO.getBookingEndDateTime());
        eventEntity.setEventImages(eventImagesURL);

        return eventEntity;
    }

    public static EventEntity toEntity(UpdateEventRequestDTO updateEventRequestDTO) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventName(updateEventRequestDTO.getEventName());
        eventEntity.setDescription(updateEventRequestDTO.getDescription());
        eventEntity.setStartDateTime(updateEventRequestDTO.getStartDateTime());
        eventEntity.setEndDateTime(updateEventRequestDTO.getEndDateTime());
        eventEntity.setMaxCapacity(updateEventRequestDTO.getMaxCapacity());
        eventEntity.setBookingStartDateTime(updateEventRequestDTO.getBookingStartDateTime());
        eventEntity.setBookingEndDateTime(updateEventRequestDTO.getBookingEndDateTime());
        eventEntity.setEventImages(new ArrayList<>());

        return eventEntity;
    }

    public static GetEventResponseDTO toDto(EventEntity eventEntity) {
        GetEventResponseDTO getEventResponseDTO = new GetEventResponseDTO();
        getEventResponseDTO.setId(eventEntity.getId());
        getEventResponseDTO.setOrganizerId(eventEntity.getOrganizerId());
        getEventResponseDTO.setEventName(eventEntity.getEventName());
        getEventResponseDTO.setDescription(eventEntity.getDescription());
        getEventResponseDTO.setStartDateTime(eventEntity.getStartDateTime());
        getEventResponseDTO.setEndDatetime(eventEntity.getEndDateTime());
        getEventResponseDTO.setMaxCapacity(eventEntity.getMaxCapacity());
        getEventResponseDTO.setBookingStartDatetime(eventEntity.getBookingStartDateTime());
        getEventResponseDTO.setBookingEndDatetime(eventEntity.getBookingEndDateTime());
        getEventResponseDTO.setEventImages(eventEntity.getEventImages());
        getEventResponseDTO.setCreatedAt(eventEntity.getCreatedAt());
        getEventResponseDTO.setUpdatedAt(eventEntity.getUpdatedAt());

        return getEventResponseDTO;
    }
}
