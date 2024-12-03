package ReportManagement;

import APIManagement.BookManagement.BookForBorrow;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class AlertReport extends LocalReporter {
    public AlertReport(Reporter reporter) {
        super(reporter);
    }

    @Override
    public void report(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setContentText(message);
        alert.show();
    }

    @Override
    public void report(String type, BookForBorrow book) {

    }

    @Override
    public void report(Label label, String msg) {

    }
}
