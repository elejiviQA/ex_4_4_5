package org.ot5usk.steps.assertions;

import org.ot5usk.models.get_all_books_by_author_xml.GetAllBooksByAuthorResponseXml;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AssertsGetAllBooksByAuthorXml {
    public static void verifyBooks(GetAllBooksByAuthorResponseXml books) {
        assertBooksIsNotNull(books);
    }

    public static void assertBooksIsNotNull(GetAllBooksByAuthorResponseXml books) {
        assertNotNull(books);
    }
}
