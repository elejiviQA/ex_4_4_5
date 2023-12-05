package org.ot5usk.repository;

import org.ot5usk.entities.Book;

import java.util.List;

public interface BookRepository {

    void saveBook(String title, Long authorId);

    List<Book> getAllBooksById(Integer bookId);

    List<Book> getAllBooksByAuthorId(Long authorId);

    List<Book> getAllBooksByTitle(String bookTitle);

    void removeBookByTitle(String bookTitle);

    void cleanBookTable();
}