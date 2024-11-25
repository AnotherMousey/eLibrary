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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import APIManagement.BookManagement.Book;

public class MyCollections extends DefaultPanel {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button log12;

    @FXML
    private TableColumn<Book, Integer> unf_col_number;
    @FXML
    private TableColumn<Book, Integer> fin_col_number;
    @FXML
    private TableColumn<Book, String> unf_col_name;
    @FXML
    private TableColumn<Book, String> fin_col_name;
    @FXML
    private TableView<Book> unf_tableView;
    @FXML
    private TableView<Book> fin_tableView;
    @FXML
    private TextField clt_isbn;
    @FXML
    private TextField clt_title;
    @FXML
    private TextField clt_author;
    @FXML
    private TextField clt_issue;
    @FXML
    private TextField clt_due;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public ObservableList<Book> unfBookListData() {
        ObservableList<Book> listData = FXCollections.observableArrayList();
        String sql = "select b.* from book b join userborrowbook br " +
                "on b.isbn = br.isbn and ";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Book book;
            while (result.next()) {
                book = new Book(result.getString("isbn"), result.getString("title"), result.getString("author"), result.getDate("issueDate"), result.getDate("returnDate"));
                listData.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<Book> unfBookList;

    public void unfBookShowListData(){
        unfBookList = unfBookListData();
        Integer n = 1;
        unf_col_number.setCellValueFactory(new PropertyValueFactory<>(n.toString()));
        n += 1;
        unf_col_name.setCellValueFactory(new PropertyValueFactory<>("title"));

        unf_tableView.setItems(unfBookList);
    }

    public void unfBookSelect() {
        Book book = unf_tableView.getSelectionModel().getSelectedItem();
        int num = unf_tableView.getSelectionModel().getSelectedIndex();

        if (num - 1 < -1) {
            return;
        }

        clt_isbn.setText(String.valueOf(book.getIsbn()));
        clt_title.setText(String.valueOf(book.getTitle()));
        clt_author.setText(String.valueOf(book.getAuthor()));
        clt_issue.setText(String.valueOf(book.getIssueDay()));
        clt_due.setText(String.valueOf(book.getReturnDay()));
    }

    /////////////////////////////////////////////////////////////////////////////

    public ObservableList<Book> finBookListData() {
        ObservableList<Book> listData = FXCollections.observableArrayList();
        String sql = "select b.* from book b join userreturnbook rt " +
                "on b.isbn = rt.isbn";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Book book;
            while (result.next()) {
                book = new Book(result.getString("isbn"), result.getString("title"), result.getString("author"), result.getDate("issueDate"), result.getDate("returnDate"));
                listData.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<Book> finBookList;

    public void finBookShowListData(){
        finBookList = finBookListData();
        Integer n = 1;
        unf_col_number.setCellValueFactory(new PropertyValueFactory<>(n.toString()));
        n += 1;
        unf_col_name.setCellValueFactory(new PropertyValueFactory<>("title"));

        unf_tableView.setItems(unfBookList);
    }

    public void finBookSelect() {
        Book book = fin_tableView.getSelectionModel().getSelectedItem();
        int num = fin_tableView.getSelectionModel().getSelectedIndex();

        if (num - 1 < -1) {
            return;
        }

        clt_isbn.setText(String.valueOf(book.getIsbn()));
        clt_title.setText(String.valueOf(book.getTitle()));
        clt_author.setText(String.valueOf(book.getAuthor()));
        clt_issue.setText(String.valueOf(book.getIssueDay()));
        clt_due.setText(String.valueOf(book.getReturnDay()));
    }

    void initialize() {
        assert log12 != null : "fx:id=\"log12\" was not injected: check your FXML file 'MyCollections.fxml'.";

    }

    public void toHome(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
        super.changeSceneTo(home);
    }
}
