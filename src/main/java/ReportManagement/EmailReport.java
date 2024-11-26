package ReportManagement;

import APIManagement.BookManagement.BookForBorrow;
import APIManagement.GmailManagement.GmailSender;
import libUser.CurrentUser;

import java.io.IOException;
import java.sql.Timestamp;

public class EmailReport extends SocialReporter {
    private static final String borrowType = "BORROW";
    private static final String returnType = "RETURN";
    private BookForBorrow book;
    private String type;

    public EmailReport(Reporter reporter) {
        super(reporter);
    }

    private void setBook(BookForBorrow book) {
        this.book = book;
    }

    private void setType(String type) {
        this.type = type;
    }

    @Override
    public void report(String message) {
        //This didn't do anything `
    }

    public void report(String type, BookForBorrow book) {
        setType(type);
        setBook(book);
        run();
    }

    @Override
    public void run() {
        String currentEmail = CurrentUser.currentUser.getEmail();
        if(this.type.equals(borrowType)) {
            try {
                GmailSender.sendMailNotifyBorrowBook(book);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                GmailSender.sendMailNotifyReturnBook(book);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
