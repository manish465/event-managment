package com.manish.content.controller;

import com.manish.common.request.AddFeedPostRequestDTO;
import com.manish.common.request.UpdateFeedPostRequestDTO;
import com.manish.common.response.GeneralMessageResponseDTO;
import com.manish.common.response.GetFeedPostResponseDTO;
import com.manish.content.service.FeedPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
public class FeedPostController {
    private final FeedPostService feedPostService;

    @PostMapping("/api/v1/event-update/add-feed-post")
    public ResponseEntity<GeneralMessageResponseDTO> addFeedPost(@RequestBody @Valid AddFeedPostRequestDTO addFeedPostRequestDTO) {
        log.info("Add Feed post request received for feed-data {}", addFeedPostRequestDTO);
        return ResponseEntity.ok(feedPostService.addFeedPost(addFeedPostRequestDTO));
    }

    @GetMapping("api/v1/event-update/get-feed-post")
    public ResponseEntity<List<GetFeedPostResponseDTO>> getFeedPostByUserId(@RequestParam String userId) {
        log.info("Get Feed post request received for user-id {}", userId);
        return ResponseEntity.ok(feedPostService.getFeedPostByUserId(userId));
    }

    @PutMapping("/api/v1/event-update/update-feed-post")
    public ResponseEntity<GeneralMessageResponseDTO> updateFeedPost(@RequestBody @Valid UpdateFeedPostRequestDTO updateFeedPostRequestDTO) {
        log.info("Update Feed post request received for feed-data {}", updateFeedPostRequestDTO);
        return ResponseEntity.ok(feedPostService.updateFeedPost(updateFeedPostRequestDTO));
    }

    @DeleteMapping("api/v1/event-update/delete-feed-post")
    public ResponseEntity<GeneralMessageResponseDTO> deleteFeedPost(@RequestParam String postId) {
        log.info("Delete Feed post request received for post-id {}", postId);
        return ResponseEntity.ok(feedPostService.deleteFeedPost(postId));
    }

    @DeleteMapping("api/v1/event-update/delete-all-feed-post")
    public ResponseEntity<GeneralMessageResponseDTO> deleteAllFeedPost() {
        log.info("Delete All Feed post request received");
        return ResponseEntity.ok(feedPostService.deleteAllFeedPost());
    }

    @GetMapping("api/v1/event-update/health-check")
    public ResponseEntity<GeneralMessageResponseDTO> healthCheck() {
        log.info("Feed Post health check request received");
        return ResponseEntity.ok(new GeneralMessageResponseDTO("Feed Post service is up and running"));
    }
}
