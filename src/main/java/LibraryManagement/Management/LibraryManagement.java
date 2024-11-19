package LibraryManagement.Management;

import APIManagement.BookManagement.Book;
import APIManagement.BookManagement.BookForBorrow;
import LibraryManagement.Interfaces.*;
import SQLManagement.ResultSetToList;
import SQLManagement.SQL;
import libUser.CurrentUser;

import javax.swing.plaf.nimbus.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class LibraryManagement implements GetBooksInfo,
        BorrowedBooks, doesBookExists, BorrowAndReturnBooks {

    private static ArrayList<Book> books;
    private static ArrayList<Book> borrowedBooks;

    public static void addBook(Book book) {
        books.add(book);
    }

    public static void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }

    private static ArrayList<String> getBookAuthor(Statement stmt, String isbn) throws SQLException {
        String query;
        query = "SELECT * FROM bookandauthor WHERE isbn='" + isbn + "';";
        ResultSet authorRs = stmt.executeQuery(query);
        List<HashMap<String,Object>> authors = ResultSetToList.convertResultSetToList(authorRs);
        ArrayList<String> listOfAuthors = new ArrayList<>();
        for(HashMap<String,Object> author : authors) {
            listOfAuthors.add((String) author.get("author"));
        }
        return listOfAuthors;
    }

    private ArrayList<String> getBookCategory(Statement stmt, String isbn) throws SQLException {
        String query;
        query = "SELECT * FROM bookandcategory bac " +
                "JOIN category c ON bac.categoryID = c.categoryID " +
                "HAVING bac.ISBN = '" + isbn + "';";
        ResultSet categoryRs = stmt.executeQuery(query);
        List<HashMap<String,Object>> category = ResultSetToList.convertResultSetToList(categoryRs);
        ArrayList<String> listOfCategories = new ArrayList<>();
        for(HashMap<String,Object> categoryObj : category) {
            listOfCategories.add((String) categoryObj.get("category"));
        }
        return listOfCategories;
    }

    public void getBookFromSQL() throws SQLException {
        ArrayList<Book> books = new ArrayList<>();

        Statement stmt = SQL.getStmt();
        String query = "SELECT * FROM book;";
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
        for(HashMap<String,Object> book : result) {
            Book newBook = new Book();
            newBook.setIsbn((String) book.get("isbn"));
            newBook.setTitle((String) book.get("title"));
            newBook.setDescription((String) book.get("description"));
            newBook.setPublishedDate((String) book.get("publishedDate"));
            newBook.setLanguage((String) book.get("language"));
            newBook.setAuthor(getBookAuthor(stmt, newBook.getIsbn()));
            newBook.setCategories(getBookCategory(stmt, newBook.getIsbn()));
            books.add(newBook);
        }
        LibraryManagement.books = books;
    }

    @Override
    public ArrayList<Book> getBooks() {
        return books;
    }

    @Override
    public ArrayList<Book> getBorrowedBooks() throws SQLException {
        ArrayList<Book> books = new ArrayList<>();

        Statement stmt = SQL.getStmt();
        String query = "SELECT * FROM userborrowbook;";
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);

        for(HashMap<String,Object> book : result) {
            String isbn = book.get("isbn").toString();
            BookForBorrow newBook = new BookForBorrow();
            newBook.setIsbn(isbn);
            newBook.setBorrowedDate((Date) book.get("borrowTime"));
            query = "SELECT * FROM book WHERE isbn='" + isbn + "';";
            ResultSet bookRs = stmt.executeQuery(query);
            List<HashMap<String,Object>> bookList = ResultSetToList.convertResultSetToList(bookRs);
            newBook.setTitle(bookList.get(0).get("title").toString());
            newBook.setDescription(bookList.get(0).get("description").toString());
            newBook.setPublishedDate(bookList.get(0).get("publishedDate").toString());
            newBook.setLanguage(bookList.get(0).get("language").toString());
            newBook.setAuthor(getBookAuthor(stmt, newBook.getIsbn()));
            newBook.setCategories(getBookCategory(stmt, newBook.getIsbn()));
            books.add(newBook);
        }
        LibraryManagement.borrowedBooks = books;
        return borrowedBooks;
    }

    @Override
    public boolean doesExists(String isbn) throws SQLException {
        Statement stmt = SQL.getStmt();
        String query = "SELECT * FROM book WHERE isbn='" + isbn + "';";
        ResultSet rs = stmt.executeQuery(query);
        return rs.isBeforeFirst();
    }

    @Override
    public int getCountBook(String isbn) throws SQLException {
        Statement stmt = SQL.getStmt();
        String query = "SELECT * FROM book WHERE isbn='" + isbn + "';";
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> book = ResultSetToList.convertResultSetToList(rs);
        return (int) book.get(0).get("bookCount");
    }

    @Override
    public boolean borrowBookYet(String isbn) throws SQLException {
        Statement stmt = SQL.getStmt();
        String query = "SELECT * FROM userborrowbook WHERE isbn='" + isbn + "';";
        ResultSet rs = stmt.executeQuery(query);
        return rs.isBeforeFirst();
    }

    @Override
    public void borrowBook(String isbn) throws SQLException {
        if(borrowBookYet(isbn)) {
            //report that the book is already borrowed
            return;
        }
        int countBook = getCountBook(isbn);
        Statement stmt = SQL.getStmt();
        String query = "UPDATE book SET bookCount = " + (countBook - 1) +
                " WHERE isbn='" + isbn + "';";
        stmt.executeUpdate(query);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        query = "INSERT INTO userborrowbook " +
                "VALUES(" + CurrentUser.currentUser.getUid() + ", '" +
                isbn + "', " + currentTime + ");";
        stmt.executeUpdate(query);
    }

    @Override
    public void returnBook(String isbn) throws SQLException{

    }
}
