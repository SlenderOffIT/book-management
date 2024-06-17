package com.app.book_management.service.book;

import com.app.book_management.entity.book.*;

import java.util.List;
import java.util.UUID;

public interface BookService {

    BookResponse createBook(BookJson bookJson);

    BookResponse getBookId(UUID bookId);

    BookResponse update(UUID id, BookUpdateJson bookJson);

    void delete(UUID bookId);

    List<BookResponse> getBookByGenre(UUID idGenre);

    List<BookResponse> getBookByAuthor(UUID idAuthor);
}
