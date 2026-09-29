package com.Frosted.Library_Management_System.dao;

import com.Frosted.Library_Management_System.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAOImpl implements bookDAO{
    private final EntityManager entityManager;

    public BookDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void createBook(Book book) {
        entityManager.persist(book);
    }

    @Override
    public Book findBookByID(int id) {
        return entityManager.find(Book.class,id);
    }

    @Override
    public List<Book> findAllBook() {
        TypedQuery<Book> books = entityManager.createQuery("FROM Book", Book.class);
        return books.getResultList();
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        TypedQuery<Book> books = entityManager.createQuery("FROM Book WHERE author=:author", Book.class);
        books.setParameter("author",author);
        return books.getResultList();
    }

    @Override
    @Transactional
    public int updateBookPrice(int id, double newPrice) {
        return entityManager.createQuery("UPDATE Book SET price=:newPrice WHERE id=:id")
                .setParameter("newPrice",newPrice)
                .setParameter("id",id).executeUpdate();
    }

    @Override
    @Transactional
    public void delete(int id) {
        Book bookWillDelete = entityManager.find(Book.class,id);
        entityManager.remove(bookWillDelete);
    }

    @Override
    public List<Book> findBooksByPriceLessThan(double price) {
        TypedQuery<Book> books = entityManager.createQuery("FROM Book WHERE price <: price", Book.class);
        books.setParameter("price",price);
        return books.getResultList();
    }
}
