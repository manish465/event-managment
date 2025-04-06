package com.manish.event.repository;

import com.manish.event.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingEntity, String> {
    @Query("SELECT b FROM BookingEntity b WHERE b.eventId = :eventId AND b.userId = :userId")
    Optional<BookingEntity> findBooking(@Param("eventId") String eventId, @Param("userId") String userId);

    @Modifying
    @Query("DELETE FROM BookingEntity b WHERE b.eventId = :eventId")
    void deleteByEventId(@Param("eventId") String eventId);

    List<BookingEntity> findAllByEventId(String eventId);
}
