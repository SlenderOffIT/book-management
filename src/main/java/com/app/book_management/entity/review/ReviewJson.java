package com.app.book_management.entity.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ReviewJson {

    UUID bookId;
    String reviewerName;
    @Min(value = 1, message = "Я понимаю, что книга вам не понравилась, но минимальный рейтинг должен быть 1")
    @Max(value = 10, message = "Я понимаю, что книга вам понравилась, но максимальный рейтинг должен быть 10")
    int rating;
    @Max(3000)
    @NotBlank
    @NotEmpty
    String reviewText;

}
