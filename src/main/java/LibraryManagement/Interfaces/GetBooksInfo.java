package LibraryManagement.Interfaces;

import APIManagement.BookManagement.Book;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface GetBooksInfo {
    ObservableList<Book> getBooks();
}
