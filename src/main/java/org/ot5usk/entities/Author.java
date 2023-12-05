package org.ot5usk.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.xml.bind.annotation.*;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
public class Author {

    @NonNull
    @XmlElement(name = "id", required = true)
    private Long id;

    @NonNull
    @XmlElement(name = "first_name", required = true)
    @JsonProperty("firstName")
    private String firstName;

    @NonNull
    @XmlElement(name = "family_name", required = true)
    @JsonProperty("familyName")
    private String familyName;

    @XmlElement(name = "second_name")
    @JsonProperty("secondName")
    private String secondName;

    @XmlElement(name = "birth_date")
    @JsonProperty("birthDate")
    private Date birthDate;

    public Author(Long id) {
        this.id = id;
    }
}