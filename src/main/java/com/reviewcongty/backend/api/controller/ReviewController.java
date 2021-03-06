package com.reviewcongty.backend.api.controller;

import com.reviewcongty.backend.api.request.ReactRequest;
import com.reviewcongty.backend.api.request.ReplyRequest;
import com.reviewcongty.backend.api.request.ReviewRequest;
import com.reviewcongty.backend.core.dao.entity.Review;
import com.reviewcongty.backend.core.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/companies/{company-id}/reviews")
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping()
    public ResponseEntity<Page<Review>> getReviews(
            @PathVariable("company-id") String companyId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(reviewService.findByCompanyId(companyId, page - 1, pageSize));
    }

    @PostMapping
    public ResponseEntity<Review> create(@PathVariable("company-id") String companyId,
                                         @Valid @RequestBody ReviewRequest request) {
        Review review = reviewService.create(companyId, request);
        return ResponseEntity.ok(review);
    }

    @PostMapping("/{review-id}/reply")
    public ResponseEntity<Review> reply(@PathVariable("review-id") String reviewId,
                                        @Valid @RequestBody ReplyRequest request) {
        Review review = reviewService.reply(reviewId, request);
        return ResponseEntity.ok(review);
    }

    @PostMapping("/{review-id}/react")
    public ResponseEntity<Review> react(@PathVariable("review-id") String reviewId,
                                        @Valid @RequestBody ReactRequest request) {
        Review review = reviewService.react(reviewId, request);
        return ResponseEntity.ok(review);
    }
}
