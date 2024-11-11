package LibraryManagement.Management;

import APIManagement.BookManagement.Book;
import LibraryManagement.Interfaces.*;
import SQLManagement.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminManagement extends SQL implements AddOrDeleteBooks{

    @Override
    public void addBook(Book book) throws SQLException {
        bookManagement.addBook(book);
        LibraryManagement.addBook(book);
    }

    @Override
    public void deleteBook(Book book) throws SQLException {
        Statement stmt = getStmt();
        String query = "DELETE FROM bookandcategory WHERE isbn = '" + book.getIsbn() + "'";
        stmt.executeUpdate(query);
        query = "DELETE FROM bookandauthor WHERE isbn = '" + book.getIsbn() + "'";
        stmt.executeUpdate(query);
        query = "DELETE FROM book WHERE isbn = '" + book.getIsbn() + "'";
        stmt.executeUpdate(query);
    }
}
