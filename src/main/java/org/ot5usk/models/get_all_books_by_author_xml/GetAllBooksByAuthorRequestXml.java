package org.ot5usk.models.get_all_books_by_author_xml;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import jakarta.xml.bind.annotation.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "author")
public class GetAllBooksByAuthorRequestXml {

    @XmlElement(name = "author_id", required = true)
    private String authorId;
}