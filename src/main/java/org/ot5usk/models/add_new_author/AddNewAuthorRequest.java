package org.ot5usk.models.add_new_author;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddNewAuthorRequest {

    @NonNull
    private String firstName;

    private String familyName;
    private String secondName;
    private String birthDate;
}
