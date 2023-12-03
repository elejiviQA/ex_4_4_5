package org.ot5usk.entities;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Book {

    private long id;
    private String bookTitle;
    private Long authorId;
    private Timestamp updated;
}