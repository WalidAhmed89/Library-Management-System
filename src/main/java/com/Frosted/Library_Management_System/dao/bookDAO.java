package com.Frosted.Library_Management_System.dao;

import com.Frosted.Library_Management_System.entity.Book;

import java.util.List;

public interface bookDAO {
    void createBook(Book book);
    Book findBookByID(int id);
    List<Book> findAllBook();
    List<Book> findBooksByAuthor(String author);
    int updateBookPrice(int id, double newPrice);
    void delete(int id);
    List<Book> findBooksByPriceLessThan(double price);
}
