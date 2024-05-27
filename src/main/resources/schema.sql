create table books (
    id VARCHAR primary key not null,
    title VARCHAR(256) not null,
    author_id VARCHAR not null references authors(id),
    genre_id VARCHAR not null references genres(id),
    published_date DATE,
    isbn VARCHAR,
    price NUMERIC not null
);

create table authors (
    id VARCHAR primary key not null,
    name VARCHAR(100) not null,
    birthdate DATE not null,
    country VARCHAR
);

create table genres (
    id VARCHAR primary key not null,
    name VARCHAR(100) not null,
    description VARCHAR
);

create table reviews (
    id VARCHAR primary key not null,
    book_id VARCHAR not null references books(id),
    reviewer_name VARCHAR(256) not null,
    rating INTEGER not null,
    review_text TEXT(1000),
    review_date DATE
    CHECK (rating >= 1 AND rating <= 10)
);

create table purchases (
    id VARCHAR primary key not null,
    book_id VARCHAR not null references books(id),
    purchase_date DATE,
    buyer_name VARCHAR not null
);

CREATE INDEX idx_books_author_id ON books(author_id);
CREATE INDEX idx_books_genre_id ON books(genre_id);
CREATE INDEX idx_books_title ON books(title);
CREATE INDEX idx_reviews_rating ON reviews(rating);

