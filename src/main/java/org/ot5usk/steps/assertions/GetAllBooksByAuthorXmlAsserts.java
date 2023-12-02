package org.ot5usk.steps.assertions;

import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.models.add_new_author.AddNewAuthorResponse;
import org.ot5usk.models.add_new_book.AddNewBookRequest;
import org.ot5usk.models.get_all_books_by_author.GetAllBooksByAuthorResponse;

import java.util.List;

public class GetAllBooksByAuthorXmlAsserts extends GetAllBooksByAuthorAsserts {

    public GetAllBooksByAuthorXmlAsserts(AddNewBookRequest expectedBook, AddNewAuthorRequest expectedAuthor,
                                         AddNewAuthorResponse currentAuthor, List<GetAllBooksByAuthorResponse> currentBooks) {
        super(expectedBook, expectedAuthor, currentAuthor, currentBooks);
    }
}