package com.app.book_management.entity.book;

import jakarta.validation.constraints.Min;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class BookUpdateJson {
    String title;
    UUID authorId;
    UUID genreId;
    LocalDate publishedDate;
    @Min(1)
    double price;
}
