package com.app.book_management.entity;

import java.time.LocalDate;
import java.util.UUID;

public record BookJson(UUID id,
        String title,
        Author author,
        Genre genre,
        LocalDate publishedDate,
        String isbn,
        double price) {
}
