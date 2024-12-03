package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import APIManagement.BookManagement.BookForBorrow;
import LibraryManagement.Management.LibraryManagement;
import SQLManagement.ResultSetToList;
import SQLManagement.SQL;
import SQLManagement.userManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private Label ret_name;
    @FXML
    private Label ret_issue;
    @FXML
    private Label ret_author;
    @FXML
    private Label ret_isbn;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;

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

    public void finding(ActionEvent event) throws SQLException {
        if (!checkFind()) {
            return;
        }
        Statement stmt = SQL.getStmt();
        BookForBorrow book = new BookForBorrow(LibraryManagement.getSingleBook(stmt, ret_search.getText()));
        LibraryManagement lm = new LibraryManagement();
        ret_name.setText(book.getTitle());
        ret_isbn.setText(book.getIsbn());
        ret_author.setText(book.getAuthor());
        if(lm.borrowBookYet(book.getIsbn())) {
            String query = "SELECT * FROM userborrowbook WHERE uid = " + CurrentUser.currentUser.getUid() +
                    " AND isbn = '" + book.getIsbn() + "'";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            List<HashMap<String,Object>> result = ResultSetToList.convertResultSetToList(rs);
            ret_issue.setText(String.valueOf(result.get(0).get("borrowTime")));
        }
    }

    public void borrowing(ActionEvent event) throws SQLException {
        if (!checkFind()) {
            return;
        }
        userManagement.borrowBook(ret_isbn.getText());
    }

    public void returning(ActionEvent event) throws SQLException {
        if (!checkFind()) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Bạm có muốn trả sách?");
        Optional<ButtonType> option = alert.showAndWait();

        userManagement.returnBook(ret_isbn.getText());
        ret_issue.setText("");
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
