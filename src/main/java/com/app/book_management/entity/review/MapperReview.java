package com.app.book_management.entity.review;

public class MapperReview {

    public static Review toReview(ReviewJson reviewJson) {
        return Review.builder()
                .reviewerName(reviewJson.getReviewerName())
                .reviewText(reviewJson.getReviewText())
                .rating(reviewJson.getRating())
                .build();
    }

    public static ReviewResponse toReviewResponse(Review review) {
        return ReviewResponse.builder()
                .reviewerName(review.getReviewerName())
                .bookName(review.getBook().getTitle())
                .rating(review.getRating())
                .reviewText(review.getReviewText())
                .reviewDate(review.getReviewDate())
                .build();
    }
}
