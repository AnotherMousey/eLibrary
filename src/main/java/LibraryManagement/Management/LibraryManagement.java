package LibraryManagement.Management;

import APIManagement.BookManagement.Book;
import APIManagement.BookManagement.BookForBorrow;
import LibraryManagement.Interfaces.*;
import SQLManagement.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import libUser.CurrentUser;
import ReportManagement.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibraryManagement implements GetBooksInfo,
        BorrowedBooks, doesBookExists, BorrowAndReturnBooks {

    private static ObservableList<Book> books;
    private static ObservableList<Book> borrowedBooks;

    public static void addBook(Book book) {
        books.add(book);
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
        book.setAuthor(result.get(0).get("author").toString());
        book.setCategories(getBookCategory(stmt, isbn));
        book.setQuantity((int) result.get(0).get("quantity"));
        return book;
    }

    public void getBookFromSQL() throws SQLException {
        ObservableList<Book> books = FXCollections.observableArrayList();

        Statement stmt = SQL.getStmt();
        String query = "SELECT * FROM book;";
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
        for(HashMap<String,Object> book : result) {
            Book newBook = new Book();
            newBook.setIsbn((String) book.get("isbn"));
            newBook.setTitle((String) book.get("title"));
            newBook.setQuantity((int) book.get("quantity"));
            newBook.setAuthor((String) book.get("author"));
            books.add(newBook);
        }
        LibraryManagement.books = books;
    }

    @Override
    public ObservableList<Book> getBooks() {
        return books;
    }

    @Override
    public ObservableList<Book> getBorrowedBooks() throws SQLException {
        ObservableList<Book> books = FXCollections.observableArrayList();

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
        return (int) book.get(0).get("quantity");
    }

    @Override
    public boolean borrowBookYet(String isbn) throws SQLException {
        Statement stmt = SQL.getStmt();
        String query = "SELECT * FROM userborrowbook WHERE isbn='" + isbn + "' AND uid = " + CurrentUser.currentUser.getUid() + ";";
        ResultSet rs = stmt.executeQuery(query);
        return rs.isBeforeFirst();
    }

    @Override
    public void borrowBook(String isbn) throws SQLException {
        Reporter reporter = new BaseReport();
        if(borrowBookYet(isbn)) {
            reporter.report("Book is already borrowed");
            reporter = new AlertReport(reporter);
            reporter.report("Book is already borrowed");
            return;
        }
        int countBook = getCountBook(isbn);
        Statement stmt = SQL.getStmt();
        //delete the book from userreturnbook if exists
        String deleteQuery = "DELETE FROM userreturnbook WHERE uid = " + CurrentUser.currentUser.getUid() +
                " AND isbn = '" + isbn + "';";
        stmt.executeUpdate(deleteQuery);

        //update bookCount to bookCount - 1
        String query = "UPDATE book SET quantity = " + (countBook - 1) +
                " WHERE isbn='" + isbn + "';";
        stmt.executeUpdate(query);
        //insert borrow book
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        query = "INSERT INTO userborrowbook " +
                "VALUES(" + CurrentUser.currentUser.getUid() + ", '" +
                isbn + "', '" + currentTime + "');";
        stmt.executeUpdate(query);

        //report
        reporter.report("Mượn sách thành công, isbn = " + isbn);
        reporter = new AlertReport(reporter);
        reporter.report("Bạn đã mượn thành công quyển sách " + getSingleBook(stmt, isbn).getTitle());
        reporter = new EmailReport(reporter);
        reporter.report("BORROW", userManagement.getSingleBorrowBook(isbn));
    }

    @Override
    public void returnBook(String isbn) throws SQLException{
        BookForBorrow book = userManagement.getSingleBorrowBook(isbn);
        if(!borrowBookYet(isbn)) {
            //report that the book is not borrowed
            return;
        }
        int countBook = getCountBook(isbn);
        Statement stmt = SQL.getStmt();

        //delete the book from userborrowbook if exists
        String deleteQuery = "DELETE FROM userborrowbook WHERE uid = " + CurrentUser.currentUser.getUid() +
                " AND isbn = '" + isbn + "';";
        stmt.executeUpdate(deleteQuery);
        //update bookCount to bookCount + 1
        String query = "UPDATE book SET quantity = " + (countBook + 1) +
                " WHERE isbn='" + isbn + "';";
        stmt.executeUpdate(query);
        //add book to userreturnbook
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        query = "INSERT INTO userreturnbook " +
                "VALUES(" + CurrentUser.currentUser.getUid() + ", '" +
                isbn + "', '" + currentTime + "');";
        stmt.executeUpdate(query);

        //report
        Reporter reporter = new BaseReport();
        reporter.report("Trả sách thành công, isbn = " + isbn);
        reporter = new AlertReport(reporter);
        reporter.report("Bạn đã trả thành công quyển sách " + book.getTitle());
        reporter = new EmailReport(reporter);
        reporter.report("RETURN", book);
    }
}
