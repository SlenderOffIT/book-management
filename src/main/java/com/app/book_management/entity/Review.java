package com.app.book_management.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Review {
    UUID id;
    Book book;
    String reviewerName;
    @Min(1)
    @Max(10)
    String reviewText;
    int rating;
    LocalDate reviewDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return rating == review.rating && Objects.equals(id, review.id) && Objects.equals(book, review.book) && Objects.equals(reviewerName, review.reviewerName) && Objects.equals(reviewText, review.reviewText) && Objects.equals(reviewDate, review.reviewDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, reviewerName, reviewText, rating, reviewDate);
    }
}
