package com.example.demo;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import ReportManagement.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
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
        log.setDefaultButton(true);
    }

    public boolean checkLogin() {
        Reporter reporter = new BaseReport();
        reporter = new AlertReport(reporter);
        String msg = CurrentUser.login(username.getText(), password.getText());
        if(msg.equals("Incorrect username or password")) {
            reporter.report(msg);
            return false;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("System Notification");
        alert.setHeaderText(null);
        alert.setContentText("Login successfully!");
        alert.showAndWait();
        return true;
    }

    public void toLogIn(ActionEvent event) throws IOException {
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
}
