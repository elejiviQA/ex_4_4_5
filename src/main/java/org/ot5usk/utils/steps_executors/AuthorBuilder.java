package org.ot5usk.utils.steps_executors;

import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.models.add_new_author.AddNewAuthorResponse;
import org.ot5usk.utils.creators.string_creator.AuthorNameLengthLimits;

import static org.ot5usk.steps.specifications.Specifications.requestSpecAddNewAuthor;
import static org.ot5usk.utils.creators.string_creator.StringCreator.randCorrectStringBySelectedLengthRandom;

public class AuthorBuilder {

    public static AddNewAuthorResponse addNewAuthor(AddNewAuthorRequest addNewAuthorRequest) {
        return requestSpecAddNewAuthor(addNewAuthorRequest, 201);
    }

    public static AddNewAuthorRequest buildRequest() {
        return new AddNewAuthorRequest(
                randCorrectStringBySelectedLengthRandom(AuthorNameLengthLimits.MAX.getLength()),
                randCorrectStringBySelectedLengthRandom(AuthorNameLengthLimits.MAX.getLength()),
                randCorrectStringBySelectedLengthRandom(AuthorNameLengthLimits.MAX.getLength())
        );
    }
}