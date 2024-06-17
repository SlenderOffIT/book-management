package com.app.book_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "book-purchase";

    /**
     * kafkaTemplate типа KafkaTemplate<String, Object> используется для отправки сообщений в Kafka.
     */
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Метод sendMessage принимающий одно сообщение как параметр
     * @param message, которое будет отправлено в топик определенный константой TOPIC.
     */
    public void sendMessage(Object message) {
        kafkaTemplate.send(TOPIC, message);
    }
}