package org.ot5usk.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    private long id;
    private String bookTitle;
    private Long authorId;
    private Timestamp updated;
}