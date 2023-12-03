package org.ot5usk.post;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.models.add_new_author.AddNewAuthorResponse;
import static org.ot5usk.steps.assertions.AddNewAuthorAsserts.assertExpectedAuthorId;
import static org.ot5usk.steps.specifications.Specifications.*;
import static org.ot5usk.utils.RequestBuilder.buildAddnewAuthorRequest;

@Epic("POST tests")
@Story("Adding new author")
public class AddNewAuthorTest {

    private AddNewAuthorRequest expectedAuthor;
    private AddNewAuthorResponse currentAuthor;

    @BeforeAll
    static void auth() {
        executeAuth("test_log", "123qweasd");
    }

    @BeforeEach
    void buildNewAuthor() {
        expectedAuthor = buildAddnewAuthorRequest();
    }

    @Tag("post")
    @Tag("positive")
    @DisplayName("Full name")
    @Description("status-code: 201")
    @Test
    void withFullName() {
        currentAuthor = requestSpecAddNewAuthor(expectedAuthor, 201);
        assertExpectedAuthorId(currentAuthor);
    }

    @Tag("post")
    @Tag("positive")
    @DisplayName("Without second name")
    @Description("status-code: 201")
    @Test
    void withoutSecondName() {
        expectedAuthor.setSecondName(null);
        currentAuthor = requestSpecAddNewAuthor(expectedAuthor, 201);
        assertExpectedAuthorId(currentAuthor);
    }

    @Tag("post")
    @Tag("positive")
    @DisplayName("Without birth date")
    @Description("status-code: 201")
    @Test
    void withoutBirthDate() {
        expectedAuthor.setBirthDate(null);
        currentAuthor = requestSpecAddNewAuthor(expectedAuthor, 201);
        assertExpectedAuthorId(currentAuthor);
    }
}