package com.app.book_management.repository.genre;

import com.app.book_management.entity.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
}
