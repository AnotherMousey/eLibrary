package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import SQLManagement.userManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import libUser.CurrentUser;

public class MyProfile extends DefaultPanel{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button log121;

    @FXML
    private Button log1211;

    @FXML
    private Label profile;

    @FXML
    private Label profile11;

    @FXML
    private TextField prof_name;
    @FXML
    private TextField prof_email;

    String oldName;
    String oldEmail;

    public void displayInfo() {
        CurrentUser curUser = new CurrentUser();
        prof_name.setText(curUser.currentUser.getName());
        prof_email.setText(curUser.currentUser.getEmail());
    }

    public boolean checkChange() {
        Alert alert;
        if (oldName.equals(prof_name.getText()) && oldEmail.equals(prof_email.getText())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Information unchanged");
            alert.showAndWait();
            return false;
        } else if (prof_name.equals("") || prof_email.equals("")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public void changeInfo() throws IOException, SQLException {
        String currentName = prof_name.getText();
        String currentEmail = prof_email.getText();

        if (checkChange()) {
            if (!currentName.equals(oldName)) {
                CurrentUser.currentUser.setName(currentName);
                userManagement.updateName(CurrentUser.currentUser.getUid(), currentName);
            }
            if (!currentEmail.equals(oldEmail)) {
                CurrentUser.currentUser.setEmail(currentEmail);
                userManagement.updateEmail(CurrentUser.currentUser.getUid(), currentEmail);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System Notification");
            alert.setHeaderText(null);
            alert.setContentText("Information changed successfully");
            alert.showAndWait();
            Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
            super.changeSceneTo(home);
        }
    }

    /**
     * luc an vao trang nay thi o 3 text field no se hien ttin name, username va email
     * hien tai cua account.
     * minh se sua truc tiep vao cai field do luon roi luc sau save lai ttin
     * cancel thi thoi
     */

    @FXML
    void initialize() {
        assert log121 != null : "fx:id=\"log121\" was not injected: check your FXML file 'MyProfile.fxml'.";
        assert log1211 != null : "fx:id=\"log1211\" was not injected: check your FXML file 'MyProfile.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'MyProfile.fxml'.";
        assert profile11 != null : "fx:id=\"profile11\" was not injected: check your FXML file 'MyProfile.fxml'.";
        displayInfo();
        oldName = prof_name.getText();
        oldEmail = prof_email.getText();
        log121.setDefaultButton(true);
    }

    public void toHome(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
        super.changeSceneTo(home);
    }

}
