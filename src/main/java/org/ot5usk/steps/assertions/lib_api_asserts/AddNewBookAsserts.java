package org.ot5usk.steps.assertions.lib_api_asserts;

import org.ot5usk.models.add_new_book.AddNewBookResponse;

import static org.junit.jupiter.api.Assertions.*;

public class AddNewBookAsserts {

    public static void assertExpectedBookId(AddNewBookResponse currentBook) {
        assertAll(
                () -> assertNotNull(currentBook.getBookId()),
                () -> assertTrue(currentBook.getBookId() > 0));
    }
}
