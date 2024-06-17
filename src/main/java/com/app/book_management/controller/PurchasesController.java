package com.app.book_management.controller;

import com.app.book_management.entity.purchase.PurchasesJson;
import com.app.book_management.service.purchases.PurchasesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1/purchases")
public class PurchasesController {

    private final PurchasesService purchasesService;

    @PostMapping("/{bookId}")
    public String purchaseBook(@PathVariable UUID bookId, @RequestBody PurchasesJson purchasesJson) {
        return purchasesService.purchaseBook(bookId, purchasesJson);
    }
}
