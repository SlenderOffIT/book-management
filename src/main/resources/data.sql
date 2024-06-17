--INSERT INTO authors (name, birthdate, country) VALUES
--    ('Author One', '1970-01-01', 'Country A'),
--    ('Author Two', '1980-02-01', 'Country B'),
--    ('Author Three', '1990-03-01', 'Country C');
--
---- Genres
--INSERT INTO genres (name, description) VALUES
--    ('Genre One', 'Description of Genre One'),
--    ('Genre Two', 'Description of Genre Two'),
--    ('Genre Three', 'Description of Genre Three');
--
---- Books
--INSERT INTO books (title, author_id, genre_id, published_date, isbn, price) VALUES
--    ('Book One', (SELECT id FROM authors WHERE name = 'Author One'), (SELECT id FROM genres WHERE name = 'Genre One'), '2020-01-01', 1, 10.99),
--    ('Book Two', (SELECT id FROM authors WHERE name = 'Author Two'), (SELECT id FROM genres WHERE name = 'Genre Two'), '2021-02-01', 2, 12.99),
--    ('Book Three', (SELECT id FROM authors WHERE name = 'Author Three'), (SELECT id FROM genres WHERE name = 'Genre Three'), '2022-03-01', 3, 15.99);
--
---- Reviews
--INSERT INTO reviews (book_id, reviewer_name, rating, review_text, review_date) VALUES
--    ((SELECT id FROM books WHERE title = 'Book One'), 'Reviewer One', 8, 'Great book!', '2020-01-10'),
--    ((SELECT id FROM books WHERE title = 'Book One'), 'Reviewer Two', 7, 'Good read.', '2020-01-15'),
--    ((SELECT id FROM books WHERE title = 'Book Two'), 'Reviewer Three', 9, 'Excellent!', '2021-02-10'),
--    ((SELECT id FROM books WHERE title = 'Book Three'), 'Reviewer Four', 6, 'Its okay.', '2022-03-10');
--
---- Purchases
--INSERT INTO purchases (book_id, purchase_date, buyer_name) VALUES
--    ((SELECT id FROM books WHERE title = 'Book One'), '2020-01-02', 'Buyer One'),
--    ((SELECT id FROM books WHERE title = 'Book Two'), '2021-02-05', 'Buyer Two'),
--    ((SELECT id FROM books WHERE title = 'Book Three'), '2022-03-07', 'Buyer Three');

INSERT INTO authors (name, birthdate, country) VALUES
    ('Лев Толстой', '1828-09-09', 'Россия'),
    ('Фёдор Достоевский', '1821-11-11', 'Россия'),
    ('Александр Пушкин', '1799-06-06', 'Россия'),
    ('Николай Гоголь', '1809-03-31', 'Россия'),
    ('Антон Чехов', '1860-01-29', 'Россия'),
    ('Иван Тургенев', '1818-11-09', 'Россия'),
    ('Михаил Булгаков', '1891-05-15', 'Россия'),
    ('Максим Горький', '1868-03-28', 'Россия'),
    ('Михаил Шолохов', '1905-05-24', 'Россия'),
    ('Борис Пастернак', '1890-02-10', 'Россия');

INSERT INTO genres (name, description) VALUES
    ('Роман', 'Литературный жанр, крупное эпическое повествование.'),
    ('Повесть', 'Литературный жанр, средняя форма повествования.'),
    ('Новелла', 'Краткое и насыщенное произведение.'),
    ('Рассказ', 'Малый эпический жанр, короткое повествование.'),
    ('Поэма', 'Лирическое произведение.'),
    ('Драма', 'Произведение для театральной постановки.'),
    ('Эпос', 'Древний жанр, героическое повествование.'),
    ('Трагедия', 'Произведение высокого стиля с трагическим финалом.'),
    ('Комедия', 'Произведение, вызывающее смех и направленное на развлечения.'),
    ('Сатира', 'Литературный жанр, использующий сарказм и иронию.');

