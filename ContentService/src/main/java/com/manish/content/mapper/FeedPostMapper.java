package com.manish.content.mapper;

import com.manish.common.request.AddFeedPostRequestDTO;
import com.manish.common.response.GetFeedPostResponseDTO;
import com.manish.content.entity.FeedPostEntity;
import jakarta.validation.Valid;

public class FeedPostMapper {
    public static FeedPostEntity toEntity(@Valid AddFeedPostRequestDTO addFeedPostRequestDTO) {
        return FeedPostEntity.builder()
                .userId(addFeedPostRequestDTO.getUserId())
                .postText(addFeedPostRequestDTO.getPostText())
                .build();
    }

    public static GetFeedPostResponseDTO toDto(FeedPostEntity feedPostEntity) {
        return GetFeedPostResponseDTO.builder()
                .id(feedPostEntity.getId())
                .userId(feedPostEntity.getUserId())
                .postText(feedPostEntity.getPostText())
                .createdAt(feedPostEntity.getCreatedAt())
                .updatedAt(feedPostEntity.getUpdatedAt())
                .build();
    }
}
