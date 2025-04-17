package com.manish.content.mapper;

import com.manish.common.request.AddFeedPostRequestDTO;
import com.manish.common.response.GetFeedPostResponseDTO;
import com.manish.content.entity.FeedPost;
import jakarta.validation.Valid;

public class FeedPostMapper {
    public static FeedPost toEntity(@Valid AddFeedPostRequestDTO addFeedPostRequestDTO) {
        return FeedPost.builder()
                .userId(addFeedPostRequestDTO.getUserId())
                .postText(addFeedPostRequestDTO.getPostText())
                .build();
    }

    public static GetFeedPostResponseDTO toDto(FeedPost feedPost) {
        return GetFeedPostResponseDTO.builder()
                .id(feedPost.getId())
                .userId(feedPost.getUserId())
                .postText(feedPost.getPostText())
                .createdAt(feedPost.getCreatedAt())
                .updatedAt(feedPost.getUpdatedAt())
                .build();
    }
}
