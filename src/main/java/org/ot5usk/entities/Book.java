package org.ot5usk.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "updated")
    private Timestamp updated;

    @Override
    public boolean equals(Object o) {
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash( bookTitle, authorId);
    }
}