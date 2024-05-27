package com.app.book_management.service;

import com.web.app.books.entity.Book;
import com.web.app.books.entity.BookJson;

import java.util.UUID;

public interface BookService {
    Book createBook(BookJson bookJson);

    Book update(UUID id, BookJson bookJson);

    void delete(UUID id, UUID userId);
}
