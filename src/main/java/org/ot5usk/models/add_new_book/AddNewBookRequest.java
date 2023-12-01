package org.ot5usk.models.add_new_book;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.ot5usk.entities.AuthorTable;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddNewBookRequest {

    private String bookTitle;
    private AuthorTable author;
}