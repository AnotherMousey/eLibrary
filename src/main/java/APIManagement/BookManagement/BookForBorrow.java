package APIManagement.BookManagement;

import java.util.Date;

public class BookForBorrow extends Book{
    Date borrowedDate;

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }
}
