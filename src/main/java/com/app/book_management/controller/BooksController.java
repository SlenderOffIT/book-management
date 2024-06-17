package com.app.book_management.controller;

import com.app.book_management.entity.book.BookJson;
import com.app.book_management.entity.book.BookResponse;
import com.app.book_management.entity.book.BookUpdateJson;
import com.app.book_management.entity.review.ReviewJson;
import com.app.book_management.entity.review.ReviewResponse;
import com.app.book_management.service.review.ReviewService;
import com.app.book_management.service.book.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1/books")
public class BooksController {

    private final BookService bookService;
    private final ReviewService reviewService;

    @PostMapping
    public BookResponse postBook(@Valid @RequestBody BookJson bookJson) {
        return bookService.createBook(bookJson);
    }

    @PatchMapping("/{bookId}")
    public BookResponse patchBook(@PathVariable UUID bookId, @Valid @RequestBody BookUpdateJson bookJson) {
        return bookService.update(bookId, bookJson);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable UUID bookId) {
        bookService.delete(bookId);
    }

    @GetMapping("/{bookId}")
    public BookResponse getBookId(@PathVariable UUID bookId) {
        return bookService.getBookId(bookId);
    }

    @PostMapping("/{bookId}/review")
    public ReviewResponse postReviewBookById(@PathVariable UUID bookId, @RequestBody ReviewJson reviewJson) {
        return reviewService.createReview(bookId, reviewJson);
    }

    @GetMapping("/genre/{genreId}")
    public List<BookResponse> getBookByGenre(@PathVariable UUID genreId) {
        return bookService.getBookByGenre(genreId);
    }

    @GetMapping("/author/{authorId}")
    public List<BookResponse> getBookByAuthor(@PathVariable UUID authorId) {
        return bookService.getBookByAuthor(authorId);
    }
}