CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS authors (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100) NOT NULL,
    birthdate DATE NOT NULL,
    country VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS genres (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100) NOT NULL,
    description VARCHAR
);

CREATE TABLE IF NOT EXISTS books (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(256) NOT NULL,
    author_id UUID NOT NULL REFERENCES authors(id),
    genre_id UUID NOT NULL REFERENCES genres(id),
    published_date DATE NOT NULL,
    isbn VARCHAR,
    price NUMERIC NOT NULL
);

CREATE TABLE IF NOT EXISTS reviews (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    book_id UUID NOT NULL REFERENCES books(id) on delete cascade on update cascade,
    reviewer_name VARCHAR(256) NOT NULL,
    rating INTEGER NOT NULL,
    review_text VARCHAR,
    review_date DATE,
    CHECK (rating >= 1 AND rating <= 10)
);

CREATE TABLE IF NOT EXISTS purchases (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    book_id UUID NOT NULL REFERENCES books(id) on delete cascade on update cascade,
    purchase_date DATE,
    buyer_name VARCHAR NOT NULL
);

CREATE INDEX idx_books_author_id ON books(author_id);
CREATE INDEX idx_books_genre_id ON books(genre_id);
CREATE INDEX idx_books_title ON books(title);
CREATE INDEX idx_reviews_rating ON reviews(rating);

-- VIEW Сценарий 3: Статистика по авторам
CREATE VIEW author_statistics AS
SELECT
    a.id AS author_id,
    a.name AS author_name,
    COUNT(b.id) AS book_count,
    AVG(r.rating) AS average_rating
FROM
    authors a
    JOIN books b ON a.id = b.author_id
    LEFT JOIN reviews r ON b.id = r.book_id
GROUP BY
    a.id, a.name;

--Сценарий 4: Статистика по жанрам
CREATE VIEW genre_statistics AS
SELECT
    g.id AS genre_id,
    g.name AS genre_name,
    COUNT(b.id) AS book_count,
    AVG(r.rating) AS average_rating
FROM
    genres g
    JOIN books b ON g.id = b.genre_id
    LEFT JOIN reviews r ON b.id = r.book_id
GROUP BY
    g.id, g.name;

-- Сценарий 5: Статистика по продажам
CREATE VIEW sales_statistics AS
SELECT
    a.id AS author_id,
    a.name AS author_name,
    SUM(b.price) AS total_sales
FROM
    authors a
    JOIN books b ON a.id = b.author_id
    JOIN purchases p ON b.id = p.book_id
GROUP BY
    a.id, a.name;