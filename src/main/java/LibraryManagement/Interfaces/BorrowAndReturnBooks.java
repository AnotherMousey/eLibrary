package LibraryManagement.Interfaces;

import java.sql.SQLException;

public interface BorrowAndReturnBooks {
    public boolean borrowBookYet(String isbn) throws SQLException;
    public void borrowBook(String isbn) throws SQLException;
    public void returnBook(String isbn) throws SQLException;
}
