package org.ot5usk.models.get_all_books_by_author;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ot5usk.entities.Author;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAllBooksByAuthorResponse {

    @XmlElement(name = "author", required = true)
    private Author author;

    @XmlElement(name = "book_title", required = true)
    @JsonProperty("bookTitle")
    private String bookTitle;

    @XmlElement(name = "updated")
    @JsonProperty("updated")
    private Date updated;
}