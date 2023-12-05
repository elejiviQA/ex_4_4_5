package org.ot5usk.models.negative_responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultNegativeResponse {

    private String errorCode;
    private String errorMessage;
    private String errorDetails;
}