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
    ('Война и мир', (SELECT id FROM authors WHERE name = 'Лев Толстой' LIMIT 1), (SELECT id FROM genres WHERE name = 'Роман' LIMIT 1), '1869-01-01', '978-5-4467-2337-0', 1500),
    ('Преступление и наказание', (SELECT id FROM authors WHERE name = 'Фёдор Достоевский' LIMIT 1), (SELECT id FROM genres WHERE name = 'Роман' LIMIT 1), '1866-01-01', '978-5-4467-2338-7', 1200),
    ('Евгений Онегин', (SELECT id FROM authors WHERE name = 'Александр Пушкин' LIMIT 1), (SELECT id FROM genres WHERE name = 'Роман' LIMIT 1), '1833-01-01', '978-5-4467-2339-4', 800),
    ('Мёртвые души', (SELECT id FROM authors WHERE name = 'Николай Гоголь' LIMIT 1), (SELECT id FROM genres WHERE name = 'Роман' LIMIT 1), '1842-01-01', '978-5-4467-2340-0', 900),
    ('Вишнёвый сад', (SELECT id FROM authors WHERE name = 'Антон Чехов' LIMIT 1), (SELECT id FROM genres WHERE name = 'Драма' LIMIT 1), '1903-01-01', '978-5-4467-2341-7', 700),
    ('Отцы и дети', (SELECT id FROM authors WHERE name = 'Иван Тургенев' LIMIT 1), (SELECT id FROM genres WHERE name = 'Роман' LIMIT 1), '1862-01-01', '978-5-4467-2342-4', 1100),
    ('Мастер и Маргарита', (SELECT id FROM authors WHERE name = 'Михаил Булгаков' LIMIT 1), (SELECT id FROM genres WHERE name = 'Роман' LIMIT 1), '1967-01-01', '978-5-4467-2343-1', 1300),
    ('Мать', (SELECT id FROM authors WHERE name = 'Максим Горький' LIMIT 1), (SELECT id FROM genres WHERE name = 'Роман' LIMIT 1), '1906-01-01', '978-5-4467-2344-8', 1000),
    ('Тихий Дон', (SELECT id FROM authors WHERE name = 'Михаил Шолохов' LIMIT 1), (SELECT id FROM genres WHERE name = 'Роман' LIMIT 1), '1940-01-01', '978-5-4467-2345-5', 1400),
    ('Доктор Живаго', (SELECT id FROM authors WHERE name = 'Борис Пастернак' LIMIT 1), (SELECT id FROM genres WHERE name = 'Роман' LIMIT 1), '1957-01-01', '978-5-4467-2346-2', 1600);

INSERT INTO reviews (book_id, reviewer_name, rating, review_text, review_date) VALUES
    ((SELECT id FROM books WHERE title = 'Война и мир' LIMIT 1), 'Иван Иванов', 9, 'Великолепное произведение!', '2023-01-01'),
    ((SELECT id FROM books WHERE title = 'Преступление и наказание' LIMIT 1), 'Мария Петрова', 8, 'Очень глубокое произведение.', '2023-02-01'),
    ((SELECT id FROM books WHERE title = 'Евгений Онегин' LIMIT 1), 'Александр Сидоров', 10, 'Классика русской литературы.', '2023-03-01'),
    ((SELECT id FROM books WHERE title = 'Мёртвые души' LIMIT 1), 'Сергей Смирнов', 7, 'Интересное, но сложное.', '2023-04-01'),
    ((SELECT id FROM books WHERE title = 'Вишнёвый сад' LIMIT 1), 'Юлия Семёнова', 6, 'Ожидала большего.', '2023-05-01'),
    ((SELECT id FROM books WHERE title = 'Отцы и дети' LIMIT 1), 'Олег Кузьмин', 8, 'Замечательная книга.', '2023-06-01'),
    ((SELECT id FROM books WHERE title = 'Мастер и Маргарита' LIMIT 1), 'Наталья Орлова', 10, 'Шедевр!', '2023-07-01'),
    ((SELECT id FROM books WHERE title = 'Мать' LIMIT 1), 'Дмитрий Колесников', 7, 'Трудное для понимания.', '2023-08-01'),
    ((SELECT id FROM books WHERE title = 'Тихий Дон' LIMIT 1), 'Оксана Николаева', 9, 'Очень понравилось.', '2023-09-01'),
    ((SELECT id FROM books WHERE title = 'Доктор Живаго' LIMIT 1), 'Ирина Михайлова', 8, 'Хорошо, но затянуто.', '2023-10-01');

INSERT INTO purchases (book_id, purchase_date, buyer_name) VALUES
    ((SELECT id FROM books WHERE title = 'Война и мир' LIMIT 1), '2023-01-02', 'Покупатель 1'),
    ((SELECT id FROM books WHERE title = 'Преступление и наказание' LIMIT 1), '2023-02-05', 'Покупатель 2'),
    ((SELECT id FROM books WHERE title = 'Евгений Онегин' LIMIT 1), '2023-03-07', 'Покупатель 3'),
    ((SELECT id FROM books WHERE title = 'Мёртвые души' LIMIT 1), '2023-04-09', 'Покупатель 4'),
    ((SELECT id FROM books WHERE title = 'Вишнёвый сад' LIMIT 1), '2023-05-11', 'Покупатель 5'),
    ((SELECT id FROM books WHERE title = 'Отцы и дети' LIMIT 1), '2023-06-13', 'Покупатель 6'),
    ((SELECT id FROM books WHERE title = 'Мастер и Маргарита' LIMIT 1), '2023-07-15', 'Покупатель 7');