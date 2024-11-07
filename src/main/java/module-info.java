module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires json.simple;
    requires java.sql;
    requires mysql.connector.j;
    requires annotations;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports APIManagement.BookManagement;
    opens APIManagement.BookManagement to javafx.fxml;
    exports APIManagement;
    opens APIManagement to javafx.fxml;
    exports SQLManagement;
    opens SQLManagement to javafx.fxml;
}