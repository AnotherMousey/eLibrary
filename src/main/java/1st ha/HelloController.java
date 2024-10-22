package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Button sign;

    @FXML
    private Label lb;

    @FXML
    void signPressed(ActionEvent event) {
        //lb.setText("Welcome to app!");
    }

}