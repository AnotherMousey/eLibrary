package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import SQLManagement.userManagement;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import libUser.CurrentUser;

public class Settings extends DefaultPanel {

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
    private Label profile1;

    @FXML
    private TextField cur_pass;
    @FXML
    private TextField new_pass;
    @FXML
    private TextField repeat_pass;

    public void clearField() {
        cur_pass.clear();
        new_pass.clear();
        repeat_pass.clear();
    }

    public boolean checkChangePass(){
        Alert alert;
        CurrentUser curUser = new CurrentUser();
        if (cur_pass.getText().equals("") || new_pass.getText().equals("") || repeat_pass.getText().equals("")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
            clearField();
            return false;
        } else if (!new_pass.getText().equals(repeat_pass.getText())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please enter the new password again");
            alert.showAndWait();
            return false;
        }
        /*else if (!cur_pass.getText().equals(curUser.currentUser.getPassword())){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Wrong password");
            alert.showAndWait();
            return false;
        }
         */
        return true;
    }

    public void changePass() throws IOException, SQLException {
        if (checkChangePass()) {
            /**
             * lay user hien tai ra r change pass
             * set password or sth
             */
            CurrentUser curUser = new CurrentUser();
            String res = new_pass.getText();
            curUser.currentUser.setPassword(res);
            userManagement.updatePassword(curUser.currentUser.getUid(), new_pass.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System Notification");
            alert.setHeaderText(null);
            alert.setContentText("Password changed successfully");
            alert.showAndWait();
            Parent setting = FXMLLoader.load(getClass().getResource("Settings.fxml"));
            super.changeSceneTo(setting);
        }
    }

    @FXML
    void initialize() {
        assert log121 != null : "fx:id=\"log121\" was not injected: check your FXML file 'Settings.fxml'.";
        assert log1211 != null : "fx:id=\"log1211\" was not injected: check your FXML file 'Settings.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'Settings.fxml'.";
        assert profile1 != null : "fx:id=\"profile1\" was not injected: check your FXML file 'Settings.fxml'.";
    }

    public void toHome(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
        super.changeSceneTo(home);
    }

}
