package org.ot5usk.steps.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.models.add_new_author.AddNewAuthorResponse;
import org.ot5usk.models.add_new_book.AddNewBookRequest;
import org.ot5usk.models.add_new_book.AddNewBookResponse;
import org.ot5usk.models.get_all_books_by_author.GetAllBooksByAuthorResponse;
import org.ot5usk.models.get_all_books_by_author_xml.GetAllBooksByAuthorRequestXml;
import org.ot5usk.models.get_all_books_by_author_xml.GetAllBooksByAuthorResponseXml;
import org.ot5usk.models.negative_responses.DefaultNegativeResponse;
import org.ot5usk.steps.EndPoints;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Specifications {

    public static RequestSpecification requestSpec(ContentType contentType) {
        return new RequestSpecBuilder()
                .setBaseUri(EndPoints.BASE.getPath())
                .setContentType(contentType)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public static ResponseSpecification responseSpecWithStatus(Integer code) {
        return new ResponseSpecBuilder()
                .expectStatusCode(code)
                .build();
    }

    public static AddNewAuthorResponse requestSpecAddNewAuthor(AddNewAuthorRequest addNewAuthorRequest, Integer expStCode) {
        return given().spec(requestSpec(ContentType.JSON))
                .body(addNewAuthorRequest)
                .when()
                .post(EndPoints.ADD_NEW_AUTHOR.getPath())
                .then().spec(responseSpecWithStatus(expStCode))
                .extract().as(AddNewAuthorResponse.class);
    }

    public static AddNewBookResponse requestSpecAddNewBook(AddNewBookRequest addNewBookRequest, Integer expStCode) {
        return given().spec(requestSpec(ContentType.JSON))
                .body(addNewBookRequest)
                .when()
                .post(EndPoints.ADD_NEW_BOOK.getPath())
                .then().spec(responseSpecWithStatus(expStCode))
                .extract().as(AddNewBookResponse.class);
    }

    public static DefaultNegativeResponse requestSpecAddNewBookNegative(AddNewBookRequest addNewBookBadRequest, Integer expStCode) {
        return given().spec(requestSpec(ContentType.JSON))
                .body(addNewBookBadRequest)
                .when()
                .post(EndPoints.ADD_NEW_BOOK.getPath())
                .then().spec(responseSpecWithStatus(expStCode))
                .extract().as(DefaultNegativeResponse.class);
    }

    public static List<GetAllBooksByAuthorResponse> requestSpecGetAllBooksByAuthor(Long authorId, Integer expStCode) {
        return given().spec(requestSpec(ContentType.JSON))
                .when()
                .get(EndPoints.GET_ALL_BOOKS_BY_AUTHOR.getPath(), authorId)
                .then().spec(responseSpecWithStatus(expStCode))
                .extract().jsonPath().getList(".", GetAllBooksByAuthorResponse.class);
    }

    public static DefaultNegativeResponse requestSpecGetAllBookByIncorrectAuthor(String incorrectAuthorId, Integer expStCode) {
        return given().spec(requestSpec(ContentType.JSON))
                .when()
                .get(EndPoints.GET_ALL_BOOKS_BY_AUTHOR.getPath(), incorrectAuthorId)
                .then().spec(responseSpecWithStatus(expStCode))
                .extract().as(DefaultNegativeResponse.class);
    }

    public static GetAllBooksByAuthorResponseXml requestSpecGetAllBooksByAuthorXml(String authorId, Integer expStCode) {
        GetAllBooksByAuthorRequestXml getAllBooksByAuthorRequestXml = new GetAllBooksByAuthorRequestXml();
        getAllBooksByAuthorRequestXml.setAuthorId(authorId);

        return given().spec(requestSpec(ContentType.XML))
                .body(getAllBooksByAuthorRequestXml)
                .when()
                .post(EndPoints.GET_ALL_BOOKS_BY_AUTHOR_XML.getPath())
                .then().spec(responseSpecWithStatus(expStCode))
                .extract().as(GetAllBooksByAuthorResponseXml.class);
    }

    public static DefaultNegativeResponse requestSpecGetAllBooksByIncorrectAuthorXml(String authorId, Integer expStCode) {
        GetAllBooksByAuthorRequestXml getAllBooksByAuthorRequestXml = new GetAllBooksByAuthorRequestXml();
        getAllBooksByAuthorRequestXml.setAuthorId(authorId);

        return given().spec(requestSpec(ContentType.XML))
                .body(getAllBooksByAuthorRequestXml)
                .when()
                .post(EndPoints.GET_ALL_BOOKS_BY_AUTHOR_XML.getPath())
                .then().spec(responseSpecWithStatus(expStCode))
                .extract().as(DefaultNegativeResponse.class);
    }
}
