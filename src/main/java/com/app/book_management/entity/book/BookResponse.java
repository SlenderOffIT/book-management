package com.app.book_management.entity.book;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class BookResponse {
        String title;
        String authorName;
        String genre;
        LocalDate publishedDate;
}
