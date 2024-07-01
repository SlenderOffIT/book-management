package com.app.book_management.entity.author;

import com.app.book_management.entity.stat.author.AuthorSaleStats;
import com.app.book_management.entity.stat.author.AuthorStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class AuthorMapping {

    public static Author toAuthor(AuthorJson authorJson) {
        return Author.builder()
                .birthdate(authorJson.getBirthdate())
                .name(authorJson.getName())
                .country(authorJson.getCountry())
                .build();
    }

    public static AuthorJson toAuthorJson(Author author) {
        return AuthorJson.builder()
                .name(author.getName())
                .birthdate(author.getBirthdate())
                .country(author.getCountry())
                .build();
    }

    public static RowMapper<AuthorStats> mapRow(List<String> selectFields) {
        return (rs, rowNum) -> {
            AuthorStats authorStats = new AuthorStats();
            if (selectFields.contains("author_id") && hasColumn(rs, "author_id")) {
                Object authorIdObj = rs.getObject("author_id");
                if (authorIdObj != null) {
                    authorStats.setAuthorId((UUID) authorIdObj);
                }
            }

            if (selectFields.contains("author_name") && hasColumn(rs, "author_name")) {
                String authorName = rs.getString("author_name");
                if (authorName != null) {
                    authorStats.setAuthorName(authorName);
                }
            }

            if (selectFields.contains("book_count") && hasColumn(rs, "book_count")) {
                long bookCount = rs.getLong("book_count");
                if (!rs.wasNull()) {
                    authorStats.setBookCount(bookCount);
                }
            }

            if (selectFields.contains("average_rating") && hasColumn(rs, "average_rating")) {
                double averageRating = rs.getDouble("average_rating");
                if (!rs.wasNull()) {
                    authorStats.setAverageRating(averageRating);
                }
            }

            return authorStats;
        };
    }

    public static RowMapper<AuthorSaleStats> mapRowSale(List<String> selectFields) {
        return (rs, rowNum) -> {
            AuthorSaleStats authorSaleStats = new AuthorSaleStats();

            if (selectFields.contains("author_id") && hasColumn(rs, "author_id")) {
                Object authorIdObj = rs.getObject("author_id");
                if (authorIdObj != null) {
                    authorSaleStats.setAuthorId((UUID) authorIdObj);
                }
            }

            if (selectFields.contains("author_name") && hasColumn(rs, "author_name")) {
                String authorName = rs.getString("author_name");
                if (authorName != null) {
                    authorSaleStats.setAuthorName(authorName);
                }
            }

            if (selectFields.contains("total_sales") && hasColumn(rs, "total_sales")) {
                double totalSales = rs.getDouble("total_sales");
                if (!rs.wasNull()) {
                    authorSaleStats.setSumSale(totalSales);
                }
            }

            return authorSaleStats;
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
