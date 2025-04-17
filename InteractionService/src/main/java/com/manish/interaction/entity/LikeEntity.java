package com.manish.interaction.entity;

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

@Document(collection = "likes_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeEntity {
    @Id
    @Field(name = "comment-id")
    private String id;
    @Field(name = "parent-id")
    private String parentId;
    @Field(name = "user-id")
    private String userId;
    @CreatedDate
    @Field(name = "created-at")
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Field(name = "updated-at")
    private LocalDateTime updatedAt;
}
