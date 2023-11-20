package org.ot5usk.steps;

public enum EndPoints {

    BASE("http://localhost:8080/library"),

    ADD_NEW_AUTHOR("/authors/save"),
    ADD_NEW_BOOK("/books/save"),

    GET_ALL_BOOKS_BY_AUTHOR("/authors/{id}/books"),
    GET_ALL_BOOKS_BY_AUTHOR_XML("/authors/books");

    public final String path;

    EndPoints(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
