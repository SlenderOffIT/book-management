package com.app.book_management.repository;

import com.app.book_management.entity.purchase.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchasesRepository extends JpaRepository<Purchases, UUID> {
}
