package ReportManagement;

import APIManagement.BookManagement.BookForBorrow;
import APIManagement.GmailManagement.GmailSender;
import javafx.scene.control.Label;
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

    public void report(String type, BookForBorrow book) {
        setType(type);
        setBook(book);
        Thread t1 = new Thread(this);
        t1.start();
    }

    @Override
    public void report(Label label, String msg) {

    }

    @Override
    public void report(String message) {
        //This didn't do anything `
    }

    @Override
    public void run() {
        System.out.println("Thread is running");
        if(this.type.equals(borrowType)) {
            try {
                GmailSender.sendMailNotifyBorrowBook(book);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if(this.type.equals(returnType)) {
            try {
                GmailSender.sendMailNotifyReturnBook(book);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Unsupported report type");
        }
    }
}
