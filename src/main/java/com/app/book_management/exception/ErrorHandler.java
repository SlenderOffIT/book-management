package com.app.book_management.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({AuthorNotFoundException.class, BookNotFoundException.class, GenreNotFoundException.class,
            StatisticNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundExceptions(Exception e) {
        e.printStackTrace();
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleThrowable(Throwable e) {
        e.printStackTrace();
        return new ErrorResponse("Произошла непредвиденная ошибка " + e.getClass().getName()
                + " c сообщением " + e.getMessage() + ".");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class, AuthorBadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(Exception e) {
        e.printStackTrace();
        return new ErrorResponse(e.getMessage());
    }
}
