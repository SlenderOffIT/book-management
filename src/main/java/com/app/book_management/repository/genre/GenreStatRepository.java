package com.app.book_management.repository.genre;

import com.app.book_management.entity.genre.GenreStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GenreStatRepository extends JpaRepository<GenreStats, UUID> {
}
