package org.ot5usk.models.get_all_books_by_author_xml;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAllBooksByAuthorRequestXml {

    private String authorId;
}
