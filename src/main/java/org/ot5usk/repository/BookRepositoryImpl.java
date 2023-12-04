package org.ot5usk.repository;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.ot5usk.utils.db.Util;
import org.ot5usk.entities.Book;

import java.util.List;

@NoArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    @Override
    public void saveBook(String bookTitle, long authorId) {
        String saveBook = """
                INSERT INTO Book
                (book_title, author_id)
                VALUES(:bookTitle, :authorId)
                """;
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            NativeQuery<Book> saveBookQuery = session.createNativeQuery(saveBook, Book.class);
            saveBookQuery.setParameter("bookTitle", bookTitle);
            saveBookQuery.setParameter("authorId", authorId);
            saveBookQuery.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> getAllBooksByAuthorId(long authorId) {
        try (Session session = Util.getSessionFactory().openSession()) {
            NativeQuery<Book> getAllBooksQuery = session.createNativeQuery("SELECT * FROM Book WHERE author_Id = :authorId", Book.class);
            getAllBooksQuery.setParameter("authorId", authorId);
            return getAllBooksQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> getAllBooksByTitle(String bookTitle) {
        try (Session session = Util.getSessionFactory().openSession()) {
            NativeQuery<Book> getAllBooksQuery = session.createNativeQuery("SELECT * FROM Book WHERE book_title = :bookTitle", Book.class);
            getAllBooksQuery.setParameter("bookTitle", bookTitle);
            return getAllBooksQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeBookByTitle(String bookTitle) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(session.find(Book.class, bookTitle));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanBooksTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DELETE FROM Book", Book.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}
