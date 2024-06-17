package com.app.book_management.entity.book;

import com.app.book_management.entity.author.Author;
import com.app.book_management.entity.genre.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;
    @Column(name = "title", nullable = false)
    String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    Author author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    Genre genre;
    @Column(name = "published_date", nullable = false)
    @PastOrPresent(message = "Дата публикации не должна быть в будущем")
    LocalDate publishedDate;
    @Column(name = "isbn", unique = true)
    String isbn;
    @Column(name = "price", nullable = false)
    double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(book.price, price) == 0
                && Objects.equals(id, book.id)
                && Objects.equals(title, book.title)
                && Objects.equals(author, book.author)
                && Objects.equals(genre, book.genre)
                && Objects.equals(publishedDate, book.publishedDate)
                && Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, genre, publishedDate, isbn, price);
    }
}
