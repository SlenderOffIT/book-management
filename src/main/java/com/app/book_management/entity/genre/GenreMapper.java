package com.app.book_management.entity.genre;

public class GenreMapper {

    public static Genre toGenre(GenreJson genreJson) {
        return Genre.builder()
                .name(genreJson.getName())
                .description(genreJson.getDescription())
                .build();
    }

    public static GenreJson toGenreJson(Genre genre) {
        return GenreJson.builder()
                .name(genre.getName())
                .description(genre.getDescription())
                .build();
    }
}
