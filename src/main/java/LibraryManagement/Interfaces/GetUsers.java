package LibraryManagement.Interfaces;

import libUser.libraryUser;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GetUsers {
    void getUsersFromSQL() throws SQLException;
    ArrayList<libraryUser> getUsers();
}
