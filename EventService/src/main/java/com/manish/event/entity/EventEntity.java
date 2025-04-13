package com.manish.event.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "event-table")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private String id;
    @Column(name = "organizer_id")
    private String organizerId;
    @Column(name = "event_name")
    private String eventName;
    @Column(name = "description")
    private String description;
    @Column(name = "start_datetime")
    private LocalDateTime startDateTime;
    @Column(name = "end_datetime")
    private LocalDateTime endDateTime;
    @Column(name = "booking_start_datetime")
    private LocalDateTime bookingStartDateTime;
    @Column(name = "booking_end_datetime")
    private LocalDateTime bookingEndDateTime;
    @Column(name = "max_capacity")
    private Integer maxCapacity;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ElementCollection
    @CollectionTable(name = "event_images", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "event_images")
    private List<String> eventImages;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
