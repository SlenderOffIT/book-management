package com.app.book_management.repository.author;

import com.app.book_management.entity.author.AuthorStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorStatRepository extends JpaRepository<AuthorStats, UUID> {
}
