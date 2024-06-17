package com.app.book_management.entity.author;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
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
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "birthdate", nullable = false)
    @PastOrPresent(message = "Дата рождения  не должна быть в будущем")
    LocalDate birthdate;
    @Column(name = "country", nullable = false)
    String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id)
                && Objects.equals(name, author.name)
                && Objects.equals(birthdate, author.birthdate)
                && Objects.equals(country, author.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthdate, country);
    }
}
