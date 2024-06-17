package com.app.book_management.service.review;

import com.app.book_management.entity.review.ReviewJson;
import com.app.book_management.entity.review.ReviewResponse;

import java.util.UUID;

public interface ReviewService {

    ReviewResponse createReview(UUID bookId, ReviewJson reviewJson);
}
