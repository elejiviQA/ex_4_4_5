package org.ot5usk.post;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.ot5usk.BaseTest.AuthorCreator.randomAnyAuthorNamesList;
import static org.ot5usk.steps.assertions.AssertsAddNewAuthor.verifyAuthorId;
import static org.ot5usk.steps.specifications.Specifications.requestSpecAddNewAuthor;

@Epic("POST tests")
@Story("Adding new author")
public class AddNewAuthorTest {

    @Tag("post")
    @Tag("positive")
    @DisplayName("Add new author")
    @Description("Should be return authorId, status-code: 201 -EXPLAIN AND SPECIFY REQUIREMENTS: Can full names be the same?")
    @ParameterizedTest(name = "firstName = {0}, familyName = {1}, secondName = {2}")
    @MethodSource("authorNames")
    void testAddNewAuthor(String firstName, String familyName, String secondName) {
        verifyAuthorId(requestSpecAddNewAuthor(firstName, familyName, secondName, 201));
    }

    static Stream<Arguments> authorNames() {
        List<String> firstNames = randomAnyAuthorNamesList();
        List<String> familyNames = randomAnyAuthorNamesList();
        List<String> secondName = randomAnyAuthorNamesList();
        List<Arguments> authors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    authors.add(Arguments.of(firstNames.get(i), familyNames.get(j), secondName.get(k)));
                }
            }
        }
        return authors.stream();
    }
}