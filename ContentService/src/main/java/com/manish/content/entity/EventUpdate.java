package com.manish.content.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "event-update-table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventUpdate {
    @Id
    @Field(name = "event-update-id")
    private String id;
    @Field(name = "event-id")
    private String eventId;
    @Field(name = "user-id")
    private String userId;
    @Field(name = "update-text")
    private String updateText;
    @CreatedDate
    @Field(name = "created-at")
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Field(name = "updated-at")
    private LocalDateTime updatedAt;
}
