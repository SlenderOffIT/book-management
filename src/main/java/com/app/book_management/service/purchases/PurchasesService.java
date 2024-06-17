package com.app.book_management.service.purchases;

import com.app.book_management.entity.purchase.PurchasesJson;

import java.util.UUID;

public interface PurchasesService {

    String purchaseBook(UUID idBook, PurchasesJson purchasesJson);
}
