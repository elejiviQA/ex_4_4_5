package org.ot5usk.models.negative_responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NegativeResponse {

    private String errorCode;
    private String errorMessage;
    private String errorDetails;
}