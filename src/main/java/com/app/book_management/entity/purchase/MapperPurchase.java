package com.app.book_management.entity.purchase;

public class MapperPurchase {

    public static PurchaseKafka toPurchaseKafka(Purchases purchases) {
        return PurchaseKafka.builder()
                .idPurchase(purchases.getId())
                .idBook(purchases.getBook().getId())
                .purchaseDate(purchases.getPurchaseDate())
                .buyerName(purchases.getBuyerName())
                .build();
    }
}
