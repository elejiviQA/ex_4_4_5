package org.ot5usk.steps.assertions;

import org.ot5usk.entities.Book;
import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.repository.BookRepository;
import org.ot5usk.repository.BookRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DbBookAsserts {

    public static void assertExpectedBookListSize(int expectedBookListSize, List<Book> currentBooks) {
        assertEquals(expectedBookListSize, currentBooks.size());
    }

    public static void assertExpectedBookAuthor(AddNewAuthorRequest expectedAuthor, Long expectedAuthorId) {
        BookRepository bookRepository = new BookRepositoryImpl();
        List<Book> currentBooks = bookRepository.getAllBooksByAuthorId(expectedAuthorId);
        currentBooks.forEach(currentBook -> {
            Long currentAuthorId = currentBook.getAuthorId();
            DbAuthorAsserts dbAuthorAsserts = new DbAuthorAsserts(expectedAuthor, currentAuthorId);
            dbAuthorAsserts.assertExpectedAuthorId();
            dbAuthorAsserts.assertExpectedAuthorName();
            dbAuthorAsserts.assertExpectedAuthorBirthDate();
        });
    }

    public static void assertExpectedBookTitle(List<String> expectedTitles, List<Book> currentBooks) {
        List<String> currentTitles = currentBooks.stream().map(Book::getBookTitle).toList();
        assertEquals(expectedTitles, currentTitles);
    }
}
