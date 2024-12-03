package APIManagement.GmailManagement;

import APIManagement.BookManagement.BookForBorrow;
import libUser.CurrentUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GmailSender {
    private static final String path = "./src/main/java/APIManagement/" +
            "GmailManagement/GmailSenderAPI/untitled/src/GmailSend.py";
    private static final String executable = "python";

    private static void sendEmail(ProcessBuilder processBuilder) throws IOException {
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public static void sendMailNotifyBorrowBook(BookForBorrow book) throws IOException {
        String emailTo = CurrentUser.currentUser.getEmail();

        String subject = "You have borrowed a book";
        StringBuilder msg = new StringBuilder();
        msg.append("Dear ").append(CurrentUser.currentUser.getName()).append(".\n\n");
        msg.append("You have borrowed a book from Titanium Library on ").append(book.getBorrowedDate()).append(". Book's info:\n");
        msg.append("ISBN: ").append(book.getIsbn()).append(".\n");
        msg.append("Title: ").append(book.getTitle()).append(".\n");
        msg.append("Author: ").append(book.getAuthor()).append(".\n");
        msg.append("Please return the book before next week");

        ProcessBuilder processBuilder = new ProcessBuilder(executable, path,
                emailTo, subject, msg.toString());
        sendEmail(processBuilder);
    }

    public static void sendMailNotifyReturnBook(BookForBorrow book) throws IOException {
        //Same but in a kinda different way
        String emailTo = CurrentUser.currentUser.getEmail();

        String subject = "You have returned a book";
        StringBuilder msg = new StringBuilder();
        msg.append("Dear ").append(CurrentUser.currentUser.getName()).append(".\n\n");
        msg.append("You have returned a book to Titanium Library on ").append(book.getReturnedDate()).append(". Book's info:\n");
        msg.append("ISBN: ").append(book.getIsbn()).append(".\n");
        msg.append("Title: ").append(book.getTitle()).append(".\n");
        msg.append("Author: ").append(book.getAuthor()).append(".\n");
        ProcessBuilder processBuilder = new ProcessBuilder(executable, path,
                emailTo, subject, msg.toString());
        sendEmail(processBuilder);
    }
}
