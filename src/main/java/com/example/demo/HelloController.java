package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    public Button welcome;
    @FXML
    private Label welcomeText;
    @FXML
    private Label welcomeLabel;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onWelcomeButtonClick(ActionEvent actionEvent) {
        welcomeLabel.setText("Welcome to OOP 11!");
    }
}