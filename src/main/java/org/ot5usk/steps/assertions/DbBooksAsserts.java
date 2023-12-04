package org.ot5usk.steps.assertions;

import org.ot5usk.entities.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DbBooksAsserts {

    public static void assertBookListSize(int expectedSize, List<Book> currentBooks) {
        assertEquals(expectedSize, currentBooks.size());
    }
}
