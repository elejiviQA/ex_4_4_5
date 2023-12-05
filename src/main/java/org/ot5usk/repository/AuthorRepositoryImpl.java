package org.ot5usk.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.ot5usk.entities.Author;
import org.ot5usk.utils.db_utils.Util;

import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {

    @Override
    public Author getAuthorById(Long authorId) {
        try (Session session = Util.getSessionFactory().openSession()) {
            NativeQuery<Author> getAuthorByIdQuery = session.createNativeQuery("SELECT * FROM author WHERE id = :authorId", Author.class);
            getAuthorByIdQuery.setParameter("authorId", authorId);
            return getAuthorByIdQuery.getResultList().get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Author> getAuthorByName(String firstName, String familyName) {
        String getAuthorByName = "SELECT * FROM author WHERE (first_name = :firstName) and (family_name = :familyName)";
        try (Session session = Util.getSessionFactory().openSession()) {
            NativeQuery<Author> getAuthorByNameQuery = session.createNativeQuery(getAuthorByName, Author.class);
            getAuthorByNameQuery.setParameter("firstName", firstName);
            getAuthorByNameQuery.setParameter("familyName", familyName);
            return getAuthorByNameQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveNewAuthor(String firstName, String familyName) {
        String saveAuthor = """
                INSERT INTO Author
                (first_name, family_name)
                VALUES(:firstName, :familyName)
                """;
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            NativeQuery<Author> saveAuthorQuery = session.createNativeQuery(saveAuthor, Author.class);
            saveAuthorQuery.setParameter("firstName", firstName);
            saveAuthorQuery.setParameter("familyName", familyName);
            saveAuthorQuery.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanAuthorTable() {
        BookRepository bookRepository = new BookRepositoryImpl();
        bookRepository.cleanBookTable();
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DELETE FROM author", Author.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}
