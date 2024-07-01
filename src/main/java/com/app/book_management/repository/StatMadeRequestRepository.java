package com.app.book_management.repository;

import com.app.book_management.entity.author.AuthorMapping;
import com.app.book_management.entity.genre.GenreMapper;
import com.app.book_management.entity.stat.StatMadeRequest;
import com.app.book_management.entity.stat.author.AuthorSaleStats;
import com.app.book_management.entity.stat.author.AuthorStats;
import com.app.book_management.entity.stat.genre.GenreStats;
import com.app.book_management.exception.StatisticNotFoundException;
import com.app.book_management.util.ExecutionTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class StatMadeRequestRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<?> prefabricatedQuery(StatMadeRequest request) {

        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("SELECT ");
        sqlQuery.append(String.join(", ", request.getSelectFields()));
        sqlQuery.append(" FROM ").append(request.getFromView());

        if (request.getWhereConditions() != null && !request.getWhereConditions().isEmpty()) {
            log.debug("В запрос добавлен WHERE {}", request.getWhereConditions());
            sqlQuery.append(" WHERE ");
            request.getWhereConditions().forEach((key, value) -> {
                sqlQuery.append(key).append(" = '").append(value).append("' AND ");
            });
            sqlQuery.setLength(sqlQuery.length() - 5);
        }

        if (request.getLimit() != null) {
            log.debug("В запрос добавлен LIMIT {}", request.getLimit());
            sqlQuery.append(" LIMIT ").append(request.getLimit());
        }

        if (request.getOffset() != null) {
            log.debug("В запрос добавлен OFFSET {}", request.getOffset());
            sqlQuery.append(" OFFSET ").append(request.getOffset());
        }

        switch (request.getFromView()) {
            case "author_statistics":
                log.debug("Запрос {} передан в БД", sqlQuery);
                return authorStatistics(request, sqlQuery);
            case "genre_statistics":
                log.debug("Запрос {} передан в БД", sqlQuery);
                return genreStatistics(request, sqlQuery);
            case "sales_statistics":
                log.debug("Запрос {} передан в БД", sqlQuery);
                return saleStatistics(request, sqlQuery);
            default:
                log.warn("Невалидный запрос по статистики {}", request.getFromView());
                throw new StatisticNotFoundException(String.format("View  %s не существует", request.getFromView()));
        }
    }

//    @ExecutionTime
    public List<AuthorStats> authorStatistics(StatMadeRequest request, StringBuilder sqlQuery) {
        return jdbcTemplate.query(
                sqlQuery.toString(), AuthorMapping.mapRow(request.getSelectFields()));
    }

//    @ExecutionTime
    public List<GenreStats> genreStatistics(StatMadeRequest request, StringBuilder sqlQuery) {
        return jdbcTemplate.query(
                sqlQuery.toString(), GenreMapper.mapRow(request.getSelectFields()));
    }

//    @ExecutionTime
    public List<AuthorSaleStats> saleStatistics(StatMadeRequest request, StringBuilder sqlQuery) {
        return jdbcTemplate.query(
                sqlQuery.toString(), AuthorMapping.mapRowSale(request.getSelectFields()));
    }
}
