package com.manish.content.repository;

import com.manish.content.entity.EventUpdateEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventUpdateRepository extends MongoRepository<EventUpdateEntity, String> {
    List<EventUpdateEntity> findByEventId(String eventId);
    void deleteByEventId(String eventId);
}
