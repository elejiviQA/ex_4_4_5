package org.ot5usk.steps.assertions;

import org.ot5usk.models.negative_responses.DefaultNegativeResponse;

import static org.junit.jupiter.api.Assertions.*;

public class NegativeAsserts {

    public static void assertNegativeResponse(DefaultNegativeResponse defaultNegativeResponse, String expErrCode, String expErrMsg, String expErrDtls) {
        assertAll(
                () -> assertEquals(expErrCode, defaultNegativeResponse.getErrorCode()),
                () ->  assertEquals(expErrMsg, defaultNegativeResponse.getErrorMessage()),
                () -> assertEquals(expErrDtls, defaultNegativeResponse.getErrorDetails()));
    }
}