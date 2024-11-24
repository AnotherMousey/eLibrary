package LibraryManagement.Management;

import APIManagement.BookManagement.Book;
import LibraryManagement.Interfaces.*;
import SQLManagement.ResultSetToList;
import SQLManagement.SQL;
import libUser.CurrentUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
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

    private static ArrayList<String> getBookCategory(Statement stmt, String isbn) throws SQLException {
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

    public static Book getSingleBook(Statement stmt, String isbn) throws SQLException {
        String query = "SELECT * FROM book WHERE ISBN='" + isbn + "';";
        Book book = new Book();
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
        book.setTitle(result.get(0).get("title").toString());
        book.setIsbn(isbn);
        book.setAuthors(getBookAuthor(stmt, isbn));
        book.setCategories(getBookCategory(stmt, isbn));
        book.setLanguage(result.get(0).get("language").toString());
        book.setPublisher(result.get(0).get("publishedDate").toString());
        book.setDescription(result.get(0).get("description").toString());
        book.setQuantity((int) result.get(0).get("bookCount"));
        book.setAuthor(result.get(0).get("mainAuthor").toString());
        return book;
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
            newBook.setAuthors(getBookAuthor(stmt, newBook.getIsbn()));
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
            books.add(getSingleBook(stmt, book.get("isbn").toString()));
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
        //delete the book from userreturnbook if exists
        String deleteQuery = "DELETE FROM userreturnbook WHERE uid = " + CurrentUser.currentUser.getUid() +
                "AND isbn = '" + isbn + "';";
        stmt.executeUpdate(deleteQuery);
        //delete the book from userborrowbook if exists
        deleteQuery = "DELETE FROM userborrowbook WHERE uid = " + CurrentUser.currentUser.getUid() +
                "AND isbn = '" + isbn + "';";
        stmt.executeUpdate(deleteQuery);

        //update bookCount to bookCount - 1
        String query = "UPDATE book SET bookCount = " + (countBook - 1) +
                " WHERE isbn='" + isbn + "';";
        stmt.executeUpdate(query);
        //insert borrow book
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        query = "INSERT INTO userborrowbook " +
                "VALUES(" + CurrentUser.currentUser.getUid() + ", '" +
                isbn + "', " + currentTime + ");";
        stmt.executeUpdate(query);
    }

    @Override
    public void returnBook(String isbn) throws SQLException{
        if(!borrowBookYet(isbn)) {
            //report that the book is not borrowed
            return;
        }
        Statement stmt = SQL.getStmt();
        String query = "SELECT * FROM userborrowbook WHERE isbn='" + isbn +
                "' AND uid = " + CurrentUser.currentUser.getUid();
        ResultSet rs = stmt.executeQuery(query);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        query = "INSERT INTO userreturnbook" +
                "VALUES(" + CurrentUser.currentUser.getUid() + ", '" +
                isbn + "', " + currentTime + ");";
        stmt.executeUpdate(query);
    }
}
