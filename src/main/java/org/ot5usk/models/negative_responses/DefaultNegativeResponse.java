package org.ot5usk.models.negative_responses;

import lombok.Data;

@Data
public class DefaultNegativeResponse {

    private int errorCode;
    private String errorMessage;
    private String errorDetails;
}
