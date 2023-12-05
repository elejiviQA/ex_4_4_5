package org.ot5usk.steps.assertions.db_asserts;

import org.ot5usk.entities.Book;
import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.models.add_new_book.AddNewBookResponse;
import org.ot5usk.repository.BookRepository;
import org.ot5usk.repository.BookRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DbBookAsserts {

    private static final BookRepository bookRepository = new BookRepositoryImpl();

    public static void assertDbExpectedBookListSize(int expectedBookListSize, List<Book> currentBooks) {
        assertEquals(expectedBookListSize, currentBooks.size());
    }

    public static void assertDbExpectedBookAuthor(AddNewAuthorRequest expectedAuthor, Long expectedAuthorId) {
        List<Book> currentBooks = bookRepository.getAllBooksByAuthorId(expectedAuthorId);
        currentBooks.forEach(currentBook -> {
            Long currentAuthorId = currentBook.getAuthorId();
            AuthorAsserts dbAuthorAsserts = new AuthorAsserts(expectedAuthor, currentAuthorId);
            dbAuthorAsserts.assertDbExpectedAuthorId();
            dbAuthorAsserts.assertDbExpectedDbAuthorName();
            dbAuthorAsserts.assertDbExpectedAuthorBirthDate();
        });
    }

    public static void assertDbExpectedBookTitle(List<String> expectedTitles, List<Book> currentBooks) {
        List<String> currentTitles = currentBooks.stream().map(Book::getBookTitle).toList();
        assertEquals(expectedTitles, currentTitles);
    }

    public static void assertDbExpectedBookId(AddNewBookResponse expectedBookResponse, List<Book> expectedBooks) {
        assertEquals(expectedBooks, bookRepository.getAllBooksById(expectedBookResponse.getBookId()));
    }

    public static void assertDbExpectedEmptyBookList(String expectedAuthorId) {
        try {
            Long id = Long.parseLong(expectedAuthorId);
            List<Book> expectedBooksList = bookRepository.getAllBooksByAuthorId(id);
            assertTrue(expectedBooksList.isEmpty());
        } catch (Exception e) {
            assertThrows(Exception.class, () -> bookRepository.getAllBooksByAuthorId(Long.valueOf(expectedAuthorId)));
        }
    }
}
