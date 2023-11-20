package org.ot5usk.models.add_new_book;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ot5usk.entity.Author;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddNewBookRequest {

    private String bookTitle;
    private Author author;
    private long authorId;
}