package LibraryManagement.Interfaces;

import java.sql.SQLException;

public interface doesBookExists {
    boolean doesExists(String isbn) throws SQLException;
    int getCountBook(String isbn) throws SQLException;
}
