package com.app.book_management.service.genre;

import com.app.book_management.entity.genre.Genre;
import com.app.book_management.entity.genre.GenreJson;
import com.app.book_management.entity.genre.GenreMapper;
import com.app.book_management.exception.GenreNotFoundException;
import com.app.book_management.repository.book.BookRepository;
import com.app.book_management.repository.genre.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.app.book_management.entity.genre.GenreMapper.toGenre;
import static com.app.book_management.entity.genre.GenreMapper.toGenreJson;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Override
    public GenreJson createGenre(GenreJson genreJson) {
        log.debug("Обрабатываем запрос на добавление жанра с названием {}", genreJson.getName());
        return toGenreJson(genreRepository.save(toGenre(genreJson)));
    }

    @Override
    public GenreJson getGenreId(UUID id) {
        log.debug("Обрабатываем запрос на получение жанра с ID {}", id);
        Genre genre = findByIdGenre(id);
        return toGenreJson(genre);
    }

    @Override
    public GenreJson updateGenre(UUID id, GenreJson genreJson) {
        log.debug("Обрабатываем запрос на изменение жанра с id {}", id);

        Genre genre = findByIdGenre(id);

        if (genreJson.getName() != null && !genreJson.getName().isBlank() && !genreJson.getName().isEmpty()) {
            log.debug("У жанра с id {} было изменено название на {}", id, genreJson.getName());
            genre.setName(genreJson.getName());
        }
        if (genreJson.getDescription() != null && !genreJson.getName().isBlank() && !genreJson.getName().isEmpty()) {
            log.debug("У жанра с id {} было изменено описание на {}", id, genreJson.getDescription());
            genre.setDescription(genreJson.getDescription());
        }

        return toGenreJson(genre);
    }

    @Override
    public void deleteGenre(UUID id) {
        log.debug("Обрабатываем запрос на удаление жанра с id {}", id);

        if (!bookRepository.findByGenre_Id(id).isEmpty()) {
            log.warn("С жанром {} связаны книги, удаление не возможно", id);
            throw new GenreNotFoundException("С данным жанром связаны книги, удаление не возможно");
        }
        genreRepository.deleteById(id);
    }

    @Override
    public List<GenreJson> getAllGenres() {
        log.debug("Обрабатываем запрос на просмотр всех жанров");

        return genreRepository.findAll().stream()
                .map(GenreMapper::toGenreJson)
                .toList();
    }

    private Genre findByIdGenre(UUID genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(() -> {
                    log.warn("Жанра с таким id {} не существует.", genreId);
                    return new GenreNotFoundException(String.format("Жанра с id %s не существует.", genreId));
                });
    }
}
