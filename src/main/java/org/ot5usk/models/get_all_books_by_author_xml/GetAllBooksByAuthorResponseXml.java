package org.ot5usk.models.get_all_books_by_author_xml;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ot5usk.models.get_all_books_by_author.GetAllBooksByAuthorResponse;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "authors_books")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAllBooksByAuthorResponseXml {

    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book", required = true)
    private List<GetAllBooksByAuthorResponse> books;

    @Override
    public boolean equals(Object o) {
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(books);
    }
}