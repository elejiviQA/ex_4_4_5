package org.ot5usk.steps.assertions.lib_api_asserts;

import org.ot5usk.models.add_new_author.AddNewAuthorResponse;

import static org.junit.jupiter.api.Assertions.*;

public class AddNewAuthorAsserts {

    public static void assertExpectedAuthorId(AddNewAuthorResponse currentAuthor) {
        assertAll(() -> assertNotNull(currentAuthor.getAuthorId()),
                () -> assertTrue(currentAuthor.getAuthorId() > 0));
    }
}
