package com.example.demo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Settings {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button log121;

    @FXML
    private Button log1211;

    @FXML
    private Label profile;

    @FXML
    private Label profile1;

    @FXML
    private Label profile11;

    @FXML
    void initialize() {
        assert log121 != null : "fx:id=\"log121\" was not injected: check your FXML file 'Settings.fxml'.";
        assert log1211 != null : "fx:id=\"log1211\" was not injected: check your FXML file 'Settings.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'Settings.fxml'.";
        assert profile1 != null : "fx:id=\"profile1\" was not injected: check your FXML file 'Settings.fxml'.";
        assert profile11 != null : "fx:id=\"profile11\" was not injected: check your FXML file 'Settings.fxml'.";

    }

}
