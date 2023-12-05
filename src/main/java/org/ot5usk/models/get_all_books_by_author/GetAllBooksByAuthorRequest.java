package org.ot5usk.models.get_all_books_by_author;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAllBooksByAuthorRequest {

    private Integer authorId;
}
