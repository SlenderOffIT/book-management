package com.app.book_management.entity.stat.author;

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
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "sales_statistics", schema = "public")
public class AuthorSaleStats {

    @Id
    @JsonIgnore
    @Column(name = "author_id")
    UUID authorId;
    @Column(name = "author_name")
    String authorName;
    @Column(name = "total_sales")
    double sumSale;
}