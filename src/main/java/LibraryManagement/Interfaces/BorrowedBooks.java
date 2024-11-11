package LibraryManagement.Interfaces;

import APIManagement.BookManagement.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BorrowedBooks {
    ArrayList<Book> getBorrowedBooks() throws SQLException;
}
