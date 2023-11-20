package org.ot5usk.post;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.ot5usk.steps.assertions.AssertsAddNewAuthor.verifyAuthorId;
import static org.ot5usk.steps.specifications.Specifications.requestSpecAddNewAuthor;

@Epic("POST tests")
@Story("Adding new author")
public class AddNewAuthorTest {

    @DisplayName("Add new author")
    @Description("Should be return authorId, status-code: 200")
    @Test
    void testAddNewAuthor() {
        verifyAuthorId(requestSpecAddNewAuthor("Not", "Brains", ""));
    }
}