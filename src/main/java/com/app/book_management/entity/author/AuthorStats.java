package com.app.book_management.entity.author;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "author_statistics", schema = "public")
public class AuthorStats {

    @Id
    @JsonIgnore
    @Column(name = "author_id")
    UUID authorId;
    @Column(name = "author_name")
    String authorName;
    @Column(name = "book_count")
    Long bookCount;
    @Column(name = "average_rating")
    Double averageRating;

}
//TODO сценарий 3
