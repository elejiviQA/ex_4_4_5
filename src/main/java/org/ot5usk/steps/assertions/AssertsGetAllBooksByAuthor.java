package org.ot5usk.steps.assertions;

import org.ot5usk.models.get_all_books_by_author.GetAllBooksByAuthorResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssertsGetAllBooksByAuthor {

    public static void verifyBooksList(List<GetAllBooksByAuthorResponse> books, Long expAuthorId) {
        assertAll(
                () -> assertTrue(books.stream().allMatch(book -> book.getAuthor().getId().equals(expAuthorId))),
                () -> assertNotNull(books)
        );
    }
}
