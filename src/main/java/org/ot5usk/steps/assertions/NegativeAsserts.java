package org.ot5usk.steps.assertions;

import org.ot5usk.models.negative_responses.DefaultNegativeResponse;

import static org.junit.jupiter.api.Assertions.*;

public class NegativeAsserts {

    public static void assertNegativeResponse(DefaultNegativeResponse response, String expErrCode, String expErrMsg, String expErrDtls) {
        assertAll(
                () -> assertEquals(expErrCode, response.getErrorCode()),
                () ->  assertEquals(expErrMsg, response.getErrorMessage()),
                () -> assertEquals(expErrDtls, response.getErrorDetails()));
    }
}