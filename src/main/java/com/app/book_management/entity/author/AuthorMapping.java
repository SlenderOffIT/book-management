package com.app.book_management.entity.author;

public class AuthorMapping {

    public static Author toAuthor(AuthorJson authorJson) {
        return Author.builder()
                .birthdate(authorJson.getBirthdate())
                .name(authorJson.getName())
                .country(authorJson.getCountry())
                .build();
    }

    public static AuthorJson toAuthorJson(Author author) {
        return AuthorJson.builder()
                .name(author.getName())
                .birthdate(author.getBirthdate())
                .country(author.getCountry())
                .build();
    }
}
