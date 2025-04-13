package com.manish.content.repository;

import com.manish.content.entity.EventReview;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventReviewRepository extends MongoRepository<EventReview, String> {
    List<EventReview> findByEventId(String eventId);
    void deleteByEventId(String eventId);
}
