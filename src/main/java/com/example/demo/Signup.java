package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

public class Signup {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button log;

    @FXML
    private Button log1;

    @FXML
    private TextField Email;

    @FXML
    private PasswordField Password;

    @FXML
    private PasswordField RePassword;

    @FXML
    private TextField username;

    @FXML
    private Label registerMsg;

    @FXML
    void initialize() {
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'Signup.fxml'.";
        assert log1 != null : "fx:id=\"log1\" was not injected: check your FXML file 'Signup.fxml'.";
    }

    public void backToLogin(ActionEvent event) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(login);
        stage.setScene(scene);
        stage.show();
    }

    public void register(ActionEvent event) throws IOException, SQLException {
        if(!Password.getText().equals(RePassword.getText())) {
            registerMsg.setText("Password does not match");
        }
        else {
            String msg = CurrentUser.register(username.getText(), Password.getText(), Email.getText());
            if(msg.equals("Username already exists") || msg.equals("Password is not in right format")) {
                registerMsg.setText(msg);
            }
            else {
                registerMsg.setText(msg);
                backToLogin(event);
            }
        }
    }
}
