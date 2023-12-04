package org.ot5usk.get;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.models.add_new_author.AddNewAuthorResponse;
import org.ot5usk.models.add_new_book.AddNewBookRequest;
import org.ot5usk.models.get_all_books_by_author.GetAllBooksByAuthorResponse;
import org.ot5usk.models.negative_responses.DefaultNegativeResponse;
import org.ot5usk.steps.assertions.GetAllBooksByAuthorAsserts;

import java.util.Date;
import java.util.List;

import static org.ot5usk.steps.assertions.NegativeAsserts.assertNegativeResponse;
import static org.ot5usk.steps.specifications.Specifications.*;
import static org.ot5usk.utils.builders.RequestBuilder.buildAddNewBookRequest;
import static org.ot5usk.utils.builders.RequestBuilder.buildAddnewAuthorRequest;

@Epic("GET tests")
@Story("Get all books by author")
public class GetAllBooksByAuthorTest {

    private static AddNewAuthorRequest expectedAuthor;
    private static AddNewAuthorResponse currentAuthor;

    public void executeAsserts(GetAllBooksByAuthorAsserts asserts) {
        asserts.assertExpectedAuthorId();
        asserts.assertExpectedBookTitles();
        asserts.assertExpectedAuthorName();
        asserts.assertExpectedNumOfBooks();
        asserts.assertExpectedUpdated();
        asserts.assertExpectedAuthorBirthDate();
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
    void testGetAllBooksByCorrectAuthor(String title) {
        AddNewBookRequest expectedBook = buildAddNewBookRequest(title, currentAuthor.getAuthorId());
        System.currentTimeMillis();
        requestSpecAddNewBook(expectedBook, 201);
        System.currentTimeMillis();
        long approximateUpdatedTime = new Date().getTime();
        List<GetAllBooksByAuthorResponse> currentBooks = requestSpecGetAllBooksByAuthor(currentAuthor.getAuthorId(), 200);
        GetAllBooksByAuthorAsserts asserts = new GetAllBooksByAuthorAsserts(expectedBook, expectedAuthor, currentAuthor, currentBooks, approximateUpdatedTime);
        executeAsserts(asserts);
    }

    @Tag("get")
    @Tag("negative")
    @DisplayName("Incorrect authorId")
    @Description("status-code: 400")
    @ParameterizedTest(name = "authorId = {0}, statusCode = {1}, errCode = {2}, errMsg = {3}")
    @CsvFileSource(resources = "/test_cases/negative/get_all_books/by_incorrect_author_id.csv")
    void withIncorrectAuthor(String incorrectAuthorId, Integer expStCode, String expErrCode, String expErrMsg, String expErrDetails) {
        DefaultNegativeResponse defaultNegativeResponse = requestSpecGetAllBookByIncorrectAuthor(incorrectAuthorId, expStCode);
        assertNegativeResponse(defaultNegativeResponse, expErrCode, expErrMsg, expErrDetails);
    }
}