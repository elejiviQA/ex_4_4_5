package org.ot5usk.steps.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.ot5usk.entity.Author;
import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.models.add_new_author.AddNewAuthorResponse;
import org.ot5usk.models.add_new_book.AddNewBookRequest;
import org.ot5usk.models.add_new_book.AddNewBookResponse;
import org.ot5usk.models.get_all_books_by_author.GetAllBooksByAuthorResponse;
import org.ot5usk.models.get_all_books_by_author_xml.GetAllBooksByAuthorRequestXml;
import org.ot5usk.models.get_all_books_by_author_xml.GetAllBooksByAuthorResponseXml;
import org.ot5usk.steps.EndPoints;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Specifications {

    public static RequestSpecification requestSpec(ContentType contentType) {
        return new RequestSpecBuilder()
                .setBaseUri(EndPoints.BASE.getPath())
                .setContentType(contentType)
                .build();
    }

    public static ResponseSpecification responseSpecWithStatus(int code) {
        return new ResponseSpecBuilder()
                .expectStatusCode(code)
                .build();
    }

    public static AddNewAuthorResponse requestSpecAddNewAuthor(String firstName, String familyName, String secondName) {
        AddNewAuthorRequest addNewAuthorRequest = new AddNewAuthorRequest(firstName, familyName, secondName);

        return given().spec(requestSpec(ContentType.JSON))
                .body(addNewAuthorRequest)
                .when()
                .post(EndPoints.ADD_NEW_AUTHOR.getPath())
                .then().spec(responseSpecWithStatus(201))
                .extract().as(AddNewAuthorResponse.class);
    }

    public static AddNewBookResponse requestSpecAddNewBook(String bookTitle, Long authorId) {
        Author author = new Author(authorId);
        AddNewBookRequest addNewBookRequest = new AddNewBookRequest(bookTitle, author);

        return given().spec(requestSpec(ContentType.JSON))
                .body(addNewBookRequest)
                .when()
                .post(EndPoints.ADD_NEW_BOOK.getPath())
                .then().spec(responseSpecWithStatus(201))
                .extract().as(AddNewBookResponse.class);
    }

    public static List<GetAllBooksByAuthorResponse> requestSpecGetAllBooksByAuthor(String authorId) {
        return given().spec(requestSpec(ContentType.JSON))
                .when()
                .get(EndPoints.GET_ALL_BOOKS_BY_AUTHOR.getPath(), authorId)
                .then().spec(responseSpecWithStatus(200))
                .extract().jsonPath().getList(".", GetAllBooksByAuthorResponse.class);
    }

    public static GetAllBooksByAuthorResponseXml requestSpecGetAllBooksByAuthorXml(Integer authorId) {
        GetAllBooksByAuthorRequestXml getAllBooksByAuthorRequestXml = new GetAllBooksByAuthorRequestXml();
        getAllBooksByAuthorRequestXml.setAuthorId(authorId);

        return given().spec(requestSpec(ContentType.XML))
                .when()
                .get(EndPoints.GET_ALL_BOOKS_BY_AUTHOR_XML.getPath())
                .then().spec(responseSpecWithStatus(200))
                .extract().as(GetAllBooksByAuthorResponseXml.class);
    }
}
