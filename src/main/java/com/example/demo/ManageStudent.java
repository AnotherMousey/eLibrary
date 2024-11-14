package com.example.demo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ManageStudent {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button log;

    @FXML
    private Button log1;

    @FXML
    private Button log11;

    @FXML
    void initialize() {
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'ManageStudent.fxml'.";
        assert log1 != null : "fx:id=\"log1\" was not injected: check your FXML file 'ManageStudent.fxml'.";
        assert log11 != null : "fx:id=\"log11\" was not injected: check your FXML file 'ManageStudent.fxml'.";
    }

}
