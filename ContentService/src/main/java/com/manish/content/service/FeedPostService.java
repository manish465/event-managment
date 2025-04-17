package com.manish.content.service;

import com.manish.common.request.AddFeedPostRequestDTO;
import com.manish.common.request.UpdateFeedPostRequestDTO;
import com.manish.common.response.GeneralMessageResponseDTO;
import com.manish.common.response.GetFeedPostResponseDTO;
import com.manish.content.entity.FeedPostEntity;
import com.manish.content.mapper.FeedPostMapper;
import com.manish.content.repository.FeedPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeedPostService {
    private final FeedPostRepository feedPostRepository;

    public GeneralMessageResponseDTO addFeedPost(AddFeedPostRequestDTO addFeedPostRequestDTO) {
        log.info("Add Feed post request received for feed-data {}", addFeedPostRequestDTO);

        feedPostRepository.save(FeedPostMapper.toEntity(addFeedPostRequestDTO));

        return new GeneralMessageResponseDTO("Feed post added successfully");
    }

    public List<GetFeedPostResponseDTO> getFeedPostByUserId(String userId) {
        log.info("Get Feed post request received for user-id {}", userId);

        return feedPostRepository
                .findByUserId(userId)
                .stream().map(FeedPostMapper::toDto)
                .toList();
    }

    public GeneralMessageResponseDTO updateFeedPost(UpdateFeedPostRequestDTO updateFeedPostRequestDTO) {
        log.info("Update Feed post request received for feed-data {}", updateFeedPostRequestDTO);

        Optional<FeedPostEntity> feedPostOptional = feedPostRepository
                .findById(updateFeedPostRequestDTO.getId());

        if (feedPostOptional.isEmpty())
            return new GeneralMessageResponseDTO("Feed post not found");

        feedPostOptional.get().setPostText(updateFeedPostRequestDTO.getPostText());
        feedPostRepository.save(feedPostOptional.get());

        return new GeneralMessageResponseDTO("Feed post updated successfully");
    }

    public GeneralMessageResponseDTO deleteFeedPost(String postId) {
        log.info("Delete Feed post request received for post-id {}", postId);

        Optional<FeedPostEntity> feedPostOptional = feedPostRepository
                .findById(postId);

        if (feedPostOptional.isEmpty())
            return new GeneralMessageResponseDTO("Feed post not found");

        feedPostRepository.delete(feedPostOptional.get());

        return new GeneralMessageResponseDTO("Feed post deleted successfully");
    }

    public GeneralMessageResponseDTO deleteAllFeedPost() {
        log.info("Delete All Feed post request received");

        feedPostRepository.deleteAll();

        return new GeneralMessageResponseDTO("All feed post deleted successfully");
    }
}
