package org.ot5usk.post;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.ot5usk.steps.assertions.AssertsAddNewBook.verifyBookId;
import static org.ot5usk.steps.specifications.Specifications.requestSpecAddNewBook;

@Epic("POST tests")
@Story("Adding new book")
public class AddNewBookTest {

    @DisplayName("Add new book")
    @Description("Should be return bookId, status-code: 200")
    @Test
    void testAddNewBook() {
        verifyBookId(requestSpecAddNewBook("NotBrains", 1L));
    }
}
