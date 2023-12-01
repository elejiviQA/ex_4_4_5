package org.ot5usk.utils.steps_executors;

import lombok.Getter;
import org.ot5usk.entities.AuthorTable;
import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.models.add_new_author.AddNewAuthorResponse;
import org.ot5usk.models.add_new_book.AddNewBookRequest;
import org.ot5usk.models.add_new_book.AddNewBookResponse;
import org.ot5usk.models.negative_responses.DefaultNegativeResponse;
import org.ot5usk.steps.assertions.AssertsAddNewBook;

import static org.ot5usk.steps.assertions.NegativeAsserts.assertNegativeResponse;
import static org.ot5usk.steps.specifications.Specifications.requestSpecAddNewBook;
import static org.ot5usk.steps.specifications.Specifications.requestSpecAddNewBookNegative;

@Getter
public class BookSender {

    private final AddNewAuthorRequest addNewAuthorRequest;
    private final AddNewAuthorResponse addNewAuthorResponse;
    private AddNewBookRequest addNewBookRequest;
    private AddNewBookResponse addNewBookResponse;
    private DefaultNegativeResponse negativeResponse;
    private AssertsAddNewBook senderChecker;
    private String expectedBookTitle;

    {
        addNewAuthorRequest = AuthorBuilder.buildRequest();
        addNewAuthorResponse = AuthorBuilder.addNewAuthor(addNewAuthorRequest);
    }

    public BookSender passThisBookSenderForAsserts() {
        return this;
    }

    public void sendBook(String bookTitle) {
        expectedBookTitle = bookTitle;
        buildRequest(bookTitle);
        addNewBook();
        senderChecker = new AssertsAddNewBook(passThisBookSenderForAsserts());
        senderChecker.assertNumOfDuplicates(bookTitle, 1);
        senderChecker.assertBookSenderGotPositiveResponseAfterSendingNewBook();
    }

    public void sendBook(String bookTitle, Integer expStCode, String expErrCode, String expErrMsg, String expErrDtls) {
        expectedBookTitle = bookTitle;
        buildRequest(bookTitle);
        addNewBook(expStCode);
        assertNegativeResponse(negativeResponse, expErrCode, expErrMsg, expErrDtls);
    }

    public void sendBook(String bookTitle, Long authorId, Integer expStCode, String expErrCode, String expErrMsg, String expErrDtls) {
        expectedBookTitle = bookTitle;
        buildRequest(bookTitle, authorId);
        addNewBook(expStCode);
        assertNegativeResponse(negativeResponse, expErrCode, expErrMsg, expErrDtls);
    }

    public void sendBook(String bookTitle, int numOfDuplicates) {
        expectedBookTitle = bookTitle;
        buildRequest(bookTitle);
        for (int i = 0; i < numOfDuplicates; i++) {
            addNewBook();
        }
        senderChecker = new AssertsAddNewBook(passThisBookSenderForAsserts());
        senderChecker.assertNumOfDuplicates(bookTitle, numOfDuplicates);
    }

    private void buildRequest(String bookTitle) {
        AuthorTable authorTable = new AuthorTable(addNewAuthorResponse.getAuthorId());
        addNewBookRequest = new AddNewBookRequest(bookTitle, authorTable);
    }

    private void buildRequest(String bookTitle, Long authorId) {
        AuthorTable authorTable = new AuthorTable(authorId);
        addNewBookRequest = new AddNewBookRequest(bookTitle, authorTable);
    }

    private void addNewBook() {
        addNewBookResponse = requestSpecAddNewBook(addNewBookRequest, 201);
    }

    private void addNewBook(Integer expNegativeStCode) {
        negativeResponse = requestSpecAddNewBookNegative(addNewBookRequest, expNegativeStCode);
    }
}
