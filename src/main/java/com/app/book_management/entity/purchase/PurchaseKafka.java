package com.app.book_management.entity.purchase;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PurchaseKafka {
    UUID idPurchase;
    UUID idBook;
    LocalDate purchaseDate;
    String buyerName;
}
