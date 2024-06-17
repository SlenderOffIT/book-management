package com.app.book_management.controller;


import com.app.book_management.entity.genre.GenreJson;
import com.app.book_management.service.genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1/genre")
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    public GenreJson postBook(@RequestBody GenreJson genreJson) {
        return genreService.createGenre(genreJson);
    }

    @GetMapping
    public List<GenreJson> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public GenreJson getGenreById(@PathVariable UUID id) {
        return genreService.getGenreId(id);
    }

    @PatchMapping("/{id}")
    public GenreJson updateGenre(@PathVariable UUID id, @RequestBody GenreJson genreJson) {
        return genreService.updateGenre(id, genreJson);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable UUID id) {
        genreService.deleteGenre(id);
    }
}
