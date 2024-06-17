package com.app.book_management.entity.author;

import jakarta.validation.constraints.PastOrPresent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AuthorUpdateJson {
    String name;
    @PastOrPresent(message = "Дата рождения  не должна быть в будущем")
    LocalDate birthdate;
    String country;
}
