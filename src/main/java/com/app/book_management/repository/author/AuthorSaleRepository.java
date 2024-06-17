package com.app.book_management.repository.author;

import com.app.book_management.entity.author.AuthorSaleStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorSaleRepository extends JpaRepository<AuthorSaleStats, UUID> {
}
