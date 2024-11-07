package LibraryManagement.Interfaces;

import APIManagement.BookManagement.Book;
import java.util.ArrayList;

public interface BorrowedBooks {
    ArrayList<Book> getBorrowedBooks();
}
