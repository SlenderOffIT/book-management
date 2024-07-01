package com.app.book_management.service.stats;

import com.app.book_management.entity.stat.StatMadeRequest;
import com.app.book_management.exception.StatisticNotFoundException;
import com.app.book_management.repository.StatMadeRequestRepository;
import com.app.book_management.repository.author.AuthorSaleRepository;
import com.app.book_management.repository.author.AuthorStatRepository;
import com.app.book_management.repository.genre.GenreStatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class StatsServiceImpl implements StatsService {

    private final GenreStatRepository genreStatRepository;
    private final AuthorStatRepository authorStatRepository;
    private final AuthorSaleRepository authorSaleRepository;
    private final StatMadeRequestRepository statMadeRepository;

    private static final List<String> AUTHOR_STATISTICS_FIELDS = Arrays.asList("author_id", "author_name", "book_count", "average_rating");
    private static final List<String> GENRE_STATISTICS_FIELDS = Arrays.asList("genre_id", "genre_name", "book_count", "average_rating");
    private static final List<String> SALES_STATISTICS_FIELDS = Arrays.asList("author_id", "author_name", "total_sales");

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

    @Override
    public List<?> prefabricatedQuery(StatMadeRequest request) {
        validateFields(request);
        return statMadeRepository.prefabricatedQuery(request);
    }

    private void validateFields(StatMadeRequest request) {
        log.debug("Обрабатываем сборный запрос на просмотр статистики по {}", request.getFromView());

        List<String> validFields;
        switch (request.getFromView()) {
            case "author_statistics":
                validFields = AUTHOR_STATISTICS_FIELDS;
                break;
            case "genre_statistics":
                validFields = GENRE_STATISTICS_FIELDS;
                break;
            case "sales_statistics":
                validFields = SALES_STATISTICS_FIELDS;
                break;
            default:
                log.warn("Невалидный запрос по статистики {}", request.getFromView());
                throw new StatisticNotFoundException(String.format("View  %s не существует", request.getFromView()));
        }

        for (String field : request.getSelectFields()) {
            if (!validFields.contains(field)) {
                log.warn("Не верно указано поле {} ", field);
                throw new StatisticNotFoundException(String.format("Не верно указано поле %s ", field));
            }
        }
    }
}

