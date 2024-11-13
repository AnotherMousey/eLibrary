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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libUser.CurrentUser;

public class Login {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button log;

    @FXML
    private Button log1;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label loginMsg;

    @FXML
    public void initialize() {
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'Login.fxml'.";
        assert log1 != null : "fx:id=\"log1\" was not injected: check your FXML file 'Login.fxml'.";
    }

    public void login(ActionEvent event) throws IOException {
        String msg = CurrentUser.login(username.getText(), password.getText());
        if(msg.equals("Incorrect username or password")) {
            loginMsg.setText(msg);
            return;
        }
        loginMsg.setText(msg);
        Parent library = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(library);
        stage.setScene(scene);
        stage.show();
    }

    public void toSignUp(ActionEvent event) throws IOException {
        Parent register = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(register);
        stage.setScene(scene);
        stage.show();
    }

}
