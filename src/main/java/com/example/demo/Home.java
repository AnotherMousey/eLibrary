package com.example.demo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Home {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane ManageBook;

    @FXML
    private Pane ManageStudent;

    @FXML
    private Pane Return;

    @FXML
    private Pane Review;

    @FXML
    private Pane books;

    @FXML
    private Button log;

    @FXML
    private Label profile;

    @FXML
    private Label profile1;

    @FXML
    void initialize() {
        assert ManageBook != null : "fx:id=\"ManageBook\" was not injected: check your FXML file 'Home.fxml'.";
        assert ManageStudent != null : "fx:id=\"ManageStudent\" was not injected: check your FXML file 'Home.fxml'.";
        assert Return != null : "fx:id=\"Return\" was not injected: check your FXML file 'Home.fxml'.";
        assert Review != null : "fx:id=\"Review\" was not injected: check your FXML file 'Home.fxml'.";
        assert books != null : "fx:id=\"books\" was not injected: check your FXML file 'Home.fxml'.";
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'Home.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'Home.fxml'.";
        assert profile1 != null : "fx:id=\"profile1\" was not injected: check your FXML file 'Home.fxml'.";

    }

}
