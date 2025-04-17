package com.manish.content.repository;

import com.manish.content.entity.FeedPostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FeedPostRepository extends MongoRepository<FeedPostEntity, String> {
    List<FeedPostEntity> findByUserId(String userId);
}
