package org.ot5usk.models.add_new_book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddNewBookResponse {

    private long bookId;
}
