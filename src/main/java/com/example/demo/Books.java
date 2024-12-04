package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.kenai.jffi.Library;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import libUser.CurrentUser;
import APIManagement.BookManagement.Book;
import LibraryManagement.Management.LibraryManagement;

public class Books extends DefaultPanel{

    @FXML
    private TableColumn<Book, String> books_col_status;
    @FXML
    private TableColumn<Book, String> books_col_title;
    @FXML
    private TableColumn<Book, String> books_col_author;
    @FXML
    private TableColumn<Book, String> books_col_isbn;
    @FXML
    private TableColumn<Book, String> books_col_quantity;
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

    public void clearSearch() {
        avaiBookSearch.clear();
    }

    public ObservableList<Book> bookListData() throws SQLException {
        ObservableList<Book> list = FXCollections.observableArrayList();
        LibraryManagement lm = new LibraryManagement();
        lm.getBookFromSQL();
        list = lm.getBooks();
        return list;
    }

    private ObservableList<Book> bookList;

    public void bookShowListData() throws SQLException {
        bookList = bookListData();

        books_col_isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        books_col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        books_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        books_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        books_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        avaiBooks_tableView.setItems(bookList);
    }

    public void bookSearch() {
        FilteredList<Book> filter = new FilteredList<>(bookList, e -> true);
        avaiBookSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateBook -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateBook.getTitle().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateBook.getAuthor().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateBook.getIsbn().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Book> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(avaiBooks_tableView.comparatorProperty());
        avaiBooks_tableView.setItems(sortedList);
    }


    @FXML
    void initialize() throws SQLException {
        assert log12 != null : "fx:id=\"log12\" was not injected: check your FXML file 'Books.fxml'.";
        assert log121 != null : "fx:id=\"log121\" was not injected: check your FXML file 'Books.fxml'.";

        bookShowListData();
        bookSearch();
    }

    public void getQueryResult(MouseEvent event) {
        bookSearch();
    }

    public void toHome(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
        super.changeSceneTo(home);
    }
}
