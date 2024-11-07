package LibraryManagement.Management;

import APIManagement.BookManagement.Book;
import LibraryManagement.Interfaces.*;

import java.util.ArrayList;

public class AdminManagement implements GetBooksInfo,
                                        BorrowedBooks,
                                        AddOrDeleteBooks{
    @Override
    public ArrayList<String> getBooks() {
        return null;
    }

    @Override
    public ArrayList<Book> getBorrowedBooks() {
        return null;
    }

    @Override
    public void addBook() {

    }

    @Override
    public void deleteBook() {

    }
}
