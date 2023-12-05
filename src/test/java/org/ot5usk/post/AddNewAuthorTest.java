package org.ot5usk.post;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.ot5usk.entities.Author;
import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.models.add_new_author.AddNewAuthorResponse;
import org.ot5usk.models.negative_responses.DefaultNegativeResponse;
import org.ot5usk.repository.AuthorRepository;
import org.ot5usk.repository.AuthorRepositoryImpl;

import static org.ot5usk.steps.assertions.db_asserts.AuthorAsserts.*;
import static org.ot5usk.steps.assertions.lib_api_asserts.AddNewAuthorAsserts.assertExpectedAuthorId;
import static org.ot5usk.steps.assertions.lib_api_asserts.NegativeAsserts.assertNegativeResponse;
import static org.ot5usk.steps.specifications.Specifications.*;
import static org.ot5usk.utils.builders.RequestBuilder.buildAddnewAuthorRequest;

@Epic("POST tests")
@Story("Adding new author")
public class AddNewAuthorTest {

    private AddNewAuthorRequest expectedAuthor;
    private AddNewAuthorResponse currentAuthor;
    private final AuthorRepository authorRepository = new AuthorRepositoryImpl();

    @BeforeAll
    static void auth() {
        executeAuth("test_log", "123qweasd");
    }

    @BeforeEach
    void buildNewAuthor() {
        authorRepository.cleanAuthorTable();
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
        Author author = authorRepository.getAuthorById(currentAuthor.getAuthorId());
        assertDbExpectedAuthorName(expectedAuthor, author);
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
        Author author = authorRepository.getAuthorById(currentAuthor.getAuthorId());
        assertDbExpectedAuthorName(expectedAuthor, author);
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
        Author author = authorRepository.getAuthorById(currentAuthor.getAuthorId());
        assertDbExpectedAuthorName(expectedAuthor, author);
    }

    @Tag("post")
    @Tag("negative")
    @DisplayName("Without family name")
    @Description("status-code: 400")
    @Test
    void withoutFamilyName() {
        expectedAuthor.setFamilyName("");
        currentAuthor = requestSpecAddNewAuthor(expectedAuthor, 400);
    }

    @Tag("post")
    @Tag("negative")
    @DisplayName("Duplicate author")
    @Description("status-code: 400")
    @Test
    void withDuplicateAuthor() {
        currentAuthor = requestSpecAddNewAuthor(expectedAuthor, 201);
        assertExpectedAuthorId(currentAuthor);
        Author author = authorRepository.getAuthorById(currentAuthor.getAuthorId());
        assertDbExpectedAuthorName(expectedAuthor, author);
        DefaultNegativeResponse defaultNegativeResponse = requestSpecAddNewAuthorDuplicate(expectedAuthor,400);
        assertNegativeResponse(defaultNegativeResponse, "1002", "Указанный автор уже добавлен в базу данных", null);
    }
}