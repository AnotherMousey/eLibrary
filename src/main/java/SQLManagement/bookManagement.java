package SQLManagement;

import APIManagement.BookManagement.Book;

import java.sql.SQLException;
import java.sql.Statement;

public class bookManagement extends SQL{

    public static void addBook(Book book) throws SQLException {
        Statement stmt = getStmt();
        String query = "INSERT INTO book(isbn, title, author, description, publishedDate, language" +
                ") VALUES ('" + book.getIsbn() + "', '" + book.getTitle() + "', '" +
                book.getDescription() + "', '" + book.getPublishedDate() + "', '" +
                book.getLanguage() + "');";
        stmt.executeUpdate(query);

        for(String author: book.getAuthors()) {
            query = "INSERT INTO bookandauthor(isbn, author) VALUES ('" + book.getIsbn() +
                    "', '" + author + "');";
            stmt.executeUpdate(query);
        }

        for(String category: book.getCategories()) {
            query = "INSERT INTO category(categoryName) VALUES('" + category + "') " +
                    "WHERE NOT EXISTS(SELECT * FROM category WHERE categoryName = '" + category + "')";
            stmt.executeUpdate(query);
            query = "INSERT INTO bookandcategory(isbn, categoryName) VALUES(" +
                    "('" + book.getIsbn() + "', SELECT categoryID FROM category WHERE categoryName = '" +
                    category + "');";
            stmt.executeUpdate(query);
        }
    }
}
