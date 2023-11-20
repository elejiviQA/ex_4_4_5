package org.ot5usk.get;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.ot5usk.steps.assertions.AssertsGetAllBooksByAuthor.verifyBooksList;
import static org.ot5usk.steps.specifications.Specifications.requestSpecGetAllBooksByAuthor;

@Epic("GET tests")
@Story("Get all books by author")
public class GetAllBooksByAuthorTest {

    @DisplayName("Get all books by authorId")
    @Description("A list of all books by the author, status-code: 200")
    @Test
    void testGetAllBooksByAuthor() {
        verifyBooksList(requestSpecGetAllBooksByAuthor("1"));
    }
}
