package APIManagement.BookManagement;

import java.sql.Timestamp;
import java.util.Date;

public class BookForBorrow extends Book{
    Timestamp borrowedDate;

    public BookForBorrow(Book book) {
        super(book);
    }

    public BookForBorrow() {

    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Timestamp borrowedDate) {
        this.borrowedDate = borrowedDate;
    }
}
