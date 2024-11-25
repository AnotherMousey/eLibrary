package com.example.demo;

import SQLManagement.SQL;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        SQL.main(new String[]{});
        DefaultPanel.setCurrentStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Titanium's library management platform");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinHeight(550);
        primaryStage.setMinWidth(650);
        primaryStage.show();
    }
}
