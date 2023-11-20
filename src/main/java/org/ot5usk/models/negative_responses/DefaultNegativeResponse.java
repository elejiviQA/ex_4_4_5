package org.ot5usk.models.negative_responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DefaultNegativeResponse {

    private Integer errorCode;
    private String errorMessage;
    private String errorDetails;
}