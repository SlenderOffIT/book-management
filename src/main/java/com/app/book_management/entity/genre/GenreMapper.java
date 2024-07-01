package com.app.book_management.entity.genre;

import com.app.book_management.entity.stat.genre.GenreStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

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

    public static RowMapper<GenreStats> mapRow(List<String> selectFields) {
        return (rs, rowNum) -> {
            GenreStats genreStats = new GenreStats();

            if (selectFields.contains("genre_id") && hasColumn(rs, "genre_id")) {
                Object genreIdObj = rs.getObject("genre_id");
                if (genreIdObj != null) {
                    genreStats.setGenreId((UUID) genreIdObj);
                }
            }

            if (selectFields.contains("genre_name") && hasColumn(rs, "genre_name")) {
                String genreName = rs.getString("genre_name");
                if (genreName != null) {
                    genreStats.setNameGenre(genreName);
                }
            }

            if (selectFields.contains("book_count") && hasColumn(rs, "book_count")) {
                int bookCount = rs.getInt("book_count");
                if (!rs.wasNull()) {
                    genreStats.setCountBook(bookCount);
                }
            }

            if (selectFields.contains("average_rating") && hasColumn(rs, "average_rating")) {
                double averageRating = rs.getDouble("average_rating");
                if (!rs.wasNull()) {
                    genreStats.setAvgRating(averageRating);
                }
            }

            return genreStats;
        };
    }

    private static boolean hasColumn(ResultSet rs, String columnName) {
        try {
            rs.findColumn(columnName);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
