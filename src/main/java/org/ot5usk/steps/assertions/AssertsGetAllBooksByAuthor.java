package org.ot5usk.steps.assertions;

import lombok.Getter;
import org.ot5usk.entities.AuthorTable;
import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.models.get_all_books_by_author.GetAllBooksByAuthorResponse;
import org.ot5usk.models.get_all_books_by_author_xml.GetAllBooksByAuthorResponseXml;
import org.ot5usk.utils.steps_executors.BookRecipient;
import org.ot5usk.utils.steps_executors.BookSender;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.ot5usk.utils.creators.string_creator.StringCreator.epxBookTitlesLength;

@Getter
public class AssertsGetAllBooksByAuthor {

    private final List<GetAllBooksByAuthorResponse> currentBooks;
    private final AddNewAuthorRequest expectedAuthor;
    private final Long expectedAuthorId;
    private static List<String> expectedTitles = new ArrayList<>();
    private static List<String> currentTitles = new ArrayList<>();
    private AuthorTable currentAuthor;

    public AssertsGetAllBooksByAuthor(BookSender bookSender, BookRecipient bookRecipient) {
        if (expectedTitles.size() == epxBookTitlesLength) {
            currentTitles = new ArrayList<>();
            expectedTitles = new ArrayList<>();
        }
        this.currentBooks = bookRecipient.getBooks();
        this.expectedAuthor = bookSender.getAddNewAuthorRequest();
        this.expectedAuthorId = bookSender.getAddNewAuthorResponse().getAuthorId();
        expectedTitles.add(bookSender.getAddNewBookRequest().getBookTitle());
        currentTitles = getCurrentTitles();
    }

    public AssertsGetAllBooksByAuthor(BookSender bookSender, GetAllBooksByAuthorResponseXml booksXml) {
        if (expectedTitles.size() == epxBookTitlesLength) {
            currentTitles = new ArrayList<>();
            expectedTitles = new ArrayList<>();
        }
        this.currentBooks = booksXml.getBooks();
        this.expectedAuthor = bookSender.getAddNewAuthorRequest();
        this.expectedAuthorId = bookSender.getAddNewAuthorResponse().getAuthorId();
        expectedTitles.add(bookSender.getAddNewBookRequest().getBookTitle());
        currentTitles = getCurrentTitles();
    }

    public List<String> getCurrentTitles() {
        currentBooks.forEach(book -> currentTitles.add(book.getBookTitle()));
        return currentTitles;
    }

    public void mainAssert() {
        assertAll(() -> assertNotNull(currentBooks),
                this::assertAllExpectedBooksOfOnlyExpectedAuthorId,
                this::assertExpectedAuthorNames,
                this::assertExpectedNumOfBooks,
                this::assertExpectedBookTitle);
    }

    public void assertAllExpectedBooksOfOnlyExpectedAuthorId() {
        assertTrue(currentBooks.stream().allMatch(book -> book.getAuthor().getId().equals(expectedAuthorId)));
    }

    public void assertExpectedAuthorNames() {
        currentBooks.forEach(book -> {
            currentAuthor = book.getAuthor();
            assertEquals(expectedAuthor.getFirstName(), currentAuthor.getFirstName());
            assertEquals(expectedAuthor.getFamilyName(), currentAuthor.getFamilyName());
            assertEquals(expectedAuthor.getSecondName(), currentAuthor.getSecondName());
        });
    }

    public void assertExpectedNumOfBooks() {
        assertEquals(expectedTitles.size(),currentBooks.size());
    }

    public void assertExpectedBookTitle() {
        expectedTitles.forEach(expectedTitle -> assertTrue(currentTitles.contains(expectedTitle)));
    }
}
