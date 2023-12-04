package org.ot5usk.utils.builders;

import org.ot5usk.entities.Author;
import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.models.add_new_book.AddNewBookRequest;

import static org.ot5usk.utils.builders.DateBuilder.buildRandomDate;
import static org.ot5usk.utils.builders.StringBuilder.randCorrectStringBySelectedLengthRandom;

public class RequestBuilder {

    public static AddNewAuthorRequest buildAddnewAuthorRequest() {
        return new AddNewAuthorRequest(
                randCorrectStringBySelectedLengthRandom(AuthorNameLengthLimits.MAX.getLength()),
                randCorrectStringBySelectedLengthRandom(AuthorNameLengthLimits.MAX.getLength()),
                randCorrectStringBySelectedLengthRandom(AuthorNameLengthLimits.MAX.getLength()),
                buildRandomDate()
        );
    }

    public static AddNewBookRequest buildAddNewBookRequest(String bookTitle, Long authorId) {
        Author author = new Author(authorId);
        return new AddNewBookRequest(bookTitle, author);
    }
}
