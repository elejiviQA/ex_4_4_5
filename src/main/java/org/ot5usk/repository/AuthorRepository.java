package org.ot5usk.repository;

import org.ot5usk.entities.Author;

import java.util.List;

public interface AuthorRepository {

    Author getAuthorById(Long authorId);

    List<Author> getAuthorByName(String firstName, String familyName);

    void saveNewAuthor(String firstName, String familyName);

    void cleanAuthorTable();
}
