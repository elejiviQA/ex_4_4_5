package org.ot5usk.steps.assertions;

import org.ot5usk.entities.Author;
import org.ot5usk.entities.Book;
import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.models.add_new_author.AddNewAuthorResponse;
import org.ot5usk.repository.AuthorRepository;
import org.ot5usk.repository.AuthorRepositoryImpl;
import org.ot5usk.repository.BookRepository;
import org.ot5usk.repository.BookRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DbAuthorAsserts {

    private final AddNewAuthorRequest expectedAuthor;
    private final Long expectedAuthorId;
    private final Author currentAuthor;

    public DbAuthorAsserts(AddNewAuthorRequest expectedAuthor, Long expectedAuthorId) {
        this.expectedAuthor = expectedAuthor;
        this.expectedAuthorId = expectedAuthorId;
        AuthorRepository authorRepository = new AuthorRepositoryImpl();
        this.currentAuthor = authorRepository.getAuthorById(expectedAuthorId);
    }

    public void assertExpectedAuthorId() {
        assertNotNull(expectedAuthorId);
        assertTrue(expectedAuthorId > 0);
        assertEquals(expectedAuthorId, currentAuthor.getId());
    }

    public void assertExpectedAuthorName() {
        assertEquals(expectedAuthor.getFirstName(), currentAuthor.getFirstName());
        assertEquals(expectedAuthor.getFamilyName(), currentAuthor.getFamilyName());
        if (expectedAuthor.getSecondName() == null) {
            assertNull(currentAuthor.getSecondName());
        } else {
            assertEquals(expectedAuthor.getSecondName(), currentAuthor.getFirstName());
        }
    }

    public void assertExpectedAuthorBirthDate() {
        assertEquals(expectedAuthor.getBirthDate(), currentAuthor.getBirthDate());
    }

    public void assertExpectedEmptyBookList(Long expectedAuthorId) {
        BookRepository bookRepository = new BookRepositoryImpl();
        List<Book> actualBooksList = bookRepository.getAllBooksByAuthorId(expectedAuthorId);
        assertTrue(actualBooksList.isEmpty());
    }
}
