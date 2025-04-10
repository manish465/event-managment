package com.manish.event.service;

import com.manish.common.response.GeneralMessageResponseDTO;
import com.manish.common.request.AddEventRequestDTO;
import com.manish.common.response.GetEventResponseDTO;
import com.manish.common.request.UpdateEventRequestDTO;
import com.manish.event.entity.EventEntity;
import com.manish.event.exception.ApplicationException;
import com.manish.event.mapper.EventMapper;
import com.manish.event.repository.EventRepository;
import com.manish.event.utils.CompareDateTimeUtils;
import com.manish.event.utils.CompareStringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public GeneralMessageResponseDTO addEvent(AddEventRequestDTO addEventRequestDTO) {
        log.info("Add Event request received for user-data {}", addEventRequestDTO);

        if(CompareStringUtils.isStingEmpty(addEventRequestDTO.getOrganizerId()))
            throw new ApplicationException("Organizer id is required");

        if(CompareStringUtils.isStingEmpty(addEventRequestDTO.getEventName()))
            throw new ApplicationException("Event name id is required");

        if(CompareDateTimeUtils.isDateTimeEmpty(addEventRequestDTO.getStartDateTime()))
            throw new ApplicationException("Start date time is required");
        if(CompareDateTimeUtils.isDateTimeEmpty(addEventRequestDTO.getEndDatetime()))
            throw new ApplicationException("End date time is required");
        if(CompareDateTimeUtils.isDateTimeBefore(addEventRequestDTO.getStartDateTime(), addEventRequestDTO.getEndDatetime()))
            throw new ApplicationException("Start date time should be before end date time");

        if(CompareDateTimeUtils.isDateTimeEmpty(addEventRequestDTO.getBookingStartDatetime()))
            throw new ApplicationException("Booking start date time is required");
        if(CompareDateTimeUtils.isDateTimeEmpty(addEventRequestDTO.getBookingEndDatetime()))
            throw new ApplicationException("Booking end date time is required");
        if(CompareDateTimeUtils.isDateTimeBefore(
                addEventRequestDTO.getBookingStartDatetime(),
                addEventRequestDTO.getBookingEndDatetime()))
            throw new ApplicationException("Booking start date time should be before booking end date time");

        if(CompareDateTimeUtils.isDateTimeBefore(
                addEventRequestDTO.getBookingEndDatetime(),
                addEventRequestDTO.getStartDateTime()))
            throw new ApplicationException("Booking end date time should be before start date time");

        if(addEventRequestDTO.getMaxCapacity() <= 0) throw new ApplicationException("Max capacity is required");

        List<String> eventImagesURL = List.of("https://modii.org/wp-content/uploads/2020/12/random.png"); // TODO :: call picture service and get picture url string
        eventRepository.save(EventMapper.toEntity(addEventRequestDTO, eventImagesURL));

        return new GeneralMessageResponseDTO("Event added successfully");
    }

    public List<GetEventResponseDTO> getEvent(List<String> eventIds) {
        log.info("Get Event request received for event-ids {}", eventIds);
        return eventRepository.findAllById(eventIds).stream().map(EventMapper::toDto).toList();
    }

    public GeneralMessageResponseDTO updateEvent(UpdateEventRequestDTO updateEventRequestDTO) {
        log.info("Update Event request received for user-data {}", updateEventRequestDTO);

        Optional<EventEntity> optionalEventEntity = eventRepository.findById(updateEventRequestDTO.getId());

        if(optionalEventEntity.isEmpty()) throw new ApplicationException("Event not found");

        if(CompareStringUtils.isStingEmpty(updateEventRequestDTO.getEventName()))
            throw new ApplicationException("Event name id is required");

        if(CompareDateTimeUtils.isDateTimeEmpty(updateEventRequestDTO.getStartDateTime()))
            throw new ApplicationException("Start date time is required");
        if(CompareDateTimeUtils.isDateTimeEmpty(updateEventRequestDTO.getEndDateTime()))
            throw new ApplicationException("End date time is required");
        if(CompareDateTimeUtils.isDateTimeBefore(updateEventRequestDTO.getStartDateTime(), updateEventRequestDTO.getEndDateTime()))
            throw new ApplicationException("Start date time should be before end date time");

        if(CompareDateTimeUtils.isDateTimeEmpty(updateEventRequestDTO.getBookingStartDateTime()))
            throw new ApplicationException("Booking start date time is required");
        if(CompareDateTimeUtils.isDateTimeEmpty(updateEventRequestDTO.getBookingEndDateTime()))
            throw new ApplicationException("Booking end date time is required");
        if(CompareDateTimeUtils.isDateTimeBefore(
                updateEventRequestDTO.getBookingStartDateTime(),
                updateEventRequestDTO.getBookingEndDateTime()))
            throw new ApplicationException("Booking start date time should be before booking end date time");

        if(CompareDateTimeUtils.isDateTimeBefore(
                updateEventRequestDTO.getBookingEndDateTime(),
                updateEventRequestDTO.getStartDateTime()))
            throw new ApplicationException("Booking end date time should be before start date time");

        if(updateEventRequestDTO.getMaxCapacity() <= 0) throw new ApplicationException("Max capacity is required");

        List<String> eventImagesURL = List.of("https://modii.org/wp-content/uploads/2020/12/random.png"); // TODO :: call picture service and get picture url string

        eventRepository.save(EventMapper.toEntity(optionalEventEntity.get(), updateEventRequestDTO, eventImagesURL));

        return new GeneralMessageResponseDTO("Event updated successfully");
    }

    public GeneralMessageResponseDTO deleteEvent(List<String> eventIds) {
        log.info("Delete Event request received for event-ids {}", eventIds);

        eventRepository.deleteAllById(eventIds);

        return new GeneralMessageResponseDTO("Event deleted successfully");
    }

    public GeneralMessageResponseDTO deleteAllEvent() {
        log.info("Delete All Event request received");

        eventRepository.deleteAll();

        return new GeneralMessageResponseDTO("All event deleted successfully");
    }

    public Boolean checkEventsExist(List<String> eventIds) {
        log.info("Check Event exist request received for event-ids {}", eventIds);
        return eventRepository.countExistingIds(eventIds) == eventIds.size();
    }
}
