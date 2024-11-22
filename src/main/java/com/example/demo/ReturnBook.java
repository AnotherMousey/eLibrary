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

public class ReturnBook extends DefaultPanel {

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
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'ReturnBook.fxml'.";
        assert log1 != null : "fx:id=\"log1\" was not injected: check your FXML file 'ReturnBook.fxml'.";
        assert log11 != null : "fx:id=\"log11\" was not injected: check your FXML file 'ReturnBook.fxml'.";

    }

    public void toHome(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
        super.changeSceneTo(home);
    }
}
