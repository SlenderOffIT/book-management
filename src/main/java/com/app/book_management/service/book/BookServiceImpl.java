package com.app.book_management.service.book;

import com.app.book_management.entity.author.Author;
import com.app.book_management.entity.book.*;
import com.app.book_management.entity.genre.Genre;
import com.app.book_management.exception.AuthorNotFoundException;
import com.app.book_management.exception.BookNotFoundException;
import com.app.book_management.exception.GenreNotFoundException;
import com.app.book_management.repository.author.AuthorRepository;
import com.app.book_management.repository.book.BookRepository;
import com.app.book_management.repository.genre.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.app.book_management.entity.book.MapperBook.toBook;
import static com.app.book_management.entity.book.MapperBook.toBookResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public BookResponse createBook(BookJson bookJson) {
        log.debug("Обрабатываем запрос на cоздание книги");
        Book book = toBook(bookJson);

        Author author = findByIdAuthor(bookJson.getAuthorId());
        Genre genre = findByIdGenre(bookJson.getGenreId());

        book.setAuthor(author);
        book.setGenre(genre);

        return toBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse getBookId(UUID bookId) {
        log.debug(String.format("Обрабатываем запрос на поиск книги по id %s", bookId));
        return toBookResponse(findByIdBook(bookId));
    }

    @Override
    public BookResponse update(UUID bookId, BookUpdateJson bookJson) {
        log.debug("Обрабатываем запрос на обновление книги с id {}", bookId);
        Book book = findByIdBook(bookId);

        if (bookJson.getTitle() != null && !bookJson.getTitle().isBlank()) {
            log.debug("У книги с id {} было изменено название на {}", book.getId(), bookJson.getTitle());
            book.setTitle(bookJson.getTitle());
        }
        if (bookJson.getGenreId() != null) {
            log.debug("У книги с id {} был изменен жанр на {}", book.getId(), bookJson.getGenreId());
            Genre genre = findByIdGenre(bookJson.getGenreId());
            book.setGenre(genre);
        }
        if (bookJson.getAuthorId() != null) {
            log.debug("У книги с id {} был изменен автор на {}", book.getId(), bookJson.getAuthorId());
            Author author = findByIdAuthor(bookJson.getAuthorId());
            book.setAuthor(author);
        }
        if (bookJson.getPublishedDate() != null) {
            log.debug("У книги с id {} была изменена дата публикации на {}", book.getId(), bookJson.getPublishedDate());
            book.setPublishedDate(bookJson.getPublishedDate());
        }
        if (bookJson.getPrice() > 0) {
            log.debug("У книги с id {} была изменена цена на {}", book.getId(), bookJson.getPrice());
            book.setPrice(bookJson.getPrice());
        }

        return toBookResponse(bookRepository.save(book));
    }

    @Override
    public void delete(UUID bookId) {
        log.debug("Обрабатываем запрос на удаление книги с id {}", bookId);
        findByIdBook(bookId);
        bookRepository.deleteById(bookId);
    }

    @Override
    public List<BookResponse> getBookByGenre(UUID idGenre) {
        log.debug("Обрабатываем запрос на просмотр всех книг в жанре {}", idGenre);

        findByIdGenre(idGenre);
        return bookRepository.findByGenre_Id(idGenre).stream()
                .map(MapperBook::toBookResponse)
                .toList();
    }

    @Override
    public List<BookResponse> getBookByAuthor(UUID idAuthor) {
        log.debug("Обрабатываем запрос на просмотр всех книг автора {}", idAuthor);

        findByIdAuthor(idAuthor);
        return bookRepository.findByAuthor_Id(idAuthor).stream()
                .map(MapperBook::toBookResponse)
                .toList();
    }

    private Author findByIdAuthor(UUID authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> {
                    log.warn("Автора с таким id {} не существует.", authorId);
                    return new AuthorNotFoundException(String.format("Автора с id %s не существует.", authorId));
                });
    }

    private Genre findByIdGenre(UUID genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(() -> {
                    log.warn("Жанра с таким id {} не существует.", genreId);
                    return new GenreNotFoundException(String.format("Жанра с id %s не существует.", genreId));
                });
    }

    private Book findByIdBook(UUID bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> {
                    log.warn("Книги с таким id {} не существует.", bookId);
                    return new BookNotFoundException(String.format("Книги с id %s не существует.", bookId));
                });
    }
}
