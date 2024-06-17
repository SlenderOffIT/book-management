package com.app.book_management.entity.review;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ReviewResponse {
    String bookName;
    String reviewerName;
    int rating;
    String reviewText;
    LocalDate reviewDate;
}
