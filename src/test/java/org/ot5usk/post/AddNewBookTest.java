package org.ot5usk.post;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.ot5usk.utils.steps_executors.BookSender;

import org.ot5usk.utils.creators.string_creator.BookTitleLengthLimits;

import java.util.stream.Stream;

import static org.ot5usk.utils.creators.string_creator.StringCreator.*;

@Epic("POST tests")
@Story("Adding new book")
public class AddNewBookTest {

    private static final BookSender bookSender = new BookSender();

    @Tag("maybebugs")
    @Tag("post")
    @Tag("positive")
    @DisplayName("With max length title -REQUIREMENTS: why bookTitle length 100 its not ok?")
    @Description("Should return bookId, status-code: 201")
    @Test
    void withMaxLimitLengthTitle() {
        String correctTitle = randCorrectStringBySelectedLength(BookTitleLengthLimits.MAX.getLength());
        bookSender.sendBook(correctTitle);
    }

    @Tag("maybebugs")
    @Tag("filthy")
    @Tag("post")
    @Tag("positive")
    @DisplayName("why bookTitle length 100 and earlier its negative too?")
    @Description("Should return bookId, status-code: 201")
    @ParameterizedTest(name = "book_title_length = {1}")
    @MethodSource("randCorrectTitlesCharsOfCorrectTitlesLengths")
    void withCorrectLengthsTitles(String correctTitle, int ignoredLength) {
        bookSender.sendBook(correctTitle);
    }

    static Stream<Arguments> randCorrectTitlesCharsOfCorrectTitlesLengths() {
        return randCorrectStringsBySelectedLengths(
                BookTitleLengthLimits.MIN.getLength(),
                BookTitleLengthLimits.MAX.getLength()
        ).stream();
    }

    @Tag("post")
    @Tag("positive")
    @DisplayName("With all fields filled correct")
    @Description("Should return bookId, status-code: 201")
    @ParameterizedTest(name = "title: {0}")
    @CsvFileSource(resources = "/test_cases/positive/correct_book_titles_values.csv")
    //Подтверждение что именно та книга в базе (проверить через гет all)
    void withCorrectTitleChars(String correctTitle) {
        bookSender.sendBook(correctTitle);
    }

    @Tag("post")
    @Tag("positive")
    @DisplayName("Duplicate book")
    @Description("Should return bookId, status-code: 201")
    @Test
    void withCorrectDuplicateTitles() {
        String correctDuplicateTitle = randCorrectStringBySelectedLength(BookTitleLengthLimits.AVERAGE.getLength());
        bookSender.sendBook(correctDuplicateTitle, 2);
    }

    @Tag("post")
    @Tag("negative")
    @DisplayName("With title length > 100")
    @Description("Should return status-code: 400")
    @ParameterizedTest(name = "book_title_length = {1}")
    @MethodSource("randCorrectTitlesCharsWhenTitlesByNotCorrectLengths")
    void withTitleIsIncorrectLengths(String incorrectTitle, int ignoredLength) {
        bookSender.sendBook(incorrectTitle, 400, "Валидация не пройдена", "Некорректный размер поля firstName", "1001");
    }

    static Stream<Arguments> randCorrectTitlesCharsWhenTitlesByNotCorrectLengths() {
        return randCorrectStringsBySelectedLengths(101, 102).stream();
    }

    @Tag("maybebugs")
    @Tag("post")
    @Tag("negative")
    @DisplayName("Negative test cases")
    @Description("look at: /test_cases/negative/by_different_incorrect_values.csv")
    @ParameterizedTest(name = "bookTitle = {0}, authorId = {1}")
    @CsvFileSource(resources = "/test_cases/negative/add_new_book/by_different_incorrect_values.csv")
    void withAnotherIncorrectValues(String bookTitle, Long authorId, Integer expStCode, String expErrCode, String expErrMsg, String expErrDtls) {
        bookSender.sendBook(bookTitle, authorId, expStCode, expErrCode, expErrMsg, expErrDtls);
    }
}
