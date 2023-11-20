package org.ot5usk.models.get_all_books_by_author_xml;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.ot5usk.entity.Book;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAllBooksByAuthorResponseXml {

    private List<Book> books;
}
