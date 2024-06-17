package com.app.book_management.entity.genre;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "description", nullable = false)
    String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id)
                && Objects.equals(name, genre.name)
                && Objects.equals(description, genre.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
