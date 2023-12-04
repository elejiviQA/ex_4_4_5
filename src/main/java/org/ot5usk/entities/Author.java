package org.ot5usk.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
public class Author {

    @NonNull
    @XmlElement(name = "id", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @XmlElement(name = "first_name", required = true)
    @JsonProperty("firstName")
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @XmlElement(name = "family_name", required = true)
    @JsonProperty("familyName")
    @Column(name = "family_name")
    private String familyName;

    @XmlElement(name = "second_name")
    @JsonProperty("secondName")
    @Column(name = "second_name")
    private String secondName;

    @XmlElement(name = "birth_date")
    @JsonProperty("birthDate")
    @Column(name = "birth_date")
    private Date birthDate;

    public Author(Long id) {
        this.id = id;
    }
}