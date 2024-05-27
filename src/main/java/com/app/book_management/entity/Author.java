package com.app.book_management.entity;

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
public class Author {
    UUID id;
    String name;
    LocalDate birthdate;
    String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) && Objects.equals(name, author.name) && Objects.equals(birthdate, author.birthdate) && Objects.equals(country, author.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthdate, country);
    }
}
