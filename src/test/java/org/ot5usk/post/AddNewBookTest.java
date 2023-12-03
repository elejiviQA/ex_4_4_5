package org.ot5usk.post;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.models.add_new_author.AddNewAuthorResponse;
import org.ot5usk.models.add_new_book.AddNewBookRequest;
import org.ot5usk.models.add_new_book.AddNewBookResponse;
import org.ot5usk.models.negative_responses.DefaultNegativeResponse;
import org.ot5usk.utils.BookTitleLengthLimits;

import static org.ot5usk.steps.assertions.AddNewBookAsserts.assertExpectedBookId;
import static org.ot5usk.steps.assertions.NegativeAsserts.assertNegativeResponse;
import static org.ot5usk.steps.specifications.Specifications.*;
import static org.ot5usk.utils.RequestBuilder.buildAddNewBookRequest;
import static org.ot5usk.utils.RequestBuilder.buildAddnewAuthorRequest;
import static org.ot5usk.utils.StringBuilder.randCorrectStringBySelectedLength;

@Epic("POST tests")
@Story("Adding new book")
public class AddNewBookTest {

    private AddNewAuthorResponse currentAuthor;
    private AddNewBookRequest expectedBook;
    private AddNewBookResponse currentBook;
    private DefaultNegativeResponse defaultNegativeResponse;
    private String expectedBookTitle;

    @BeforeAll
    static void auth() {
        executeAuth("test_log", "123qweasd");
    }

    @BeforeEach
    public void init() {
        AddNewAuthorRequest expectedAuthor = buildAddnewAuthorRequest();
        currentAuthor = requestSpecAddNewAuthor(expectedAuthor, 201);
    }

    @Tag("post")
    @Tag("positive")
    @DisplayName("Title length limits")
    @Description("status-code: 201")
    @ParameterizedTest
    @EnumSource(BookTitleLengthLimits.class)
    public void withTitleLengthLimits(BookTitleLengthLimits limits) {
        expectedBookTitle = randCorrectStringBySelectedLength(limits.getLength());
        expectedBook = buildAddNewBookRequest(expectedBookTitle, currentAuthor.getAuthorId());
        currentBook = requestSpecAddNewBook(expectedBook, 201);
        assertExpectedBookId(currentBook);
    }

    @Tag("post")
    @Tag("positive")
    @DisplayName("All fields filled correct")
    @Description("status-code: 201")
    @ParameterizedTest(name = "title: {0}")
    @CsvFileSource(resources = "/test_cases/positive/correct_book_titles_values.csv")
    public void withCorrectTitleChars(String correctTitle) {
        expectedBookTitle = correctTitle;
        expectedBook = buildAddNewBookRequest(expectedBookTitle, currentAuthor.getAuthorId());
        currentBook = requestSpecAddNewBook(expectedBook, 201);
        assertExpectedBookId(currentBook);
    }

    @Tag("post")
    @Tag("positive")
    @DisplayName("Duplicate book")
    @Description("status-code: 201")
    @Test
    public void withCorrectDuplicateTitles() {
        expectedBookTitle = randCorrectStringBySelectedLength(BookTitleLengthLimits.AVERAGE.getLength());
        expectedBook = buildAddNewBookRequest(expectedBookTitle, currentAuthor.getAuthorId());
        currentBook = requestSpecAddNewBook(expectedBook, 201);
        assertExpectedBookId(currentBook);
        currentBook = requestSpecAddNewBook(expectedBook, 201);
        assertExpectedBookId(currentBook);
    }

    @Tag("post")
    @Tag("negative")
    @DisplayName("Incorrect title length")
    @Description("status-code: 400")
    @Test
    public void withIncorrectTitleLength() {
        expectedBookTitle = randCorrectStringBySelectedLength(BookTitleLengthLimits.MAX.getLength() + 1);
        expectedBook = buildAddNewBookRequest(expectedBookTitle, currentAuthor.getAuthorId());
        defaultNegativeResponse = requestSpecAddNewBookNegative(expectedBook, 400);
        assertNegativeResponse(defaultNegativeResponse, "1001", "Некорректный размер поля firstName", "Валидация не пройдена");
    }

    @Tag("maybebugs")
    @Tag("post")
    @Tag("negative")
    @DisplayName("Another negative test cases")
    @Description("look at file: /test_cases/negative/by_different_incorrect_values.csv")
    @ParameterizedTest(name = "bookTitle = {0}, authorId = {1}")
    @CsvFileSource(resources = "/test_cases/negative/add_new_book/by_different_incorrect_values.csv")
    public void withAnotherIncorrectValues(String title, Long authorId, Integer expStCode, String expErrCode, String expErrMsg, String expErrDtls) {
        expectedBookTitle = title;
        currentAuthor.setAuthorId(authorId);
        expectedBook = buildAddNewBookRequest(expectedBookTitle, currentAuthor.getAuthorId());
        defaultNegativeResponse = requestSpecAddNewBookNegative(expectedBook, expStCode);
        assertNegativeResponse(defaultNegativeResponse, expErrCode, expErrMsg, expErrDtls);
    }
}