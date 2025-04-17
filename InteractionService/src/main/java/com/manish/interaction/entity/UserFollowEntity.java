package com.manish.interaction.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "user_follow_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFollowEntity {
    @Id
    @Field(name = "user-follow-id")
    private String id;
    @Field(name = "following-user-id")
    private String followingUserId;
    @Field(name = "user-id")
    private String userId;
    @CreatedDate
    @Field(name = "created-at")
    private LocalDateTime createdAt;
}
