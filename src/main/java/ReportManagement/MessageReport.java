package ReportManagement;

import APIManagement.BookManagement.BookForBorrow;
import javafx.scene.control.Label;

public class MessageReport extends LocalReporter {
    public MessageReport(Reporter reporter) {
        super(reporter);
    }

    @Override
    public void report(String message) {
        //This didn't do anything
    }

    @Override
    public void report(String type, BookForBorrow book) {

    }

    public void report(Label label, String message) {
        label.setText(message);
    }
}
