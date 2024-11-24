package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    @FXML
    private TextField avaiBookSearch;

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

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("System notification");
                    alert.setHeaderText(null);
                    alert.setContentText("Added successfully!");
                    alert.showAndWait();

                    availableBooksShowListData();
                    availableBooksClear();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void availableBooksUpdate() {
        String sql = "UPDATE book SET title = '" + bookSearch_title.getText()
                + "', author = '" + bookSearch_author.getText()
                + "', quantity = '" + bookSearch_quantity.getText()
                + "' WHERE isbn = '" + bookSearch_isbn.getText() + "'";

        connect = database.connectDb();
        try  {
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

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Double-check");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update this book?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("System notification");
                    alert.setHeaderText(null);
                    alert.setContentText("Updated successfully!");
                    alert.showAndWait();

                    availableBooksShowListData();
                    availableBooksClear();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void availableBooksDelete(){

        String sql = "DELETE FROM book WHERE isbn = '"
                + bookSearch_isbn.getText()+"'";

        connect = database.connectDb();

        try{
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
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("System notification");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete this book?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("System notification");
                    alert.setHeaderText(null);
                    alert.setContentText("Deleted successfully!");
                    alert.showAndWait();

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

        bookSearch_isbn.setText(String.valueOf(book.getIsbn()));
        bookSearch_title.setText(String.valueOf(book.getTitle()));
        bookSearch_author.setText(String.valueOf(book.getAuthor()));
        bookSearch_quantity.setText(String.valueOf(book.getQuantity()));
    }

    public void availableBooksSearch() {

        FilteredList<Book> filter = new FilteredList<>(avaiBookList, e -> true);
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
        sortedList.comparatorProperty().bind(mngB_tableView.comparatorProperty());
        mngB_tableView.setItems(sortedList);
    }


    @FXML
    void initialize() {
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'ManageBook.fxml'.";
        assert log1 != null : "fx:id=\"log1\" was not injected: check your FXML file 'ManageBook.fxml'.";
        assert log11 != null : "fx:id=\"log11\" was not injected: check your FXML file 'ManageBook.fxml'.";
        assert log12 != null : "fx:id=\"log12\" was not injected: check your FXML file 'ManageBook.fxml'.";
        assert log13 != null : "fx:id=\"log11\" was not injected: check your FXML file 'ManageBook.fxml'.";

        availableBooksShowListData();
        availableBooksSearch();
    }

    public void toHome(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
        super.changeSceneTo(home);
    }

}
