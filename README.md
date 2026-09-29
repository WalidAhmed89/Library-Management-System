# Library Management System

A simple backend-focused Library Management System built with Java and Spring Boot to practice database access with JPA, Hibernate, and PostgreSQL.

This project was created as a hands-on practice project after studying Spring Boot database access and Hibernate/JPA CRUD operations.

## Project Overview

The application manages books stored in a PostgreSQL database.

The project demonstrates the following data-access flow:

```text
CommandLineRunner
       |
       v
    BookDAO
       |
       v
   BookDAOImpl
       |
       v
   EntityManager
       |
       v
      JPA
       |
       v
   Hibernate
       |
       v
 PostgreSQL
```

The project does not use a REST API yet. Operations are executed through Spring Boot's `CommandLineRunner` so the main focus remains on JPA, Hibernate, DAO, transactions, and JPQL.

## Features

- Create a new book
- Create multiple books
- Find a book by ID
- Find all books
- Find books by author
- Update a book's price
- Delete a book by ID
- Find books below a specific price

## Technologies and Tools

### Java

Java is the main programming language used to build the application.

### Spring Boot

Spring Boot is used as the main application framework. It provides:

- Dependency Injection
- Bean management
- Component scanning
- Application configuration
- Integration with JPA and Hibernate
- Application startup

The application uses `@SpringBootApplication`.

### Spring IoC and Dependency Injection

Spring manages the application's objects and their dependencies.

For example, `EntityManager` is injected into `BookDAOImpl` using constructor injection:

```java
public BookDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
}
```

### JPA

JPA (Java Persistence API) is the persistence specification used for database access.

The project uses:

- `@Entity`
- `@Table`
- `@Id`
- `@GeneratedValue`
- `@Column`
- `EntityManager`
- `TypedQuery`
- JPQL

### Hibernate

Hibernate is the JPA implementation used by the project.

It handles ORM and translates JPA/JPQL operations into SQL operations that can be executed against PostgreSQL.

```text
JPA = Specification
Hibernate = Implementation
```

### PostgreSQL

PostgreSQL is the relational database used to store the books.

The application connects to PostgreSQL through the Spring datasource and Hibernate/JPA.


### Environment Variables and Database Configuration

Database credentials and connection details are not hard-coded in the project.

The application uses environment variables for the database configuration:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

The actual values are stored in a local `.env` file.

The `.env` file is added to `.gitignore` so database credentials and other sensitive configuration values are not committed to GitHub.

This keeps sensitive database information outside the source code while allowing the application to read the required values from the environment.

Example environment variables:

```text
DB_URL=jdbc:postgresql://localhost:5432/your_database
DB_USERNAME=your_username
DB_PASSWORD=your_password
```

The actual values should remain local and should not be committed to the repository.

### Lombok

Lombok is used to reduce boilerplate code in the `Book` entity.

The project uses annotations such as:

```java
@NoArgsConstructor
@Getter
@Setter
@ToString
```

### Maven

Maven is used for dependency management and project builds through `pom.xml`.

### IntelliJ IDEA

IntelliJ IDEA was used as the main development environment for:

- Writing Java code
- Running the Spring Boot application
- Debugging
- Managing dependencies
- Working with Lombok

### DataGrip

DataGrip was used to work with the PostgreSQL database.

It was useful for:

- Connecting to PostgreSQL
- Inspecting databases and tables
- Running SQL queries
- Checking stored data

## Project Structure

```text
src
└── main
    └── java
        └── com.Frosted.Library_Management_System
            ├── LibraryManagementSystemApplication.java
            │
            ├── dao
            │   ├── bookDAO.java
            │   └── BookDAOImpl.java
            │
            └── entity
                └── Book.java
```

## Entity

The `Book` entity represents a book stored in the database.

| Field | Type | Description |
|---|---|---|
| `id` | `int` | Unique identifier |
| `title` | `String` | Book title |
| `author` | `String` | Book author |
| `price` | `double` | Book price |
| `category` | `String` | Book category |

The entity is mapped using JPA annotations:

```java
@Entity
@Table(name = "Book")
public class Book {
    ...
}
```

