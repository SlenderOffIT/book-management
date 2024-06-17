package com.app.book_management.entity.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class BookJson {
    @NotNull
    String title;
    @NotNull
    UUID authorId;
    @NotNull
    UUID genreId;
    @NotNull
    LocalDate publishedDate;
    @Min(1)
    double price;
}
