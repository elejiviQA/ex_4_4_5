package org.ot5usk.entity;

import lombok.Data;

@Data
public class Book {

    private long id;
    private String bookTitle;
    private Long authorId;
}

