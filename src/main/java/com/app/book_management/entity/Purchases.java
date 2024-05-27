package com.app.book_management.entity;

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
public class Purchases {
    UUID id;
    Book book;
    LocalDate purchaseDate;
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
