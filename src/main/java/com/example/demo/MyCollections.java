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
        // ma oi code 1 dong xong doi branch no ko luu cho h phai di code lai
        /**
         * phai trace ra dc user dang dung la ai, lay duoc uid
         * tu uid do, anh xa vao user borrow/return book de lay isbn cua sach
         * roi join voi database cua sach de lay ra info cua sach
         */
        CurrentUser curUser = new CurrentUser();
        String res = String.valueOf(curUser.currentUser.getUid());
        String sql = "select b.* from book b join userreturnbook rt " +
                "on b.isbn = rt.isbn and rt.uid = '" + res + "'";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Book book;
            while (result.next()) {
                book = new Book(result.getString("b.isbn"), result.getString("b.title"), result.getString("b.author"), result.getDate("b.issueDate"), result.getDate("b.returnDate"));
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
        clt_issue.setText(String.valueOf(book.getIssueDate()));
        clt_due.setText(String.valueOf(book.getReturnDate()));
    }

    /////////////////////////////////////////////////////////////////////////////

    public ObservableList<Book> finBookListData() {
        ObservableList<Book> listData = FXCollections.observableArrayList();

        CurrentUser curUser = new CurrentUser();
        String res = String.valueOf(curUser.currentUser.getUid());
        String sql = "select b.* from book b join userreturnbook rt " +
                "on b.isbn = rt.isbn and rt.uid = '" + res + "'";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Book book;
            while (result.next()) {
                book = new Book(result.getString("b.isbn"), result.getString("b.title"), result.getString("b.author"), result.getDate("b.issueDate"), result.getDate("b.returnDate"));
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
        fin_col_name.setCellValueFactory(new PropertyValueFactory<>("title"));
        fin_tableView.setItems(finBookList);
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
        clt_issue.setText(String.valueOf(book.getIssueDate()));
        clt_due.setText(String.valueOf(book.getReturnDate()));
    }

    @FXML
    void initialize() {
        assert log12 != null : "fx:id=\"log12\" was not injected: check your FXML file 'MyCollections.fxml'.";

        unfBookShowListData();
        finBookShowListData();
    }

    public void toHome(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
        super.changeSceneTo(home);
    }
}
