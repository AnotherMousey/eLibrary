package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import APIManagement.BookManagement.BookForBorrow;
import SQLManagement.ResultSetToList;
import SQLManagement.SQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import APIManagement.BookManagement.Book;
import libUser.CurrentUser;

public class MyCollections extends DefaultPanel {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button log12;

    @FXML
    private TableColumn<BookForBorrow, Integer> unf_col_number;
    @FXML
    private TableColumn<BookForBorrow, Integer> fin_col_number;
    @FXML
    private TableColumn<BookForBorrow, String> unf_col_name;
    @FXML
    private TableColumn<BookForBorrow, String> fin_col_name;
    @FXML
    private TableView<BookForBorrow> unf_tableView;
    @FXML
    private TableView<BookForBorrow> fin_tableView;
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

    private ResultSet result;

    public ObservableList<BookForBorrow> unfBookListData() throws SQLException {
        ObservableList<BookForBorrow> listData = FXCollections.observableArrayList();
        // ma oi code 1 dong xong doi branch no ko luu cho h phai di code lai
        /**
         * phai trace ra dc user dang dung la ai, lay duoc uid
         * tu uid do, anh xa vao user borrow/return book de lay isbn cua sach
         * roi join voi database cua sach de lay ra info cua sach
         */
        String sql = "SELECT * FROM userborrowbook " +
                "WHERE uid = " + CurrentUser.currentUser.getUid() + ";";

        Statement stmt = SQL.getStmt();
        result = stmt.executeQuery(sql);
        List<HashMap<String,Object>> res = ResultSetToList.convertResultSetToList(result);

        for (HashMap<String,Object> borrowBook : res) {
            BookForBorrow book = new BookForBorrow();
            book.setIsbn(borrowBook.get("isbn").toString());
            book.setBorrowedDate((Timestamp) borrowBook.get("borrowTime"));

            String smallQuery = "SELECT * FROM book WHERE isbn = '" + book.getIsbn() + "';";
            Statement stmt2 = SQL.getStmt();
            ResultSet rs = stmt2.executeQuery(smallQuery);
            List<HashMap<String,Object>> curBook = ResultSetToList.convertResultSetToList(rs);
            book.setTitle(curBook.get(0).get("title").toString());
            book.setAuthor(curBook.get(0).get("author").toString());
            listData.add(book);
        }
        return listData;
    }

    public void unfBookShowListData() throws SQLException {
        ObservableList<BookForBorrow> unfBookList = unfBookListData();
        unf_col_name.setCellValueFactory(new PropertyValueFactory<>("title"));
        unf_tableView.setItems(unfBookList);
    }

    public void unfBookSelect() {
        BookForBorrow book = unf_tableView.getSelectionModel().getSelectedItem();
        int num = unf_tableView.getSelectionModel().getSelectedIndex();

        if (num - 1 < -1) {
            return;
        }

        clt_isbn.setText(String.valueOf(book.getIsbn()));
        clt_title.setText(String.valueOf(book.getTitle()));
        clt_author.setText(String.valueOf(book.getAuthor()));
        clt_issue.setText(String.valueOf(book.getBorrowedDate()));
        clt_due.setText(String.valueOf(book.getReturnedDate()));
    }

    /////////////////////////////////////////////////////////////////////////////

    public ObservableList<BookForBorrow> finBookListData() throws SQLException {
        ObservableList<BookForBorrow> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM userreturnbook " +
                "WHERE uid = " + CurrentUser.currentUser.getUid() + ";";

        Statement stmt = SQL.getStmt();
        result = stmt.executeQuery(sql);
        List<HashMap<String,Object>> res = ResultSetToList.convertResultSetToList(result);

        for (HashMap<String,Object> borrowBook : res) {
            BookForBorrow book = new BookForBorrow();
            book.setIsbn(borrowBook.get("isbn").toString());
            book.setBorrowedDate((Timestamp) borrowBook.get("borrowTime"));
            book.setReturnedDate((Timestamp) borrowBook.get("returnTime"));

            String smallQuery = "SELECT * FROM book WHERE isbn = '" + book.getIsbn() + "';";
            Statement stmt2 = SQL.getStmt();
            ResultSet rs = stmt2.executeQuery(smallQuery);
            List<HashMap<String,Object>> curBook = ResultSetToList.convertResultSetToList(rs);
            book.setTitle(curBook.get(0).get("title").toString());
            book.setAuthor(curBook.get(0).get("author").toString());
            listData.add(book);
        }
        return listData;
    }

    public void finBookShowListData() throws SQLException {
        ObservableList<BookForBorrow> finBookList = finBookListData();
        fin_col_name.setCellValueFactory(new PropertyValueFactory<>("title"));
        fin_tableView.setItems(finBookList);
    }

    public void finBookSelect() {
        BookForBorrow book = fin_tableView.getSelectionModel().getSelectedItem();
        int num = fin_tableView.getSelectionModel().getSelectedIndex();

        if (num - 1 < -1) {
            return;
        }

        clt_isbn.setText(String.valueOf(book.getIsbn()));
        clt_title.setText(String.valueOf(book.getTitle()));
        clt_author.setText(String.valueOf(book.getAuthor()));
        clt_issue.setText(String.valueOf(book.getBorrowedDate()));
        clt_due.setText(String.valueOf(book.getReturnedDate()));
    }

    @FXML
    void initialize() throws SQLException {
        assert log12 != null : "fx:id=\"log12\" was not injected: check your FXML file 'MyCollections.fxml'.";

        unfBookShowListData();
        finBookShowListData();
    }

    public void toHome(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
        super.changeSceneTo(home);
    }
}
