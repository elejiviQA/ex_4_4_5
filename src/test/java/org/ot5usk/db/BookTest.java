package org.ot5usk.db;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ot5usk.entities.Book;
import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.models.add_new_author.AddNewAuthorResponse;
import org.ot5usk.repository.BookRepository;
import org.ot5usk.repository.BookRepositoryImpl;
import org.ot5usk.utils.builders.BookTitleLengthLimits;

import java.util.List;

import static org.ot5usk.steps.assertions.db_asserts.DbBookAsserts.assertDbExpectedBookListSize;
import static org.ot5usk.steps.specifications.Specifications.executeAuth;
import static org.ot5usk.steps.specifications.Specifications.requestSpecAddNewAuthor;
import static org.ot5usk.utils.builders.RequestBuilder.buildAddnewAuthorRequest;
import static org.ot5usk.utils.builders.StringBuilder.randCorrectStringBySelectedLength;

public class BookTest {

    private static BookRepository bookRepository;
    private static AddNewAuthorResponse expectedAuthorResponse;

    @BeforeAll
    static void init() {
        bookRepository = new BookRepositoryImpl();
        bookRepository.cleanBookTable();

        executeAuth("test_log", "123qweasd");
        AddNewAuthorRequest expectedAuthor = buildAddnewAuthorRequest();
        expectedAuthorResponse = requestSpecAddNewAuthor(expectedAuthor, 201);
    }

    @DisplayName("Simple book test")
    @Description("check saving and removing books")
    @Test
    void testDbBooks() {
        String firstExpBookTitle = randCorrectStringBySelectedLength(BookTitleLengthLimits.AVERAGE.getLength());
        String secondExpBookTitle = randCorrectStringBySelectedLength(BookTitleLengthLimits.AVERAGE.getLength());

        bookRepository.saveBook(firstExpBookTitle, expectedAuthorResponse.getAuthorId());
        bookRepository.saveBook(secondExpBookTitle, expectedAuthorResponse.getAuthorId());

        List<Book> currentBooks = bookRepository.getAllBooksByAuthorId(expectedAuthorResponse.getAuthorId());
        currentBooks.forEach(System.out::println);
        assertDbExpectedBookListSize(2, currentBooks);

        currentBooks = bookRepository.getAllBooksByTitle(firstExpBookTitle);
        currentBooks.forEach(System.out::println);
        assertDbExpectedBookListSize(1, currentBooks);

        bookRepository.removeBookByTitle(firstExpBookTitle);

        currentBooks = bookRepository.getAllBooksByAuthorId(expectedAuthorResponse.getAuthorId());
        currentBooks.forEach(System.out::println);
        assertDbExpectedBookListSize(1, currentBooks);
    }
}
