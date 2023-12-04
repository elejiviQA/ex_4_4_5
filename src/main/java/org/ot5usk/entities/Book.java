package org.ot5usk.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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
}