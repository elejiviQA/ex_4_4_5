package org.ot5usk.post;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.ot5usk.models.negative_responses.NegativeResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.ot5usk.BaseTest.AuthorCreator.getNewAuthorId;
import static org.ot5usk.steps.assertions.AssertsAddNewBook.*;
import static org.ot5usk.steps.assertions.NegativeAsserts.assertNegativeResponse;
import static org.ot5usk.steps.specifications.Specifications.*;

@Epic("POST tests")
@Story("Adding new book")
public class AddNewBookTest {

    private static final Long authorId = getNewAuthorId();

    @Tag("post")
    @Tag("positive")
    @DisplayName("With max length title -REQUIREMENTS: why bookTitle length 100 its not ok?")
    @Description("Should return bookId, status-code: 201")
    @Test
    void withMaxLengthTitle() {
        String title = RandomStringUtils.random(100, true, true);
        assertPositiveResponseAddNewBook(requestSpecAddNewBook(title, authorId, 201), title, authorId);
    }

    @Tag("post")
    @Tag("positive")
    @DisplayName("Duplicate book")
    @Description("Should return bookId, status-code: 201")
    @Test
    void withDuplicateTitle() {
        requestSpecAddNewBook("book", authorId, 201);
        assertPositiveResponseAddDuplicateBook(requestSpecAddNewBook("book", authorId, 201), "book", authorId);
    }

    @Tag("post")
    @Tag("positive")
    @DisplayName("With all fields filled correct")
    @Description("Should return bookId, status-code: 201")
    @ParameterizedTest(name = "title: {0}")
    @CsvFileSource(resources = "/test_cases/positive/book_titles.csv", encoding = "windows-1251")
    void withAllCorrectFieldValues(String title) {
        assertPositiveResponseAddNewBook(requestSpecAddNewBook(title, authorId, 201), title, authorId);
    }

    @Tag("post")
    @Tag("negative")
    @DisplayName("With title length > 100, REQUIREMENTS: why bookTitle length 100 and earlier its negative too?, 100 characters or bytes, or?")
    @Description("Should return status-code: 400")
    @ParameterizedTest(name = "book_title_length = {1}")
    @MethodSource("titlesOfIncorrectLength")
    void withTitleIsIncorrectLength(String incorrectTitle, int ignoredLength) {
        NegativeResponse response = requestSpecAddNewBookNegative(incorrectTitle, authorId, 400);
        assertNegativeResponse(response, "Валидация не пройдена", "Некорректный размер поля firstName", "1001");
    }

    static Stream<Arguments> titlesOfIncorrectLength() {
        List<Arguments> titles = new ArrayList<>();
        for (int i = 101; i <= 102; i++) {
            titles.add(Arguments.of(RandomStringUtils.random(i, true, true), i));
        }
        return titles.stream();
    }

    @Tag("post")
    @Tag("negative")
    @DisplayName("Negative test cases")
    @Description("look file: /add_new_book.csv")
    @ParameterizedTest(name = "bookTitle = {0}, authorId = {1}")
    @CsvFileSource(resources = "/test_cases/negative/add_new_book.csv", encoding = "windows-1251")
    void test(String bookTitle, Long authorId, Integer expStCode, String expErrCode, String expErrMsg, String expErrDtls) {
        NegativeResponse response = requestSpecAddNewBookNegative(bookTitle, authorId, expStCode);
        assertNegativeResponse(response, expErrCode, expErrMsg, expErrDtls);
    }
}
