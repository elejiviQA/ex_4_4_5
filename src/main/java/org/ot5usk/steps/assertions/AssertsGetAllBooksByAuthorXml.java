package org.ot5usk.steps.assertions;

import org.ot5usk.models.get_all_books_by_author_xml.GetAllBooksByAuthorResponseXml;
import org.ot5usk.utils.steps_executors.BookSender;

public class AssertsGetAllBooksByAuthorXml extends AssertsGetAllBooksByAuthor {

    public AssertsGetAllBooksByAuthorXml(BookSender bookSender, GetAllBooksByAuthorResponseXml booksXml) {
        super(bookSender, booksXml);
    }
}
