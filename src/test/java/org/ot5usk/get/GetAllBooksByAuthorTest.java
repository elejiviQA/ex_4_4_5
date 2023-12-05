package org.ot5usk.get;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.ot5usk.entities.Book;
import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.models.add_new_author.AddNewAuthorResponse;
import org.ot5usk.models.add_new_book.AddNewBookRequest;
import org.ot5usk.models.add_new_book.AddNewBookResponse;
import org.ot5usk.models.get_all_books_by_author.GetAllBooksByAuthorResponse;
import org.ot5usk.models.negative_responses.DefaultNegativeResponse;
import org.ot5usk.repository.BookRepository;
import org.ot5usk.repository.BookRepositoryImpl;
import org.ot5usk.steps.assertions.lib_api_asserts.GetAllBooksByAuthorAsserts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.ot5usk.steps.assertions.db_asserts.DbBookAsserts.*;
import static org.ot5usk.steps.assertions.lib_api_asserts.GetAllBooksByAuthorAsserts.assertExpectedEmptyList;
import static org.ot5usk.steps.assertions.lib_api_asserts.NegativeAsserts.assertNegativeResponse;
import static org.ot5usk.steps.specifications.Specifications.*;
import static org.ot5usk.utils.builders.RequestBuilder.buildAddNewBookRequest;
import static org.ot5usk.utils.builders.RequestBuilder.buildAddnewAuthorRequest;

@Epic("GET tests")
@Story("Get all books by author")
public class GetAllBooksByAuthorTest {

    private static AddNewAuthorRequest expectedAuthor ;
    private static AddNewAuthorResponse currentAuthor;
    private static AddNewBookResponse expectedBookResponse;
    private static final List<String> expectedBookTitles = new ArrayList<>();
    private static final BookRepository bookRepository = new BookRepositoryImpl();

    private void executeLibApiAsserts(GetAllBooksByAuthorAsserts libAsserts) {
        libAsserts.assertExpectedAuthorId();
        libAsserts.assertExpectedBookTitles();
        libAsserts.assertExpectedAuthorName();
        libAsserts.assertExpectedNumOfBooks();
        libAsserts.assertExpectedUpdated();
        libAsserts.assertExpectedAuthorBirthDate();
    }

    private void executeDbAsserts() {
        List<Book> currentBooks = bookRepository.getAllBooksByAuthorId(currentAuthor.getAuthorId());
        assertDbExpectedBookTitle(expectedBookTitles, currentBooks);
        assertDbExpectedBookAuthor(expectedAuthor, currentAuthor.getAuthorId());
        assertDbExpectedBookId(expectedBookResponse, currentBooks);
    }

    @BeforeAll
    static void auth() {
        executeAuth("test_log", "123qweasd");
        expectedAuthor = buildAddnewAuthorRequest();
        currentAuthor = requestSpecAddNewAuthor(expectedAuthor, 201);
    }

    @Tag("get")
    @Tag("positive")
    @DisplayName("Correct authorId")
    @Description("status-code: 200")
    @ParameterizedTest(name = "bookTitle = {0}")
    @CsvFileSource(resources = "/test_cases/positive/correct_book_titles_values.csv")
    void byCorrectAuthorWithBooks(String title) {
        expectedBookTitles.add(title);
        AddNewBookRequest expectedBook = buildAddNewBookRequest(title, currentAuthor.getAuthorId());
        expectedBookResponse = requestSpecAddNewBook(expectedBook, 201);
        long approximateUpdatedTime = new Date().getTime();
        List<GetAllBooksByAuthorResponse> currentBooksResponse = requestSpecGetAllBooksByAuthor(currentAuthor.getAuthorId(), 200);
        GetAllBooksByAuthorAsserts libAsserts = new GetAllBooksByAuthorAsserts(expectedBook, expectedAuthor, currentAuthor, currentBooksResponse, approximateUpdatedTime);
        executeLibApiAsserts(libAsserts);
        executeDbAsserts();
    }

    @Tag("get")
    @Tag("positive")
    @DisplayName("Correct author without books")
    @Description("status-code: 200")
    @Test
    void byCorrectAuthorWithoutBooks() {
        AddNewAuthorRequest expectedAuthor = buildAddnewAuthorRequest();
        AddNewAuthorResponse currentAuthor = requestSpecAddNewAuthor(expectedAuthor, 201);
        List<GetAllBooksByAuthorResponse> currentBooksResponse = requestSpecGetAllBooksByAuthor(currentAuthor.getAuthorId(), 200);
        assertExpectedEmptyList(currentBooksResponse);
        assertDbExpectedEmptyBookList(currentAuthor.getAuthorId().toString());
    }

    @Tag("get")
    @Tag("negative")
    @DisplayName("Incorrect authorId")
    @Description("status-code: 400")
    @ParameterizedTest(name = "authorId = {0}, statusCode = {1}, errCode = {2}, errMsg = {3}")
    @CsvFileSource(resources = "/test_cases/negative/get_all_books/by_incorrect_author_id.csv")
    void byIncorrectAuthor(String incorrectAuthorId, Integer expStCode, String expErrCode, String expErrMsg, String expErrDetails) {
        DefaultNegativeResponse defaultNegativeResponse = requestSpecGetAllBookByIncorrectAuthor(incorrectAuthorId, expStCode);
        assertNegativeResponse(defaultNegativeResponse, expErrCode, expErrMsg, expErrDetails);
        assertDbExpectedEmptyBookList(incorrectAuthorId);
    }
}