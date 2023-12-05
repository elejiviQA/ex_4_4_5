package org.ot5usk.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import jakarta.xml.bind.annotation.*;
import java.util.Date;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
public class Author {

    @NonNull
    @XmlElement(name = "id", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NonNull
    @XmlElement(name = "first_name")
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @XmlElement(name = "family_name", required = true)
    @Column(name = "family_name")
    private String familyName;

    @XmlElement(name = "second_name")
    @Column(name = "second_name")
    private String secondName;



    @XmlElement(name = "birth_date")
    @JsonProperty("birthDate")
    @Column(name = "birth_date")
    private Date birthDate;

    @Override
    public boolean equals(Object o) {
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, familyName, secondName);
    }
}