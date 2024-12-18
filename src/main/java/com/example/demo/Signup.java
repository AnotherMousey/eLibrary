package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import ReportManagement.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import libUser.CurrentUser;

public class Signup extends DefaultPanel{

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
        log.setDefaultButton(true);
    }

    public void backToLogin(ActionEvent event) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("Login.fxml"));
        super.changeSceneTo(login);
    }

    public void register(ActionEvent event) throws IOException, SQLException {
        Reporter reporter = new BaseReport();
        reporter = new AlertReport(reporter);
        if(!Password.getText().equals(RePassword.getText())) {
            reporter.report("Password does not match");
        }
        else {
            String msg = CurrentUser.register(username.getText(), Password.getText(), Email.getText());
            if(msg.equals("Username already exists") || msg.equals("Password is not in right format")) {
                reporter.report(msg);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                registerMsg.setText(msg);
                alert.setTitle("System Notification");
                alert.setHeaderText(null);
                alert.setContentText("Register account successfully!");
                alert.showAndWait();
                backToLogin(event);
            }
        }
    }
}
