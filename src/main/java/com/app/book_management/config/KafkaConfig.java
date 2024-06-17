package com.app.book_management.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * KafkaConfig настраивает компоненты для работы с Apache Kafka в контексте Spring Boot приложения.
 * @Configuration сообщает Spring, что этот класс является конфигурационным. В нем определяются бины,
 * которые будут зарегистрированы в контексте Spring при запуске приложения.
 * @EnableKafka включает поддержку Kafka в Spring, автоматически создавая необходимые бины
 * и компоненты для работы с Kafka.
 */
@Configuration
@EnableKafka
public class KafkaConfig {

    /**
     * ProducerFactory<String, Object>: Определяет фабрику для создания экземпляров Kafka-продюсеров.
     * Конкретно здесь используется DefaultKafkaProducerFactory.
     *
     * - ProducerConfig.BOOTSTRAP_SERVERS_CONFIG: Устанавливает адрес брокера Kafka (в данном случае "kafka:9092").
     * Этот параметр указывает, к какому серверу будет подключаться продюсер для отправки сообщений.
     * - ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG: класс сериализатора для ключей сообщений.
     * Здесь используется StringSerializer, который преобразует ключи в строковый формат.
     * - ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG: класс сериализатора для значений сообщений.
     * Здесь используется JsonSerializer, который будет преобразовывать объекты Java в JSON-формат.
     *
     * @return метод создает и возвращает DefaultKafkaProducerFactory с заданной конфигурацией.
     */
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * Этот метод создает экземпляр KafkaTemplate, используя ProducerFactory, определенную в предыдущем методе.
     * KafkaTemplate оборачивает логику работы с продюсерами и упрощает отправку сообщений в Kafka.
     */
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * Метод создает топик book-purchase с 1 разделом и фактором репликации 1.
     * NewTopic позволяет описывать параметры топика, имя, количество разделов и фактор репликации.
     */
    @Bean
    public NewTopic topic() {
        return new NewTopic("book-purchase", 1, (short) 1);
    }
}
