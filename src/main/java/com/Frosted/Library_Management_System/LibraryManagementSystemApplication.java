package com.Frosted.Library_Management_System;

import com.Frosted.Library_Management_System.dao.bookDAO;
import com.Frosted.Library_Management_System.entity.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(bookDAO bookDAO){
		return runner ->{
			//createNewBook(bookDAO);

			//createMultipleBooks(bookDAO);

			//findBookByID(bookDAO,3);

			//findAllBooks(bookDAO);

			//findByAuthor(bookDAO,"Robert Martin");

			//updateBookPrice(bookDAO,2,100);

			//deleteBook(bookDAO,4);

			findBooksByPriceLessThan(bookDAO,50);
		};
	}

	private void createNewBook(bookDAO bookDAO){
		System.out.println("Creating book...");
		Book book = new Book("Clean Code","Robert Martin",30.0,"Programming");
		bookDAO.createBook(book);
		System.out.println("Book "+book.getId()+" was created");
	}

	private void createMultipleBooks(bookDAO bookDAO){
		System.out.println("Creating books...");
		Book book1 = new Book("Clean Code","Robert Martin",30.0,"Programming");
		Book book2 = new Book("Effective Java","Joshua Bloch",40.0,"Programming");
		Book book3 = new Book("The Hobbit","J.R.R Tolkien",25.0,"Fantasy");

		System.out.println("Saving Books");
		bookDAO.createBook(book1);
		bookDAO.createBook(book2);
		bookDAO.createBook(book3);

		System.out.println("Saved student. Generated id: "+ book1.getId()+" "+book2.getId()+" "+book3.getId());
	}

	private void findBookByID(bookDAO bookDAO,int id){
		System.out.println("Processing for finding the book...");
		Book book = bookDAO.findBookByID(id);
		System.out.println("The Book that had the "+id+" ID Number is: "+book);
	}

	private void findAllBooks(bookDAO bookDAO){
		List<Book> books = bookDAO.findAllBook();

		for(Book book : books){
			System.out.println(book);
		}
	}

	private void findByAuthor(bookDAO bookDAO,String author){
		List<Book> books = bookDAO.findBooksByAuthor(author);

		for(Book book : books){
			System.out.println(book);
		}
	}

	private void updateBookPrice(bookDAO bookDAO,int id,double newPice){
		int numOfUpdatingBooks = bookDAO.updateBookPrice(id,newPice);
		System.out.println(numOfUpdatingBooks+" Was Updated");
	}

	private void deleteBook(bookDAO bookDAO,int id){
		System.out.println("Deleting Book...");
		bookDAO.delete(id);
		System.out.println("Book was deleted");
	}

	private void findBooksByPriceLessThan(bookDAO bookDAO,double price){
		List<Book> books = bookDAO.findBooksByPriceLessThan(price);

		System.out.println("This books is less than "+price);
		for(Book book : books){
			System.out.println(book);
		}
	}
}
