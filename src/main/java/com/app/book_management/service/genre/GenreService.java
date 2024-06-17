package com.app.book_management.service.genre;

import com.app.book_management.entity.genre.GenreJson;

import java.util.List;
import java.util.UUID;

public interface GenreService {

    GenreJson createGenre(GenreJson genreJson);

    GenreJson getGenreId(UUID id);

    GenreJson updateGenre(UUID id, GenreJson genreJson);

    void deleteGenre(UUID id);

    List<GenreJson> getAllGenres();
}
