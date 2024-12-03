package ReportManagement;

import APIManagement.BookManagement.BookForBorrow;
import javafx.scene.control.Label;

public interface Reporter {
    void report(String message);
    void report(String type, BookForBorrow book);
    void report(Label label, String msg);
}
