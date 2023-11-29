package org.ot5usk.steps.assertions;

import org.ot5usk.models.get_all_books_by_author_xml.GetAllBooksByAuthorResponseXml;

import static org.junit.jupiter.api.Assertions.*;

public class AssertsGetAllBooksByAuthorXml {
    public static void verifyBooksListXml(GetAllBooksByAuthorResponseXml xmlBooks, String expAuthorId) {
        assertAll(
                () -> assertTrue(xmlBooks.getBooks().stream().allMatch(book -> book.getAuthor().getId().equals(Long.parseLong(expAuthorId)))),
                () -> assertNotNull(xmlBooks.getBooks())
        );
    }


}
