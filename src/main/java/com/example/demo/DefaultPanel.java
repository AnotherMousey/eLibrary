package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DefaultPanel {
    private static Stage currentStage;

    public Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage stage) {
        currentStage = stage;
    }

    public void changeSceneTo(Parent parent) {
        Scene scene = new Scene(parent);
        getCurrentStage().setScene(scene);
        getCurrentStage().show();
    }
}
