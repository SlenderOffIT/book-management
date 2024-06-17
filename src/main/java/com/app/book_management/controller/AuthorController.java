package com.app.book_management.controller;

import com.app.book_management.entity.author.AuthorJson;
import com.app.book_management.entity.author.AuthorUpdateJson;
import com.app.book_management.service.author.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/author")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public AuthorJson createAuthor(@Valid @RequestBody AuthorJson authorJson) {
        return authorService.createAuthor(authorJson);
    }

    @GetMapping("/{id}")
    public AuthorJson getAuthorById(@PathVariable UUID id) {
        return authorService.getAuthorId(id);
    }

    @PatchMapping("/{id}")
    public AuthorJson updateAuthor(@PathVariable UUID id, @Valid @RequestBody AuthorUpdateJson authorJson) {
        return authorService.updateAuthor(id, authorJson);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable UUID id) {
        authorService.deleteAuthor(id);
    }
}
