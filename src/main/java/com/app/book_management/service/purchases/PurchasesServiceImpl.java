package com.app.book_management.service.purchases;

import com.app.book_management.entity.book.Book;
import com.app.book_management.entity.purchase.Purchases;
import com.app.book_management.entity.purchase.PurchasesJson;
import com.app.book_management.exception.BookNotFoundException;
import com.app.book_management.repository.PurchasesRepository;
import com.app.book_management.repository.book.BookRepository;
import com.app.book_management.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

import static com.app.book_management.entity.purchase.MapperPurchase.toPurchaseKafka;

@Service
@Slf4j
@RequiredArgsConstructor
public class PurchasesServiceImpl implements PurchasesService {

    private final PurchasesRepository purchasesRepository;
    private final BookRepository bookRepository;
    private final KafkaProducerService kafkaProducerService;


    @Override
    public String purchaseBook(UUID idBook, PurchasesJson purchasesJson) {
        log.debug("Обрабатываем запрос на покупку книги {} от пользователя {}", idBook, purchasesJson.getBuyerName());

        Purchases purchases = new Purchases();
        Book book = bookRepository.findById(idBook)
                .orElseThrow(() -> {
                    log.warn("Книги с таким id {} не существует.", idBook);
                    return new BookNotFoundException(String.format("Книги с id %s не существует.", idBook));
                });

        purchases.setPurchaseDate(LocalDate.now());
        purchases.setBuyerName(purchasesJson.getBuyerName());
        purchases.setBook(book);

        Purchases purchaseSale = purchasesRepository.save(purchases);
        kafkaProducerService.sendMessage(toPurchaseKafka(purchaseSale));

        log.debug("Сообщение в брокер было успешно отправлено");
        log.debug("Заказ на книгу с id {} от пользователя с именем {} успешно создан", idBook, purchaseSale.getBuyerName());
        return String.format("Заказ на книгу \"%s\" создан", purchaseSale.getBook().getTitle());
    }
}