The ID is generated automatically:

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
```

## DAO Layer

The project uses the DAO (Data Access Object) pattern to separate database-access logic from the rest of the application.

The `bookDAO` interface defines the available operations:

```text
createBook()
findBookByID()
findAllBook()
findBooksByAuthor()
updateBookPrice()
delete()
findBooksByPriceLessThan()
```

`BookDAOImpl` contains the actual implementation using `EntityManager`.

## EntityManager

`EntityManager` is the main JPA API used in this project to interact with the persistence context.

The project uses it for:

### Persist

```java
entityManager.persist(book);
```

Used to save a new book.

### Find

```java
entityManager.find(Book.class, id);
```

Used to retrieve a book by its primary key.

### Query

```java
entityManager.createQuery("FROM Book", Book.class);
```

Used to execute JPQL queries.

### Remove

```java
entityManager.remove(book);
```

Used to delete an entity.

## JPQL

The project uses JPQL (Java Persistence Query Language) for database queries.

JPQL works with entity names and Java fields instead of directly using database table and column names.

For example:

```java
FROM Book
```

refers to the `Book` entity.

The project uses JPQL for:

- Retrieving all books
- Searching by author
- Updating prices
- Filtering books by price

Example:

```java
FROM Book WHERE author=:author
```

The parameter is then supplied with:

```java
books.setParameter("author", author);
```

## Transactions

Write operations use `@Transactional`.

Transactions are used for:

- Creating books
- Updating book prices
- Deleting books

Example:

```java
@Transactional
public void createBook(Book book) {
    entityManager.persist(book);
}
```

## CommandLineRunner

The application uses Spring Boot's `CommandLineRunner` to execute database operations when the application starts.

The DAO is injected into the runner:

```java
@Bean
public CommandLineRunner commandLineRunner(bookDAO bookDAO) {
    return runner -> {
        // database operations
    };
}
```

This approach was used intentionally to practice database access before introducing REST controllers.

## CRUD Operations

The project covers the main CRUD operations.

### Create

A new `Book` object is created and persisted using JPA.

### Read

Books can be retrieved:

- By ID
- As a complete list
- By author
- By maximum price

### Update

A book's price can be updated using a JPQL update query.

### Delete

A book can be found by ID and removed using `EntityManager`.

## Example Operations

The application contains methods for testing the different operations:

```text
createNewBook()
createMultipleBooks()
findBookByID()
findAllBooks()
findByAuthor()
updateBookPrice()
deleteBook()
findBooksByPriceLessThan()
```

The desired operation can be enabled in `CommandLineRunner` during testing.

## What I Practiced

This project helped me practice:

- Spring Boot project structure
- Dependency Injection
- Spring Beans
- `@SpringBootApplication`
- `@Bean`
- `CommandLineRunner`
- JPA entities
- Entity-to-table mapping
- Primary keys
- Generated IDs
- Hibernate ORM
- EntityManager
- TypedQuery
- JPQL
- Named parameters
- DAO pattern
- Constructor injection
- Transactions
- CRUD operations
- PostgreSQL integration
- Lombok
- Maven

## Key Concepts

### JPA vs Hibernate

```text
JPA
 |
 | Defines persistence APIs and rules
 v
Hibernate
 |
 | Implements JPA
 v
PostgreSQL
```

### JPQL vs SQL

SQL works directly with database structures:

```sql
SELECT * FROM book;
```

JPQL works with entities:

```java
FROM Book
```

This distinction is important when working with JPA and Hibernate.

### DAO Architecture

```text
Application
    |
    v
  DAO
    |
    v
DAO Implementation
    |
    v
EntityManager
    |
    v
 Hibernate
    |
    v
PostgreSQL
```

## Future Improvements

Possible next steps include:

- Rename `bookDAO` to `BookDAO` following Java naming conventions
- Improve method naming such as `findBookByID()` to `findBookById()`
- Add category-based search
- Improve handling when a book ID does not exist
- Introduce a Service layer
- Add REST API endpoints
- Add DTOs
- Add validation
- Add global exception handling
- Add unit and integration tests
- Add Swagger/OpenAPI documentation

## Project Goal

The goal of this project was not to build a production-ready library application.

The main goal was to gain practical experience with:

```text
Spring Boot
     +
JPA
     +
Hibernate
     +
EntityManager
     +
JPQL
     +
PostgreSQL
     +
DAO Pattern
     +
Transactions
```

This project provides a foundation for moving from basic database access toward building complete Spring Boot REST backends.

## Author

Walid Ahmed

GitHub: `WalidAhmed89`
