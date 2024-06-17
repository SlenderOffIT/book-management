package com.app.book_management.service.review;

import com.app.book_management.entity.book.Book;
import com.app.book_management.entity.review.Review;
import com.app.book_management.entity.review.ReviewJson;
import com.app.book_management.entity.review.ReviewResponse;
import com.app.book_management.exception.BookNotFoundException;
import com.app.book_management.repository.book.BookRepository;
import com.app.book_management.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

import static com.app.book_management.entity.review.MapperReview.toReview;
import static com.app.book_management.entity.review.MapperReview.toReviewResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    @Override
    public ReviewResponse createReview(UUID bookId, ReviewJson reviewJson) {
        log.debug("Обрабатываем запрос на создание отзыва к книге с id {}", reviewJson.getBookId());

        Review review = toReview(reviewJson);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> {
                    log.warn("Книги с таким id {} не существует.", bookId);
                    return new BookNotFoundException(String.format("Книги с id %s не существует.", bookId));
                });
        review.setBook(book);
        review.setReviewDate(LocalDate.now());

        Review saveReview = reviewRepository.save(review);
        return toReviewResponse(saveReview);
    }
}
