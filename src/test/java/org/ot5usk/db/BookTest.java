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

import static org.ot5usk.steps.assertions.DbBooksAsserts.assertBookListSize;
import static org.ot5usk.steps.specifications.Specifications.executeAuth;
import static org.ot5usk.steps.specifications.Specifications.requestSpecAddNewAuthor;
import static org.ot5usk.utils.builders.RequestBuilder.buildAddnewAuthorRequest;
import static org.ot5usk.utils.builders.StringBuilder.randCorrectStringBySelectedLength;

public class BookTest {

    private static BookRepository bookRepository;
    private static AddNewAuthorResponse currentAuthor;

    @BeforeAll
    static void init() {
        bookRepository = new BookRepositoryImpl();
        bookRepository.cleanBooksTable();

        executeAuth("test_log", "123qweasd");
        AddNewAuthorRequest expectedAuthor = buildAddnewAuthorRequest();
        currentAuthor = requestSpecAddNewAuthor(expectedAuthor, 201);
    }

    //я правда не догоняю что сделал не так, не бейте сильно,пж
    @DisplayName("Simple book test")
    @Description("without customers")
    @Test
    void testDbBooks() {
        String firstExpBookTitle = randCorrectStringBySelectedLength(BookTitleLengthLimits.AVERAGE.getLength());
        String secondExpBookTitle = randCorrectStringBySelectedLength(BookTitleLengthLimits.AVERAGE.getLength());

        bookRepository.saveBook(firstExpBookTitle, currentAuthor.getAuthorId());
        bookRepository.saveBook(secondExpBookTitle, currentAuthor.getAuthorId());

        List<Book> currentBooks = bookRepository.getAllBooksByAuthorId(currentAuthor.getAuthorId());
        currentBooks.forEach(System.out::println);
        assertBookListSize(2, currentBooks);

        currentBooks = bookRepository.getAllBooksByTitle(firstExpBookTitle);
        currentBooks.forEach(System.out::println);
        assertBookListSize(1, currentBooks);

        bookRepository.removeBookByTitle(firstExpBookTitle);

        currentBooks = bookRepository.getAllBooksByAuthorId(currentAuthor.getAuthorId());
        currentBooks.forEach(System.out::println);
        assertBookListSize(1, currentBooks);
    }
}
