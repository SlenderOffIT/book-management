package com.app.book_management.entity.book;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class BookResponseGradeAndReview {
        String nameBook;
        double rating;
        int quantityReview;
}
