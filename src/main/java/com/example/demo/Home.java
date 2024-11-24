package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import libUser.CurrentUser;

public class Home extends DefaultPanel {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane ManageBook;

    @FXML
    private Pane ManageStudent;

    @FXML
    private Pane Return;

    @FXML
    private Pane Review;

    @FXML
    private Pane books;

    @FXML
    private Button log;

    @FXML
    private Label profile;

    @FXML
    private Label profile1;

    @FXML 
    private Label displayName;

    @FXML
    void initialize() {
        assert ManageBook != null : "fx:id=\"ManageBook\" was not injected: check your FXML file 'Home.fxml'.";
        assert ManageStudent != null : "fx:id=\"ManageStudent\" was not injected: check your FXML file 'Home.fxml'.";
        assert Return != null : "fx:id=\"Return\" was not injected: check your FXML file 'Home.fxml'.";
        assert Review != null : "fx:id=\"Review\" was not injected: check your FXML file 'Home.fxml'.";
        assert books != null : "fx:id=\"books\" was not injected: check your FXML file 'Home.fxml'.";
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'Home.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'Home.fxml'.";
        assert profile1 != null : "fx:id=\"profile1\" was not injected: check your FXML file 'Home.fxml'.";
        displayName.setText(CurrentUser.currentUser.getName());
    }

    public void toBooks(MouseEvent event) throws IOException {
        Parent books = FXMLLoader.load(getClass().getResource("Books.fxml"));
        super.changeSceneTo(books);
    }

    public void toReturnBooks(MouseEvent event) throws IOException {
        Parent returnBook = FXMLLoader.load(getClass().getResource("ReturnBook.fxml"));
        super.changeSceneTo(returnBook);
    }

    public void toManageBook(MouseEvent event) throws IOException {
        if(CurrentUser.currentUser.getAuthority() == 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Lỗi quyền truy cập");
            alert.setContentText("Bạn không có quyền truy cập vào mục này");
            alert.show();
            return;
        }
        Parent manageBook = FXMLLoader.load(getClass().getResource("ManageBook.fxml"));
        super.changeSceneTo(manageBook);
    }

    public void toManageStudent(MouseEvent event) throws IOException {
        if(CurrentUser.currentUser.getAuthority() == 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Lỗi quyền truy cập");
            alert.setContentText("Bạn không có quyền truy cập vào mục này");
            alert.show();
            return;
        }
        Parent manageStudent = FXMLLoader.load(getClass().getResource("ManageStudent.fxml"));
        super.changeSceneTo(manageStudent);
    }

    public void toCollections(MouseEvent event) throws IOException {
        Parent myCollections = FXMLLoader.load(getClass().getResource("MyCollections.fxml"));
        super.changeSceneTo(myCollections);
    }

    public void toMyProfile(MouseEvent event) throws IOException {
        Parent myProfile = FXMLLoader.load(getClass().getResource("MyProfile.fxml"));
        super.changeSceneTo(myProfile);
    }

    public void toSettings(MouseEvent event) throws IOException {
        Parent settings = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        super.changeSceneTo(settings);
    }

    public void logout(ActionEvent event) throws IOException {
        CurrentUser.logout();
        Parent login = FXMLLoader.load(getClass().getResource("Login.fxml"));
        super.changeSceneTo(login);
    }
}
