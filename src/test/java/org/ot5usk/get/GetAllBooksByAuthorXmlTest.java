package org.ot5usk.get;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.ot5usk.steps.assertions.AssertsGetAllBooksByAuthorXml.verifyBooks;
import static org.ot5usk.steps.specifications.Specifications.requestSpecGetAllBooksByAuthorXml;

@Epic("GET tests .xml")
@Story("Get all books by author .xml")
public class GetAllBooksByAuthorXmlTest {

    @DisplayName("Get all books by authorId .xml")
    @Description("Should be return books by author, status-code: 200")
    @Test
    void testGetAllBooksByAuthorXml() {
        verifyBooks(requestSpecGetAllBooksByAuthorXml(1));
    }
}
