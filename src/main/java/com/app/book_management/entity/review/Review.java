package com.app.book_management.entity.review;

import com.app.book_management.entity.book.Book;
import jakarta.persistence.*;
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
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    Book book;
    @Column(name = "reviewer_name", nullable = false)
    String reviewerName;
    @Min(1)
    @Max(10)
    @Column(name = "rating", nullable = false)
    int rating;
    @Column(name = "review_text", nullable = false)
    String reviewText;
    @Column(name = "review_date")
    LocalDate reviewDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return rating == review.rating && Objects.equals(id, review.id)
                && Objects.equals(book, review.book)
                && Objects.equals(reviewerName, review.reviewerName)
                && Objects.equals(reviewText, review.reviewText)
                && Objects.equals(reviewDate, review.reviewDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, reviewerName, reviewText, rating, reviewDate);
    }
}
