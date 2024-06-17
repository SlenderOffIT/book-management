package com.app.book_management.entity.book;

public class MapperBook {

    public static Book toBook(BookJson bookDto) {
        return Book.builder()
                .title(bookDto.getTitle())
                .publishedDate(bookDto.getPublishedDate())
                .price(bookDto.getPrice())
                .build();
    }

    public static BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                .title(book.getTitle())
                .authorName(book.getAuthor().getName())
                .genre(book.getGenre().getName())
                .publishedDate(book.getPublishedDate())
                .build();
    }
}
