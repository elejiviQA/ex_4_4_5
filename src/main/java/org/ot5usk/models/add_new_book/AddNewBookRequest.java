package org.ot5usk.models.add_new_book;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.ot5usk.entities.Author;

import java.util.Objects;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddNewBookRequest {

    private String bookTitle;
    private Author author;

    @Override
    public boolean equals(Object o) {
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash( bookTitle, author.getId());
    }
}