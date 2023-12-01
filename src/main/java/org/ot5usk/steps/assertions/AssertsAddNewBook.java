package org.ot5usk.steps.assertions;

import org.ot5usk.models.add_new_author.AddNewAuthorResponse;
import org.ot5usk.models.add_new_book.AddNewBookRequest;
import org.ot5usk.models.add_new_book.AddNewBookResponse;
import org.ot5usk.models.get_all_books_by_author.GetAllBooksByAuthorResponse;
import org.ot5usk.utils.steps_executors.BookSender;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.ot5usk.steps.specifications.Specifications.requestSpecGetAllBooksByAuthor;

public class AssertsAddNewBook {

    private final List<GetAllBooksByAuthorResponse> books;
    private final AddNewBookRequest expBook;
    private final AddNewBookResponse currentBook;

    public AssertsAddNewBook(BookSender bookSender) {
        expBook = bookSender.getAddNewBookRequest();
        currentBook = bookSender.getAddNewBookResponse();
        AddNewAuthorResponse expAuthorTable = bookSender.getAddNewAuthorResponse();
        books = requestSpecGetAllBooksByAuthor(expAuthorTable.getAuthorId(), 200);
    }

    public void assertBookSenderGotPositiveResponseAfterSendingNewBook() {
        assertAll(
                () -> assertNotNull(currentBook.getBookId()),
                () -> assertTrue(currentBook.getBookId() > 0),
                this::assertExpectedTitle);
    }

    public void assertExpectedTitle() {
        assertTrue(books.stream().anyMatch(book -> book.getBookTitle().equals(expBook.getBookTitle())));
    }

    public void assertNumOfDuplicates(String expTitle, int numOfDuplicates) {
        assertBookSenderGotPositiveResponseAfterSendingNewBook();
        assertExpectedTitle();
        assertEquals(numOfDuplicates, books.stream().filter(book -> book.getBookTitle().equals(expTitle)).count());
    }
}
