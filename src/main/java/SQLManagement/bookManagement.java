package SQLManagement;

import APIManagement.BookManagement.Book;

import java.sql.SQLException;
import java.sql.Statement;

public class bookManagement extends SQL{

    public static void addBook(Book book) throws SQLException {
        Statement stmt = getStmt();
        String query = "INSERT INTO book(isbn, title, author, quantity" +
                ") VALUES ('" + book.getIsbn() + "', '" + book.getTitle() + "', '" +
                book.getAuthor() + "', '" + book.getQuantity() + "');";
        stmt.executeUpdate(query);
    }
}
