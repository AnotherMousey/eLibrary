package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import libUser.CurrentUser;
import APIManagement.BookManagement.Book;

public class Books extends DefaultPanel{

    @FXML
    private TableColumn<Book, String> books_col_number;
    @FXML
    private TableColumn<Book, String> books_col_title;
    @FXML
    private TableColumn<Book, String> books_col_author;
    @FXML
    private TableColumn<Book, String> books_col_isbn;
    @FXML
    private TableColumn<Book, String> books_col_remain;
    @FXML
    private TableView<Book> avaiBooks_tableView;
    @FXML
    private TextField avaiBookSearch;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button log12; //home button

    @FXML
    private Button log121; //save button
    /*
    public ObservableList<Book> borrowedBookListData() {

        String sql = "select * from userborrowbook" +
                "join book on book.isbn = isbn";
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Book book;

            while (result.next()) {
                book =
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     */


    @FXML
    void initialize() {
        assert log12 != null : "fx:id=\"log12\" was not injected: check your FXML file 'Books.fxml'.";
        assert log121 != null : "fx:id=\"log121\" was not injected: check your FXML file 'Books.fxml'.";

    }

    public void toHome(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
        super.changeSceneTo(home);
    }
}
