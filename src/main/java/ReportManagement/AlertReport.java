package ReportManagement;

import javafx.scene.control.Alert;

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
}
