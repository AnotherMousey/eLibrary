package LibraryManagement.Management;

import APIManagement.BookManagement.Book;
import LibraryManagement.Interfaces.*;
import SQLManagement.*;
import libUser.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminManagement extends SQL implements AddOrDeleteBooks,
                                                    GetUsers {

    private ArrayList<libraryUser> users;

    @Override
    public void addBook(Book book) throws SQLException {
        bookManagement.addBook(book);
        LibraryManagement.addBook(book);
    }

    @Override
    public void deleteBook(Book book) throws SQLException {
        Statement stmt = getStmt();
        String query = "DELETE FROM bookandcategory WHERE isbn = '" + book.getIsbn() + "'";
        stmt.executeUpdate(query);
        query = "DELETE FROM bookandauthor WHERE isbn = '" + book.getIsbn() + "'";
        stmt.executeUpdate(query);
        query = "DELETE FROM book WHERE isbn = '" + book.getIsbn() + "'";
        stmt.executeUpdate(query);
    }

    public ArrayList<libraryUser> getUsers() {
        return this.users;
    }

    @Override
    public void getUsersFromSQL() throws SQLException {
        ArrayList<libraryUser> users = new ArrayList<>();
        Statement stmt = getStmt();
        String query = "SELECT * FROM users";
        ResultSet rs = stmt.executeQuery(query);
        List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
        for(HashMap<String,Object> row : result) {
            libraryUser user;
            if(row.get("priority").equals(1)) user = new user();
            else continue;
            user.setEmail((String) row.get("email"));
            user.setName((String) row.get("name"));
            user.setPassword((String) row.get("password"));
            user.setUsername((String) row.get("username"));
            user.setUid((int) row.get("uid"));
            users.add(user);
        }
        this.users = users;
    }
}
