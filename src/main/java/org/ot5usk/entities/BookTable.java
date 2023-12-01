package org.ot5usk.entities;

import lombok.Data;

@Data
public class BookTable {

    private long id;
    private String bookTitle;
    private Long authorId;
}

