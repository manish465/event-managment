package com.manish.content.service;

import com.manish.common.request.AddEventUpdateRequestDTO;
import com.manish.common.request.UpdateEventUpdateRequestDTO;
import com.manish.common.response.GeneralMessageResponseDTO;
import com.manish.common.response.GetEventUpdateResponseDTO;
import com.manish.content.entity.EventUpdate;
import com.manish.content.mapper.EventUpdateMapper;
import com.manish.content.repository.EventUpdateRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventUpdateService {
    private final EventUpdateRepository eventUpdateRepository;

    public GeneralMessageResponseDTO addEventUpdate(@Valid AddEventUpdateRequestDTO addEventUpdateRequestDTO) {
        log.info("Add Event update request received for event-data {}", addEventUpdateRequestDTO);

        eventUpdateRepository.save(EventUpdateMapper.toEntity(addEventUpdateRequestDTO));

        return new GeneralMessageResponseDTO("Event update added successfully");
    }

    public List<GetEventUpdateResponseDTO> getEventUpdate(String eventId) {
        log.info("Get Event update request received for event-id {}", eventId);

        return eventUpdateRepository.findByEventId(eventId).stream().map(EventUpdateMapper::toDto).toList();
    }

    public GeneralMessageResponseDTO updateEventUpdate(@Valid UpdateEventUpdateRequestDTO updateEventUpdateRequestDTO) {
        log.info("Update Event update request received for event-data {}", updateEventUpdateRequestDTO);

        Optional<EventUpdate> eventUpdateOptional = eventUpdateRepository.findById(updateEventUpdateRequestDTO.getId());

        if (eventUpdateOptional.isEmpty())
            return new GeneralMessageResponseDTO("Event update not found");

        eventUpdateOptional.get().setUpdateText(updateEventUpdateRequestDTO.getUpdateText());
        eventUpdateRepository.save(eventUpdateOptional.get());

        return new GeneralMessageResponseDTO("Event update updated successfully");

    }

    public GeneralMessageResponseDTO deleteEventUpdate(String updateId) {
        log.info("Delete Event update request received for update-id {}", updateId);

        Optional<EventUpdate> eventUpdateOptional = eventUpdateRepository.findById(updateId);
        if (eventUpdateOptional.isEmpty())
            return new GeneralMessageResponseDTO("Event update not found");

        eventUpdateRepository.delete(eventUpdateOptional.get());

        return new GeneralMessageResponseDTO("Event update deleted successfully");
    }

    public GeneralMessageResponseDTO deleteEventUpdateByEventId(String eventId) {
        log.info("Delete Event update request received for event-id {}", eventId);

        eventUpdateRepository.deleteByEventId(eventId);

        return new GeneralMessageResponseDTO("Event update deleted successfully");
    }

    public GeneralMessageResponseDTO deleteAllEventUpdate() {
        log.info("Delete All Event update request received");

        eventUpdateRepository.deleteAll();

        return new GeneralMessageResponseDTO("All event update deleted successfully");
    }
}
