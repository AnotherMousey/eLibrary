package com.example.demo;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
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

public class Login extends DefaultPanel {

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

    public boolean checkLogin() {
        String msg = CurrentUser.login(username.getText(), password.getText());
        if(msg.equals("Incorrect username or password")) {
            loginMsg.setText(msg);
            return false;
        }
        loginMsg.setText(msg);
        return true;
    }

    public void login(ActionEvent event) throws IOException {
        if(!checkLogin()) {
            return;
        }
        Parent library = FXMLLoader.load(getClass().getResource("Home.fxml"));
        super.changeSceneTo(library);
    }

    public void toSignUp(ActionEvent event) throws IOException {
        Parent register = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        super.changeSceneTo(register);
    }

    public void onKeyPressed(KeyEvent event) throws IOException {
        if(event.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!checkLogin()) {
                return;
            }
            Parent library = FXMLLoader.load(getClass().getResource("Home.fxml"));
            super.changeSceneTo(library);
        }
    }
}
