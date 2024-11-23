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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import APIManagement.BookManagement.Book;

public class ManageBook extends DefaultPanel{

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
    private TableColumn<Book, String> mngB_col_isbn;
    @FXML
    private TableColumn<Book, String> mngB_col_title;
    @FXML
    private TableColumn<Book, String> mngB_col_author;
    @FXML
    private TableColumn<Book, String> mngB_col_quantity;
    @FXML
    private TableView<Book> mngB_tableView;
    @FXML
    private TextField bookSearch_isbn;
    @FXML
    private TextField bookSearch_title;
    @FXML
    private TextField bookSearch_author;
    @FXML
    private TextField bookSearch_quantity;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void availableBooksAdd() {
        String sql = "INSERT INTO book (isbn, title, author, quantity) VALUES (?, ?, ?, ?)";

        connect = database.connectDb();
        try {
            Alert alert;
            if (bookSearch_author.getText().isEmpty()
                    || bookSearch_title.getText().isEmpty()
                    || bookSearch_quantity.getText().isEmpty()
                    || bookSearch_isbn.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid book information");
                alert.showAndWait();
            } else {
                //check if book id is already exist
                String checkData = "Select * from book where isbn = '"
                        + bookSearch_isbn.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Book already exists!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, bookSearch_isbn.getText());
                    prepare.setString(2, bookSearch_title.getText());
                    prepare.setString(3, bookSearch_author.getText());
                    prepare.setString(4, bookSearch_quantity.getText());

                    prepare.executeUpdate();
                    availableBooksShowListData();
                    availableBooksClear();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void availableBooksClear() {
        bookSearch_isbn.setText("");
        bookSearch_title.setText("");
        bookSearch_author.setText("");
        bookSearch_quantity.setText("");
    }

    public ObservableList<Book> avaiBookListData() {
        ObservableList<Book> listData = FXCollections.observableArrayList();
        String sql = "select * from Book";

        connect = database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Book book;

            while(result.next()) {
                book = new Book(result.getString("isbn"), result.getString("title"), result.getString("author"), result.getInt("quantity"));
                listData.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<Book> avaiBookList;

    public void availableBooksShowListData() {
        avaiBookList = avaiBookListData();

        mngB_col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        mngB_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        mngB_col_isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        mngB_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        mngB_tableView.setItems(avaiBookList);
    }

    public void availableBooksSelect() {
        Book book = mngB_tableView.getSelectionModel().getSelectedItem();
        int num = mngB_tableView.getSelectionModel().getSelectedIndex();

        if (num - 1 < -1) {
            return;
        }

        mngB_col_isbn.setText(String.valueOf(book.getIsbn()));
        mngB_col_title.setText(String.valueOf(book.getTitle()));
        mngB_col_author.setText(String.valueOf(book.getAuthor()));
        mngB_col_quantity.setText(String.valueOf(book.getQuantity()));
    }


    @FXML
    void initialize() {
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'ManageBook.fxml'.";
        assert log1 != null : "fx:id=\"log1\" was not injected: check your FXML file 'ManageBook.fxml'.";
        assert log11 != null : "fx:id=\"log11\" was not injected: check your FXML file 'ManageBook.fxml'.";
        assert log12 != null : "fx:id=\"log12\" was not injected: check your FXML file 'ManageBook.fxml'.";
        assert log13 != null : "fx:id=\"log11\" was not injected: check your FXML file 'ManageBook.fxml'.";

        availableBooksShowListData();
    }

    public void toHome(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
        super.changeSceneTo(home);
    }

}
