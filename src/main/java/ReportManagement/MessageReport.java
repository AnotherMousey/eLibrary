package ReportManagement;

import javafx.scene.control.Label;

public class MessageReport extends LocalReporter {
    public MessageReport(Reporter reporter) {
        super(reporter);
    }

    @Override
    public void report(String message) {
        //This didn't do anything
    }

    public void report(Label label, String message) {
        label.setText(message);
    }
}
