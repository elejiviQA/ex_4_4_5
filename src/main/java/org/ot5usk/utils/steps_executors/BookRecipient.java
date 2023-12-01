package org.ot5usk.utils.steps_executors;

import lombok.Getter;
import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.models.get_all_books_by_author.GetAllBooksByAuthorResponse;
import org.ot5usk.models.get_all_books_by_author_xml.GetAllBooksByAuthorResponseXml;
import org.ot5usk.models.negative_responses.DefaultNegativeResponse;
import org.ot5usk.steps.assertions.AssertsGetAllBooksByAuthor;
import org.ot5usk.steps.assertions.AssertsGetAllBooksByAuthorXml;

import java.util.List;

import static org.ot5usk.steps.assertions.NegativeAsserts.assertNegativeResponse;
import static org.ot5usk.steps.specifications.Specifications.*;

@Getter
public class BookRecipient {

    private Long receivedAuthorId;
    private AddNewAuthorRequest enteredAuthor;
    private List<GetAllBooksByAuthorResponse> books;
    private AssertsGetAllBooksByAuthor mainBooksChecker;
    private DefaultNegativeResponse negativeResponse;
    private GetAllBooksByAuthorResponseXml xmlBooks;
    private DefaultNegativeResponse defaultNegativeResponse;
    private AssertsGetAllBooksByAuthorXml booksCheckerXml;

    private BookRecipient passThisBookRecipientForAsserts() {
        return this;
    }

    private void saveInfoAboutAddNewAuthor(BookSender bookSender) {
        enteredAuthor = bookSender.getAddNewAuthorRequest();
        receivedAuthorId = bookSender.getAddNewAuthorResponse().getAuthorId();
    }
    public void getAllBooksByAuthor(BookSender bookSender, Integer expStCode) {
        saveInfoAboutAddNewAuthor(bookSender);
        books = requestSpecGetAllBooksByAuthor(receivedAuthorId, expStCode);
        mainBooksChecker = new AssertsGetAllBooksByAuthor(bookSender.passThisBookSenderForAsserts(), passThisBookRecipientForAsserts());
        mainBooksChecker.mainAssert();
    }

    public void getAllBooksByAuthor(String incorrectAuthorId, Integer expStCode, String expErrCode, String expErrMsg, String expErrDetails) {
        negativeResponse = requestSpecGetAllBookByIncorrectAuthor(incorrectAuthorId, expStCode);
        assertNegativeResponse(negativeResponse, expErrCode, expErrMsg, expErrDetails);
    }

    public void getAllBooksByAuthorXml(BookSender bookSender, Integer expStCode) {
        saveInfoAboutAddNewAuthor(bookSender);
        requestSpecAddNewBook(bookSender.getAddNewBookRequest(), 201);
        xmlBooks = requestSpecGetAllBooksByAuthorXml(receivedAuthorId.toString(), expStCode);
        booksCheckerXml = new AssertsGetAllBooksByAuthorXml(bookSender.passThisBookSenderForAsserts(), xmlBooks);
        booksCheckerXml.mainAssert();
    }

    public void getAllBooksByAuthorXml(String incorrectAuthorId, Integer expStCode, String expErrCode, String expErrMsg, String expErrDetails) {
        defaultNegativeResponse = requestSpecGetAllBooksByIncorrectAuthorXml(incorrectAuthorId, expStCode);
        assertNegativeResponse(defaultNegativeResponse, expErrCode, expErrMsg, expErrDetails);
    }
}
