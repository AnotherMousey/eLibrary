package LibraryManagement.Interfaces;

import APIManagement.BookManagement.Book;

import java.sql.SQLException;

public interface AddOrDeleteBooks {
    void addBook(Book book) throws SQLException;
    void deleteBook(Book book) throws SQLException;
}
