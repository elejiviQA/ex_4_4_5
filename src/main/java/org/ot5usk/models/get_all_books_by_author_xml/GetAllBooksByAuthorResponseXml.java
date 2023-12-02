package org.ot5usk.models.get_all_books_by_author_xml;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ot5usk.models.get_all_books_by_author.GetAllBooksByAuthorResponse;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "authors_books")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAllBooksByAuthorResponseXml {

    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book", required = true)
    private List<GetAllBooksByAuthorResponse> books;
}
