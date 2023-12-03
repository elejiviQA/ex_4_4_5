package org.ot5usk.steps;

public enum EndPoints {

    BASE("http://localhost:8080/"),
    AUTH("auth/login"),

    ADD_NEW_AUTHOR("library/authors/save"),
    ADD_NEW_BOOK("library/books/save"),

    GET_ALL_BOOKS_BY_AUTHOR("library/authors/{id}/books"),
    GET_ALL_BOOKS_BY_AUTHOR_XML("library/authors/books");

    public final String path;

    EndPoints(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
