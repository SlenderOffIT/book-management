package com.app.book_management.service.stats;

import com.app.book_management.exception.StatisticNotFoundException;
import com.app.book_management.repository.author.AuthorSaleRepository;
import com.app.book_management.repository.author.AuthorStatRepository;
import com.app.book_management.repository.genre.GenreStatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class StatsServiceImpl implements StatsService {

    private final GenreStatRepository genreStatRepository;
    private final AuthorStatRepository authorStatRepository;
    private final AuthorSaleRepository authorSaleRepository;

    @Override
    public List<?> getView(String viewName) {
        log.debug("Обрабатываем запрос на просмотр статистики по {}", viewName);
        switch (viewName) {
            case "author_statistics":
                return authorStatRepository.findAll();
            case "genre_statistics":
                return genreStatRepository.findAll();
            case "sales_statistics":
                return authorSaleRepository.findAll();
            default:
                log.warn("Невалидный запрос по статистики {}", viewName);
                throw new StatisticNotFoundException(String.format("Статистики по %s не найдено", viewName));
        }
    }
}
