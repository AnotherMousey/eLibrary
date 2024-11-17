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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import libUser.CurrentUser;

public class Home {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane Collections;

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
    void initialize() {
        assert Collections != null : "fx:id=\"Collections\" was not injected: check your FXML file 'Home.fxml'.";
        assert ManageBook != null : "fx:id=\"ManageBook\" was not injected: check your FXML file 'Home.fxml'.";
        assert ManageStudent != null : "fx:id=\"ManageStudent\" was not injected: check your FXML file 'Home.fxml'.";
        assert Return != null : "fx:id=\"Return\" was not injected: check your FXML file 'Home.fxml'.";
        assert Review != null : "fx:id=\"Review\" was not injected: check your FXML file 'Home.fxml'.";
        assert books != null : "fx:id=\"books\" was not injected: check your FXML file 'Home.fxml'.";
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'Home.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'Home.fxml'.";
    }

    public void toBooks(MouseEvent event) throws IOException {
        //Go to stage Book
    }

    public void toReturnBooks(MouseEvent event) throws IOException {
        Parent returnBook = FXMLLoader.load(getClass().getResource("ReturnBook.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(returnBook);
        stage.setScene(scene);
        stage.show();
    }

    public void toReviewBooks(MouseEvent event) throws IOException {
        Parent reviewBook = FXMLLoader.load(getClass().getResource("ReviewBook.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(reviewBook);
        stage.setScene(scene);
        stage.show();
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
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(manageBook);
        stage.setScene(scene);
        stage.show();
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
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(manageStudent);
        stage.setScene(scene);
        stage.show();
    }

    public void toCollections(MouseEvent event) throws IOException {
        //Go to stage collections
    }

    public void toMyProfile(MouseEvent event) throws IOException {
        //Go to my profile
    }

    public void logout(ActionEvent event) throws IOException {
        CurrentUser.logout();
        Parent login = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(login);
        stage.setScene(scene);
        stage.show();
    }
}
