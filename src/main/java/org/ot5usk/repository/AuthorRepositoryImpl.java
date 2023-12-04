package org.ot5usk.repository;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.ot5usk.entities.Author;
import org.ot5usk.utils.db.Util;

import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {

    @Override
    public Author getAuthorById(long authorId) {
        try (Session session = Util.getSessionFactory().openSession()) {
            NativeQuery<Author> getAuthorByIdQuery = session.createNativeQuery("SELECT * FROM author WHERE id = :authorId", Author.class);
            getAuthorByIdQuery.setParameter("authorId", authorId);
            return getAuthorByIdQuery.getResultList().get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
