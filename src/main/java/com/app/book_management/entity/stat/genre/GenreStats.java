package com.app.book_management.entity.stat.genre;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "genre_statistics", schema = "public")
public class GenreStats {

    @Id
    @JsonIgnore
    @Setter
    @Column(name = "genre_id")
    UUID genreId;
    @Setter
    @Column(name = "genre_name")
    String nameGenre;
    @Setter
    @Column(name = "book_count")
    int countBook;
    @Column(name = "average_rating")
    Double avgRating;

    public void setAvgRating(Double avgRating) {
        if (avgRating == null) {
            this.avgRating = 0.0;
        }
        this.avgRating = avgRating;
    }
}
