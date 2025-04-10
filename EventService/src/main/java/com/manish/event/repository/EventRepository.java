package com.manish.event.repository;

import com.manish.event.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, String> {
    @Query("SELECT COUNT(e.id) FROM EventEntity e WHERE e.id IN :ids")
    long countExistingIds(@Param("ids") List<String> eventIDs);
}
