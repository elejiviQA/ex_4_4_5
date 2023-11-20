package org.ot5usk.steps.assertions;

import org.ot5usk.models.add_new_book.AddNewBookResponse;

import static org.junit.jupiter.api.Assertions.*;

public class AssertsAddNewBook {

    public static void verifyBookId(AddNewBookResponse response) {
        assertAll(
                () -> assertBookIdIsNotNull(response),
                () -> assertBookIdGreaterThanZero(response)
        );
    }

    public static void assertBookIdIsNotNull(AddNewBookResponse response) {
        assertNotNull(response.getBookId());
    }

    public static void assertBookIdGreaterThanZero(AddNewBookResponse response) {
        assertTrue(response.getBookId() > 0);
    }
}
