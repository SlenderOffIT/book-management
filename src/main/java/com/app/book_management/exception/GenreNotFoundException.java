package com.app.book_management.exception;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(String message) {
        super(message);
    }
}
