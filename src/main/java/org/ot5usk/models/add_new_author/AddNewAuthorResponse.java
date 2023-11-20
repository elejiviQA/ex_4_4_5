package org.ot5usk.models.add_new_author;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddNewAuthorResponse {

    private Long authorId;
}