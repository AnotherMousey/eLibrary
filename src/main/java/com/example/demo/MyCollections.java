package com.example.demo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MyCollections {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button log12;

    @FXML
    void initialize() {
        assert log12 != null : "fx:id=\"log12\" was not injected: check your FXML file 'My collections.fxml'.";

    }

}
