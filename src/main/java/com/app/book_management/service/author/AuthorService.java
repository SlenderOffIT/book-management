package com.app.book_management.service.author;

import com.app.book_management.entity.author.AuthorJson;
import com.app.book_management.entity.author.AuthorUpdateJson;

import java.util.UUID;

public interface AuthorService {

    AuthorJson createAuthor(AuthorJson authorJson);

    AuthorJson getAuthorId(UUID id);

    AuthorJson updateAuthor(UUID id, AuthorUpdateJson authorJson);

    void deleteAuthor(UUID id);
}