INSERT INTO books (title, author_id, genre_id, published_date, isbn, price) VALUES
    ('Война и мир', (SELECT id FROM authors WHERE name = 'Лев Толстой'), (SELECT id FROM genres WHERE name = 'Роман'), '1869-01-01', '978-5-4467-2337-0', 1500),
    ('Преступление и наказание', (SELECT id FROM authors WHERE name = 'Фёдор Достоевский'), (SELECT id FROM genres WHERE name = 'Роман'), '1866-01-01', '978-5-4467-2338-7', 1200),
    ('Евгений Онегин', (SELECT id FROM authors WHERE name = 'Александр Пушкин'), (SELECT id FROM genres WHERE name = 'Роман'), '1833-01-01', '978-5-4467-2339-4', 800),
    ('Мёртвые души', (SELECT id FROM authors WHERE name = 'Николай Гоголь'), (SELECT id FROM genres WHERE name = 'Роман'), '1842-01-01', '978-5-4467-2340-0', 900),
    ('Вишнёвый сад', (SELECT id FROM authors WHERE name = 'Антон Чехов'), (SELECT id FROM genres WHERE name = 'Драма'), '1903-01-01', '978-5-4467-2341-7', 700),
    ('Отцы и дети', (SELECT id FROM authors WHERE name = 'Иван Тургенев'), (SELECT id FROM genres WHERE name = 'Роман'), '1862-01-01', '978-5-4467-2342-4', 1100),
    ('Мастер и Маргарита', (SELECT id FROM authors WHERE name = 'Михаил Булгаков'), (SELECT id FROM genres WHERE name = 'Роман'), '1967-01-01', '978-5-4467-2343-1', 1300),
    ('Мать', (SELECT id FROM authors WHERE name = 'Максим Горький'), (SELECT id FROM genres WHERE name = 'Роман'), '1906-01-01', '978-5-4467-2344-8', 1000),
    ('Тихий Дон', (SELECT id FROM authors WHERE name = 'Михаил Шолохов'), (SELECT id FROM genres WHERE name = 'Роман'), '1940-01-01', '978-5-4467-2345-5', 1400),
    ('Доктор Живаго', (SELECT id FROM authors WHERE name = 'Борис Пастернак'), (SELECT id FROM genres WHERE name = 'Роман'), '1957-01-01', '978-5-4467-2346-2', 1600);

INSERT INTO reviews (book_id, reviewer_name, rating, review_text, review_date) VALUES
    ((SELECT id FROM books WHERE title = 'Война и мир'), 'Иван Иванов', 9, 'Великолепное произведение!', '2023-01-01'),
    ((SELECT id FROM books WHERE title = 'Преступление и наказание'), 'Мария Петрова', 8, 'Очень глубокое произведение.', '2023-02-01'),
    ((SELECT id FROM books WHERE title = 'Евгений Онегин'), 'Александр Сидоров', 10, 'Классика русской литературы.', '2023-03-01'),
    ((SELECT id FROM books WHERE title = 'Мёртвые души'), 'Сергей Смирнов', 7, 'Интересное, но сложное.', '2023-04-01'),
    ((SELECT id FROM books WHERE title = 'Вишнёвый сад'), 'Юлия Семёнова', 6, 'Ожидала большего.', '2023-05-01'),
    ((SELECT id FROM books WHERE title = 'Отцы и дети'), 'Олег Кузьмин', 8, 'Замечательная книга.', '2023-06-01'),
    ((SELECT id FROM books WHERE title = 'Мастер и Маргарита'), 'Наталья Орлова', 10, 'Шедевр!', '2023-07-01'),
    ((SELECT id FROM books WHERE title = 'Мать'), 'Дмитрий Колесников', 7, 'Трудное для понимания.', '2023-08-01'),
    ((SELECT id FROM books WHERE title = 'Тихий Дон'), 'Оксана Николаева', 9, 'Очень понравилось.', '2023-09-01'),
    ((SELECT id FROM books WHERE title = 'Доктор Живаго'), 'Ирина Михайлова', 8, 'Хорошо, но затянуто.', '2023-10-01');

INSERT INTO purchases (book_id, purchase_date, buyer_name) VALUES
    ((SELECT id FROM books WHERE title = 'Война и мир'), '2023-01-02', 'Покупатель 1'),
    ((SELECT id FROM books WHERE title = 'Преступление и наказание'), '2023-02-05', 'Покупатель 2'),
    ((SELECT id FROM books WHERE title = 'Евгений Онегин'), '2023-03-07', 'Покупатель 3'),
    ((SELECT id FROM books WHERE title = 'Мёртвые души'), '2023-04-09', 'Покупатель 4'),
    ((SELECT id FROM books WHERE title = 'Вишнёвый сад'), '2023-05-11', 'Покупатель 5'),
    ((SELECT id FROM books WHERE title = 'Отцы и дети'), '2023-06-13', 'Покупатель 6'),
    ((SELECT id FROM books WHERE title = 'Мастер и Маргарита'), '2023-07-15', 'Покупатель 7'),
    ((SELECT id FROM books WHERE title = 'Мать'), '2023-08-17', 'Покупатель 8'),
    ((SELECT id FROM books WHERE title = 'Тихий Дон'), '2023-09-19', 'Покупатель 9'),
    ((SELECT id FROM books WHERE title = 'Доктор Живаго'), '2023-10-21', 'Покупатель 10');