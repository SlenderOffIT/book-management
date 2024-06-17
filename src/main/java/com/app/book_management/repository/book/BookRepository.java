package com.app.book_management.repository.book;

import com.app.book_management.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findByGenre_Id(UUID id);

    List<Book> findByAuthor_Id(UUID id);
}
