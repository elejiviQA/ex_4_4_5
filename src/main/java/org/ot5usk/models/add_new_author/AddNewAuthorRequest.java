package org.ot5usk.models.add_new_author;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddNewAuthorRequest {

    private String firstName;
    private String familyName;
    private String secondName;
}
