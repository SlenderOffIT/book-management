package com.app.book_management.service.author;

import com.app.book_management.entity.author.Author;
import com.app.book_management.entity.author.AuthorJson;
import com.app.book_management.entity.author.AuthorSaleStats;
import com.app.book_management.entity.author.AuthorStats;
import com.app.book_management.entity.author.AuthorUpdateJson;
import com.app.book_management.exception.AuthorBadRequestException;
import com.app.book_management.exception.AuthorNotFoundException;
import com.app.book_management.repository.author.AuthorRepository;
import com.app.book_management.repository.book.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.app.book_management.entity.author.AuthorMapping.toAuthor;
import static com.app.book_management.entity.author.AuthorMapping.toAuthorJson;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public AuthorJson createAuthor(AuthorJson authorJson) {
        log.debug("Обрабатываем запрос на создание автора с именем {}", authorJson.getName());
        return toAuthorJson(authorRepository.save(toAuthor(authorJson)));
    }

    @Override
    public AuthorJson getAuthorId(UUID id) {
        log.debug("Обрабатываем запрос на просмотр автора автора с id {}", id);
        return toAuthorJson(findByIdAuthor(id));
    }

    @Override
    public AuthorJson updateAuthor(UUID id, AuthorUpdateJson authorJson) {
        log.debug("Обрабатываем запрос на изменение автора с id {}",id);

        Author author = findByIdAuthor(id);

        if (authorJson.getName() != null && authorJson.getName().isBlank()) {
            log.debug("У автора с id {} было изменено имя на {}", id, authorJson.getName());

            author.setName(authorJson.getName());
        }
        if (authorJson.getBirthdate() != null) {
            log.debug("У автора с id {} была изменена дата рождения на {}", id, authorJson.getBirthdate());

            author.setBirthdate(authorJson.getBirthdate());
        }
        if (authorJson.getCountry() != null && authorJson.getCountry().isBlank()) {
            log.debug("У автора с id {} была изменена страна на {}", id, authorJson.getCountry());

            author.setCountry(authorJson.getCountry());
        }

        return toAuthorJson(authorRepository.save(author));
    }

    @Override
    public void deleteAuthor(UUID id) {
        log.debug("Обрабатываем запрос на удаление автора с id {}", id);

        if (bookRepository.findByAuthor_Id(id).isEmpty()) {
            authorRepository.deleteById(id);
        } else {
            log.warn("Удаление автора {} не удалось", id);
            throw new AuthorBadRequestException(String.format("С автором id %s связано много книг, удаление не возможно", id));
        }
    }

    private Author findByIdAuthor(UUID authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> {
                    log.warn("Автора с таким id {} не существует.", authorId);
                    return new AuthorNotFoundException(String.format("Автора с id %s не существует.", authorId));
                });
    }
}
