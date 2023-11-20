package org.ot5usk.steps.assertions;

import org.ot5usk.models.add_new_author.AddNewAuthorResponse;

import static org.junit.jupiter.api.Assertions.*;

public class AssertsAddNewAuthor {

    public static void verifyAuthorId(AddNewAuthorResponse response) {
        assertAll(
                () -> assertAuthorIdIsNotNull(response),
                () -> assertAuthorIdGreaterThanZero(response)
        );
    }

    public static void assertAuthorIdIsNotNull(AddNewAuthorResponse response) {
        assertNotNull(response.getAuthorId());
    }

    public static void assertAuthorIdGreaterThanZero(AddNewAuthorResponse response) {
        assertTrue(response.getAuthorId() > 0);
    }
}
