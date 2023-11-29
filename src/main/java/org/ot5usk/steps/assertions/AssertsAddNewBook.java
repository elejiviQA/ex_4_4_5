package org.ot5usk.steps.assertions;

import org.ot5usk.models.add_new_book.AddNewBookResponse;
import org.ot5usk.models.get_all_books_by_author.GetAllBooksByAuthorResponse;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;
import static org.ot5usk.steps.specifications.Specifications.requestSpecGetAllBooksByAuthor;

public class AssertsAddNewBook {
    private static List<GetAllBooksByAuthorResponse> books;
    public static void assertPositiveResponseAddNewBook(AddNewBookResponse response, String expTitle, Long expAuthorId) {
        books = requestSpecGetAllBooksByAuthor(expAuthorId, 200);
        AtomicBoolean expTitleIsHere = new AtomicBoolean(false);
        books.forEach(book -> {
            if(book.getBookTitle().equals(expTitle)) {
                expTitleIsHere.set(true);
            }
        });
        assertAll(
                () -> assertNotNull(response.getBookId()),
                () -> assertTrue(response.getBookId() > 0),
                () -> assertTrue(expTitleIsHere.get()));
    }

    public static void assertPositiveResponseAddDuplicateBook(AddNewBookResponse response, String expTitle, Long expAuthorId) {
        assertPositiveResponseAddNewBook(response, expTitle, expAuthorId);
        assertTrue(books.stream().filter(book -> book.getBookTitle().equals(expTitle)).count() > 1);
    }
}
