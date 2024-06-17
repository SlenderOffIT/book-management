## Веб-приложение для управления библиотекой книг.

Для развертывания приложения понадобится:
- Установленный Docker;
- открытый порт 5432 для PostgreSQL;
- доступный порт 9092 для Kafka;
- доступный порт 2181 для Zookeeper;

Запуск приложения осуществляется через терминал командой docker-compose up --build.  
Остановка приложения командой docker-compose down.

Приложение использует базу данных PostgreSQL под названием - book-management.  
Для подключения к БД можно использовать DBeaver с такими данными:

- Host: localhost
- Port: 5433
- Database: book-management
- Username: postgres
- Password: postgres

Либо, когда запущен контейнер через терминал с помощью команд:
- docker exec -it book-management-db /bin/sh - вход в контейнер PostgreSQL;
- psql -U postgres -d book-management - внутри контейнера подключитесь к базе данных с помощью утилиты psql;
- \dt - посмотреть список таблиц;
- SELECT * FROM .....; - сделать запрос в нужную таблицу.

Для просмотра сообщений в топике book-purchase можно воспользоваться:  
1. kafka-ui - перейдя по ссылке http://localhost:8586/  
2. открыть новое окно в терминале и ввести команды:
- docker exec -it kafka /bin/bash - с ее помощью подключитесь к контейнеру Kafka;
- kafka-console-consumer --bootstrap-server localhost:9092 --topic book-purchase --from-beginning - данная команда подключится к топику book-purchase и начнет выводить все сообщения с начала

Для удобства тестирования, база данных заполнена тестовыми данными из файла data.sql.  
Для обращения к эндпоинтам можно использовать Postman

## В ходе проекта реализованы следующие функциональности:

### Управление авторами
#### API:
- GET /v1/author/{id} — возвращает автора по id;
- PATCH /v1/author/{id} - изменение информации об авторе;
- DELETE /v1/author/{id} - удаление автора.
- POST /v1/author - создает и возвращает автора, body:
```json
  {
    "name": "имя автора",
    "birthdate": "дата рождения в формате yyyy-ММ-dd",
    "country": "страна"
  }
  ```

### Управление книгами
#### API:
- GET v1/books/{bookId} - возвращает книгу по id;
- PATCH v1/books/{bookId} - изменение информации о книге;
- DELETE /v1/books/{bookId} - удаление книги;
- GET v1/books/genre/{genreId} - возвращает список всех книг по id жанра;
- GET v1/books/author/{authorId} - возвращает список всех книг по id автора;
- POST v1/books - создает и возвращает книгу,  body:
```json
  {
    "title": "название книги",
    "authorId": "id автора",
    "genreId": "id жанра",
    "publishedDate":"дата публикации в формате yyyy-ММ-dd",
    "price": цена
  }
  ```
- POST v1/books/{bookId}/review - добавление отзыва к книге, body:
```json
 
  {
    "bookId": "id книги",
    "reviewerName": "имя",
    "rating": оценка,
    "reviewText": "текст отзыва"
  }
  ```
### Управление жанрами
#### API:

- GET v1/genre - возвращает список всех жанров;
- GET v1/genre/{id} - просмотр жанра по id;
- PATCH v1/genre/{id} - изменение жанра;
- DELETE v1/genre/{id} - удаление жанра;
- POST v1/genre - создание и возвращает жанр, body:
```json
{
  "name": "Название жанра",
  "description":"Описание"
}
```
### Совершение покупки
#### API:
- POST v1/purchases//{bookId} - возвращает сообщение о успешной покупке книги, body:
```json
{
    "bookId": "id книги",
    "buyerName": "имя покупателя"
}
```

### Статистика
#### API:
- GET /v1/stats/{viewName} - в зависимости от имени представления возвращает статистику.  
Параметры: 
- author_statistics - получение статистики по авторам;
- genre_statistics - получение статистики по жанрам;
- sales_statistics - получение статистики по продажам.