package org.ot5usk.get;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.ot5usk.utils.steps_executors.BookRecipient;
import org.ot5usk.utils.steps_executors.BookSender;

@Epic("GET tests .xml")
@Story("Get all books by author .xml")
public class GetAllBooksByAuthorXmlTest {

    private static final BookSender bookSender = new BookSender();
    private static final BookRecipient bookRecipient = new BookRecipient();

    @Tag("maybebugs")
    @Tag("get")
    @Tag("positive")
    @DisplayName("Get all books by correct authorId")
    @Description("A list of all books by the author, status-code: 200")
    @ParameterizedTest(name = "bookTitle = {0}")
    @CsvFileSource(resources = "/test_cases/positive/correct_book_titles_values.csv")
    void testGetAllBooksByAuthorXml(String title) {
        bookSender.sendBook(title);
        bookRecipient.getAllBooksByAuthorXml(bookSender, 200);
    }

    @Tag("maybebugs")
    @Tag("get")
    @Tag("negative")
    @DisplayName("Get all books by incorrect authorId")
    @Description("status-code: 400")
    @ParameterizedTest(name = "authorId = {0}, statusCode = {1}, errCode = {2}, errMsg = {3}")
    @CsvFileSource(resources = "/test_cases/negative/get_all_books/by_incorrect_author_Id_xml.csv")
    void withIncorrectAuthor(String incorrectAuthorId, Integer expStCode, String expErrCode, String expErrMsg, String expErrDetails) {
        bookRecipient.getAllBooksByAuthorXml(incorrectAuthorId, expStCode, expErrCode,expErrMsg, expErrDetails);
    }
}
