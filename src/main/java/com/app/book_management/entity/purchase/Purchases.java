package com.app.book_management.entity.purchase;

import com.app.book_management.entity.book.Book;
import jakarta.persistence.*;
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
@Table(name = "purchases")
public class Purchases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    Book book;
    @Column(name = "purchase_date")
    LocalDate purchaseDate;
    @Column(name = "buyer_name", nullable = false)
    String buyerName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchases purchases = (Purchases) o;
        return Objects.equals(id, purchases.id) && Objects.equals(book, purchases.book)
                && Objects.equals(purchaseDate, purchases.purchaseDate)
                && Objects.equals(buyerName, purchases.buyerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, purchaseDate, buyerName);
    }
}
