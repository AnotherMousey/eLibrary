package SQLManagement;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import APIManagement.BookManagement.BookForBorrow;
import LibraryManagement.Management.LibraryManagement;
import ReportManagement.*;
import libUser.CurrentUser;

public class userManagement extends SQL{
    private static Statement stmt;

    /**
     * check if the password is valid.
     * @param password password
     * a password is valid if it contains lowercase, uppercase, and a number, and no special characters.
     * @return true if valid, false otherwise
     */
    public static boolean checkValidPassword(String password) {
        int cntLowerCase = 0, cntUpperCase = 0, cntNumber = 0;
        for (char c : password.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                if (c <= '9') {
                    cntNumber++;
                } else if (c >= 'a') {
                    cntLowerCase++;
                } else {
                    cntUpperCase++;
                }
            } else {
                return false;
            }
        }
        return cntNumber > 0 && cntUpperCase > 0 && cntLowerCase > 0 && password.length() <= 20;
    }

    public static boolean checkUserIfExists(String username) throws SQLException {
        stmt = getStmt();
        String query = "SELECT * FROM user WHERE username = '" + username + "';";
        ResultSet rs = stmt.executeQuery(query);
        return rs.isBeforeFirst();
    }

    public static void addUser(String username, String password,
                               int priority, String email) throws SQLException {
        stmt = getStmt();
        String query = "INSERT INTO user (username, password, priority, email)"
                + " VALUES ('" + username + "', '" + password + "', " + priority
                + ", '" + email + "');";
        stmt.executeUpdate(query);
    }

    public static int getUserUID(String username, String password) throws SQLException {
        stmt = getStmt();
        String query = "SELECT * FROM user WHERE username = '" + username
                + "' AND password = '" + password + "';";
        ResultSet rs = stmt.executeQuery(query);
        if(!rs.isBeforeFirst()) {
            return -1;
        }
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
        return (int) result.get(0).get("uid");
    }

    public static String getName(int uid) throws SQLException {
        stmt = getStmt();
        String query = "SELECT * FROM user WHERE uid = '" + uid + "';";
        ResultSet rs = stmt.executeQuery(query);
        if(!rs.isBeforeFirst()) {
            return null;
        }
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
        return (String) result.get(0).get("name");
    }

    public static String getUserName(int uid) throws SQLException {
        stmt = getStmt();
        String query = "SELECT * FROM user WHERE uid = " + uid + ";";
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
        return (String) result.get(0).get("username");
    }

    public static String getUserEmail(int uid) throws SQLException {
        stmt = getStmt();
        String query = "SELECT * FROM user WHERE uid = " + uid + ";";
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
        return (String) result.get(0).get("email");
    }

    public static int getUserPriority(int uid) throws SQLException {
        stmt = getStmt();
        String query = "SELECT * FROM user WHERE uid = " + uid + ";";
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
        return (int) result.get(0).get("priority");
    }

    public static void updatePassword(int uid, String newPassword) throws SQLException {
        stmt = getStmt();
        String query = "UPDATE user SET password = '"
                + newPassword + "' WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
    }

    public static void updateName(int uid, String newName) throws SQLException {
        stmt = getStmt();
        String query = "UPDATE user SET name = '"
                + newName + "' WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
        System.out.println(query);
    }

    public static void updateEmail(int uid, String newEmail) throws SQLException {
        stmt = getStmt();
        String query = "UPDATE user SET email = '"
                + newEmail + "' WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
    }

    public static void updatePriority(int uid, int newPriority) throws SQLException {
        stmt = getStmt();
        String query = "UPDATE user SET priority = '"
                + newPriority + "' WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
    }

    public static void deleteUser(int uid) throws SQLException {
        stmt = getStmt();
        String query = "DELETE FROM user WHERE uid = " + uid + ";";
        stmt.executeUpdate(query);
    }

    public static void borrowBook(String isbn) throws SQLException {
        Reporter reporter = new BaseReport();
        reporter = new AlertReport(reporter);
        LibraryManagement lm = new LibraryManagement();
        if(!lm.doesExists(isbn)) {
            reporter.report("The book does not exist.");
            return;
        }
        int bookCount = lm.getCountBook(isbn);
        if(bookCount == 0) {
            reporter.report("This book is out of stock.");
            return;
        }
        System.out.println("Borrowing");
        lm.borrowBook(isbn);
    }

    public static void returnBook(String isbn) throws SQLException {
        LibraryManagement lm = new LibraryManagement();
        Reporter reporter = new BaseReport();
        reporter = new AlertReport(reporter);
        if(!lm.doesExists(isbn)) {
            reporter.report("The book does not exist.");
            return;
        }
        lm.returnBook(isbn);
    }

    public static BookForBorrow getSingleBorrowBook(String isbn) throws SQLException {
        Statement stmt = getStmt();
        BookForBorrow book = new BookForBorrow();

        String query = "SELECT * FROM userborrowbook WHERE isbn = '" + isbn + "' AND uid = " + CurrentUser.currentUser.getUid();
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);

        book.setBook(LibraryManagement.getSingleBook(stmt, isbn));
        book.setBorrowedDate((Timestamp) result.get(0).get("borrowDate"));
        book.setReturnedDate((Timestamp) result.get(0).get("returnDate"));
        return book;
    }

    public static ArrayList<BookForBorrow> getBorrowedBook() throws SQLException {
        ArrayList<BookForBorrow> borrowedBooks = new ArrayList<>();
        Statement stmt = getStmt();

        //get all borrowed books
        String query = "SELECT * FROM userborrowbook WHERE uid = " + CurrentUser.currentUser.getUid() + ";";
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
        for(HashMap<String,Object> book : result) {
            BookForBorrow borrowBook = new BookForBorrow();
            borrowBook.setBook(LibraryManagement.getSingleBook(stmt, book.get("isbn").toString()));
            borrowBook.setBorrowedDate((Timestamp) book.get("borrowTime"));

            //get return time from userreturnbook if exists
            String newQuery = "SELECT returnTime FROM userreturnbook WHERE uid = " + CurrentUser.currentUser.getUid() +
                    "AND isbn = '" + borrowBook.getIsbn() + "';";
            ResultSet rs2 = stmt.executeQuery(newQuery);
            if(rs2.isBeforeFirst()) {
                continue;
            }
            List<HashMap<String,Object>> result2 = ResultSetToList.convertResultSetToList(rs2);
            borrowBook.setReturnedDate((Timestamp) result2.get(0).get("returnTime"));

            borrowedBooks.add(borrowBook);
        }
        return borrowedBooks;
    }

    public static ArrayList<BookForBorrow> getReturnedBook() throws SQLException {
        ArrayList<BookForBorrow> returnedBooks = new ArrayList<>();
        Statement stmt = getStmt();
        String query = "SELECT * FROM userreturnbook WHERE uid = " + CurrentUser.currentUser.getUid() + ";";
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
        for(HashMap<String,Object> book : result) {
            BookForBorrow returnBook = new BookForBorrow();
            returnBook.setBook(LibraryManagement.getSingleBook(stmt, book.get("isbn").toString()));
            returnBook.setBorrowedDate((Timestamp) book.get("returnTime"));

            //get borrow time from userborrowbook
            String newQuery = "SELECT borrowTime FROM userborrowbook WHERE uid = " + CurrentUser.currentUser.getUid() +
                    "AND isbn = '" + returnBook.getIsbn() + "';";
            ResultSet rs2 = stmt.executeQuery(newQuery);
            if(rs2.isBeforeFirst()) {
                continue;
            }
            List<HashMap<String,Object>> result2 = ResultSetToList.convertResultSetToList(rs2);
            returnBook.setBorrowedDate((Timestamp) result2.get(0).get("borrowTime"));

            returnedBooks.add(returnBook);
        }
        return returnedBooks;
    }
}
