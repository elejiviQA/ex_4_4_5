package org.ot5usk.repository;

import org.ot5usk.entities.Author;

import java.util.List;

public interface AuthorRepository {

    Author getAuthorById(long authorId);
}
