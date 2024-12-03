package ReportManagement;

import APIManagement.BookManagement.BookForBorrow;
import javafx.scene.control.Label;

public class BaseReport implements Reporter {

    @Override
    public void report(String message) {
        System.out.println(message);
    }

    @Override
    public void report(String type, BookForBorrow book) {

    }

    @Override
    public void report(Label label, String msg) {

    }


}
