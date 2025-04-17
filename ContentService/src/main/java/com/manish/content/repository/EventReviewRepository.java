package com.manish.content.repository;

import com.manish.content.entity.EventReviewEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventReviewRepository extends MongoRepository<EventReviewEntity, String> {
    List<EventReviewEntity> findByEventId(String eventId);
    void deleteByEventId(String eventId);
}
