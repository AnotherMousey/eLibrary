package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MyCollections extends DefaultPanel {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button log12;

    @FXML
    void initialize() {
        assert log12 != null : "fx:id=\"log12\" was not injected: check your FXML file 'MyCollections.fxml'.";

    }

    public void toHome(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
        super.changeSceneTo(home);
    }
}
