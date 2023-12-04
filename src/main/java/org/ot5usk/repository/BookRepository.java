package org.ot5usk.repository;

import org.ot5usk.entities.Book;

import java.util.List;

public interface BookRepository {

    void saveBook(String title, long authorId);

    List<Book> getAllBooksByAuthorId(long authorId);

    List<Book> getAllBooksByTitle(String bookTitle);

    void removeBookByTitle(String bookTitle);

    void cleanBooksTable();
}