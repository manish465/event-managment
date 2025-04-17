package com.manish.content.repository;

import com.manish.content.entity.FeedPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FeedPostRepository extends MongoRepository<FeedPost, String> {
    List<FeedPost> findByUserId(String userId);
}
