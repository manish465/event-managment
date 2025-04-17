package com.manish.content.service;

import com.manish.common.request.AddEventReviewRequestDTO;
import com.manish.common.request.UpdateEventReviewRequestDTO;
import com.manish.common.response.GeneralMessageResponseDTO;
import com.manish.common.response.GetEventReviewResponseDTO;
import com.manish.content.entity.EventReviewEntity;
import com.manish.content.mapper.EventReviewMapper;
import com.manish.content.repository.EventReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventReviewService {
    private final EventReviewRepository eventReviewRepository;

    public GeneralMessageResponseDTO addEventReview(AddEventReviewRequestDTO addEventReviewRequestDTO) {
        log.info("Add Event review request received for event-data {}", addEventReviewRequestDTO);

        eventReviewRepository.save(EventReviewMapper.toEntity(addEventReviewRequestDTO));

        return new GeneralMessageResponseDTO("Event review added successfully");
    }

    public List<GetEventReviewResponseDTO> getEventReview(String eventId) {
        log.info("Get Event review request received for event-id {}", eventId);

        return eventReviewRepository.findByEventId(eventId).stream().map(EventReviewMapper::toDto).toList();
    }

    public GeneralMessageResponseDTO updateEventReview(UpdateEventReviewRequestDTO updateEventReviewRequestDTO) {
        log.info("Update Event review request received for event-data {}", updateEventReviewRequestDTO);

        Optional<EventReviewEntity> eventReview = eventReviewRepository.findById(updateEventReviewRequestDTO.getId());
        if (eventReview.isEmpty())
            return new GeneralMessageResponseDTO("Event review not found");

        eventReview.get().setRating(updateEventReviewRequestDTO.getRating());
        eventReview.get().setReviewText(updateEventReviewRequestDTO.getReviewText());
        eventReviewRepository.save(eventReview.get());

        return new GeneralMessageResponseDTO("Event review updated successfully");
    }

    public GeneralMessageResponseDTO deleteEventReview(String reviewId) {
        log.info("Delete Event review request received for review-id {}", reviewId);

        Optional<EventReviewEntity> eventReview = eventReviewRepository.findById(reviewId);
        if (eventReview.isEmpty())
            return new GeneralMessageResponseDTO("Event review not found");

        eventReviewRepository.delete(eventReview.get());

        return new GeneralMessageResponseDTO("Event review deleted successfully");
    }

    public GeneralMessageResponseDTO deleteEventReviewByEventId(String eventId) {
        log.info("Delete Event review request received for event-id {}", eventId);

        eventReviewRepository.deleteByEventId(eventId);

        return new GeneralMessageResponseDTO("Event review deleted successfully");
    }

    public GeneralMessageResponseDTO deleteAllEventReview() {
        log.info("Delete All Event review request received");

        eventReviewRepository.deleteAll();

        return new GeneralMessageResponseDTO("All event review deleted successfully");
    }
}
