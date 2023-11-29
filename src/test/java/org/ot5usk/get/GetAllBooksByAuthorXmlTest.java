package org.ot5usk.get;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.ot5usk.models.negative_responses.NegativeResponse;

import static org.ot5usk.BaseTest.AuthorCreator.createNewAuthor;
import static org.ot5usk.steps.assertions.AssertsGetAllBooksByAuthorXml.verifyBooksListXml;
import static org.ot5usk.steps.assertions.NegativeAsserts.assertNegativeResponse;
import static org.ot5usk.steps.specifications.Specifications.*;

@Epic("GET tests .xml")
@Story("Get all books by author .xml")
public class GetAllBooksByAuthorXmlTest {

    private static final String authorId = createNewAuthor().getAuthorId().toString();

    @Tag("get")
    @Tag("positive")
    @DisplayName("Get all books by correct authorId")
    @Description("A list of all books by the author, status-code: 200")
    @ParameterizedTest(name = "bookTitle = {0}")
    @CsvFileSource(resources = "/test_cases/positive/book_titles.csv", encoding = "windows-1251")
    void testGetAllBooksByAuthor(String title) {
        requestSpecAddNewBook(title, Long.parseLong(authorId), 201);
        verifyBooksListXml(requestSpecGetAllBooksByAuthorXml(authorId, 200), authorId);
    }

    @Tag("get")
    @Tag("negative")
    @DisplayName("Get all books by incorrect authorId")
    @Description("status-code: 400")
    @ParameterizedTest(name = "authorId = {0}, statusCode = {1}, errCode = {2}, errMsg = {3}")
    @CsvFileSource(resources = "/test_cases/negative/get_all_books_by_author_xml.csv", encoding = "windows-1251")
    void withIncorrectAuthor(String incorrectAuthorId, Integer expStCode, String expErrCode, String expErrMsg, String expErrDetails) {
        NegativeResponse response = requestSpecGetAllBooksByIncorrectAuthorXml(incorrectAuthorId, expStCode);
        assertNegativeResponse(response, expErrCode, expErrMsg, expErrDetails);
    }
}
