package LibraryManagement.Interfaces;

import APIManagement.BookManagement.Book;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BorrowedBooks {
    ObservableList<Book> getBorrowedBooks() throws SQLException;
}
