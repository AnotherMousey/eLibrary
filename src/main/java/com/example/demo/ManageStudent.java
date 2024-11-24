package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManageStudent extends DefaultPanel {

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
    private Button log12;

    @FXML
    private Button log13;
    @FXML
    private TableColumn<?, ?> mngU_col_uid;
    @FXML
    private TableColumn<?, ?> mngU_col_name;
    @FXML
    private TableColumn<?, ?> mngU_col_username;
    @FXML
    private TableColumn<?, ?> mngU_col_email;
    @FXML
    private TableView<?> mngU_tableView;
    @FXML
    private TextField mngU_search;
    @FXML
    private TextField mngU_uid;
    @FXML
    private TextField mngU_name;
    @FXML
    private TextField mngU_username;
    @FXML
    private TextField mngU_email;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    

    @FXML
    void initialize() {
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'ManageStudent.fxml'.";
        assert log1 != null : "fx:id=\"log1\" was not injected: check your FXML file 'ManageStudent.fxml'.";
        assert log11 != null : "fx:id=\"log11\" was not injected: check your FXML file 'ManageStudent.fxml'.";
        assert log12 != null : "fx:id=\"log12\" was not injected: check your FXML file 'ManageStudent.fxml'.";
    }

    public void toHome(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
        super.changeSceneTo(home);
    }

}
