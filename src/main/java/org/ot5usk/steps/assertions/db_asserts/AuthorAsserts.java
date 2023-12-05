package org.ot5usk.steps.assertions.db_asserts;

import org.ot5usk.entities.Author;
import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.repository.AuthorRepository;
import org.ot5usk.repository.AuthorRepositoryImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.ot5usk.utils.builders.DateBuilder.convertToLocalDateViaMillisecond;

public class AuthorAsserts {

    private final AddNewAuthorRequest expectedAuthor;
    private final Long expectedAuthorId;
    private final Author currentAuthor;
    private static AuthorRepository authorRepository;

    public AuthorAsserts(AddNewAuthorRequest expectedAuthor, Long expectedAuthorId) {
        this.expectedAuthor = expectedAuthor;
        this.expectedAuthorId = expectedAuthorId;
        authorRepository = new AuthorRepositoryImpl();
        this.currentAuthor = authorRepository.getAuthorById(expectedAuthorId);
    }

    public static void assertDbExpectedAuthorName(AddNewAuthorRequest expectedAuthor, Author currentAuthor) {
        assertEquals(expectedAuthor, currentAuthor);
    }

    public void assertDbExpectedAuthorId() {
        assertNotNull(expectedAuthorId);
        assertTrue(expectedAuthorId > 0);
        assertEquals(Math.toIntExact(expectedAuthorId), currentAuthor.getId());
    }

    public void assertDbExpectedDbAuthorName() {
        assertEquals(expectedAuthor.getFirstName(), currentAuthor.getFirstName());
        assertEquals(expectedAuthor.getFamilyName(), currentAuthor.getFamilyName());
        if (expectedAuthor.getSecondName() == null) {
            assertNull(currentAuthor.getSecondName());
        } else {
            assertEquals(expectedAuthor.getSecondName(), currentAuthor.getSecondName());
        }
    }

    public void assertDbExpectedAuthorBirthDate() {
        LocalDate currentDate = convertToLocalDateViaMillisecond(currentAuthor.getBirthDate());
        assertEquals(expectedAuthor.getBirthDate(), currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
