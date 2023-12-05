package org.ot5usk.models.add_new_author;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddNewAuthorRequest {

    @NonNull
    private String firstName;

    @NonNull
    private String familyName;

    private String secondName;
    private String birthDate;

    @Override
    public boolean equals(Object o) {
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, familyName, secondName);
    }
}
