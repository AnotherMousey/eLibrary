module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires json.simple;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;
    requires annotations;
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires java.desktop;

    opens libUser to javafx.base;
    opens com.example.demo to javafx.fxml;
    requires org.python.jython2.standalone;
    exports com.example.demo;
    exports APIManagement.BookManagement;
    opens APIManagement.BookManagement to javafx.fxml;
    exports APIManagement;
    opens APIManagement to javafx.fxml;
    exports SQLManagement;
    opens SQLManagement to javafx.fxml;
}