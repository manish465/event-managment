package com.manish.content.repository;

import com.manish.content.entity.EventUpdate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventUpdateRepository extends MongoRepository<EventUpdate, String> {
    List<EventUpdate> findByEventId(String eventId);
    void deleteByEventId(String eventId);
}
