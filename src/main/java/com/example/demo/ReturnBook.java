package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

import SQLManagement.userManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libUser.CurrentUser;
import APIManagement.BookManagement.Book;

public class ReturnBook extends DefaultPanel {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button log; // find details

    @FXML
    private Button log1; // return book

    @FXML
    private Button log11; // home

    @FXML
    private TextField ret_search;
    @FXML
    private TextField ret_name;
    @FXML
    private TextField ret_issue;
    @FXML
    private TextField ret_due;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public boolean checkFind() {
        if (ret_search.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid information");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public void finding() {
        if (checkFind()) {
            String info = ret_search.getText();
            CurrentUser curUser = new CurrentUser();
            String res = String.valueOf(curUser.currentUser.getUid());
            String sql = "select * from book join userborrowbook on book.isbn = userborrowbook.isbn where userborrowbook.uid = '" + res + "' and book.isbn = '" + info + "'";

            connect = database.connectDb();

            try {
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                Book book;
                while (result.next()) {
                    book = new Book(info, result.getString("title"), result.getDate("issueDate"), result.getDate("returnDate"));
                    ret_name.setText(book.getTitle());
                    ret_issue.setText(String.valueOf(book.getIsbn()));
                    ret_due.setText(String.valueOf(book.getReturnDate()));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void returning() throws SQLException{

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to return?");
        Optional<ButtonType> option = alert.showAndWait();

        if(option.get().equals(ButtonType.OK)) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System notification");
            alert.setHeaderText(null);
            alert.setContentText("Returned successfully!");
            alert.showAndWait();
        }

        String info = ret_search.getText();
        userManagement.returnBook(info);
    }


    @FXML
    void initialize() {
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'ReturnBook.fxml'.";
        assert log1 != null : "fx:id=\"log1\" was not injected: check your FXML file 'ReturnBook.fxml'.";
        assert log11 != null : "fx:id=\"log11\" was not injected: check your FXML file 'ReturnBook.fxml'.";

    }

    public void toHome(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
        super.changeSceneTo(home);
    }
}
