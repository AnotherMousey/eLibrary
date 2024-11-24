package APIManagement.BookManagement;

import java.sql.Timestamp;
import java.util.Date;

public class BookForBorrow extends Book{
    Timestamp borrowedDate;
    Timestamp returnedDate;

    public BookForBorrow(Book book) {
        super(book);
    }

    public BookForBorrow() {

    }

    public Timestamp getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Timestamp borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Timestamp getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Timestamp returnDate) {
        this.returnedDate = returnDate;
    }
}
