package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;

import SQLManagement.SQL;
import javafx.application.Application;
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
import javafx.stage.Stage;
import libUser.libraryUser;

public class ManageStudent extends DefaultPanel {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button log1;

    @FXML
    private Button log11;

    @FXML
    private Button log12;

    @FXML
    private Button log13;
    @FXML
    private TableColumn<libraryUser,Integer> mngU_col_uid;
    @FXML
    private TableColumn<libraryUser, String> mngU_col_name;
    @FXML
    private TableColumn<libraryUser, String> mngU_col_username;
    @FXML
    private TableColumn<libraryUser, String> mngU_col_email;
    @FXML
    private TableView<libraryUser> mngU_tableView;
    @FXML
    private TextField mngU_search;
    @FXML
    private TextField mngU_uid;
    @FXML
    private TextField mngU_name;
    @FXML
    private TextField mngU_username;
    @FXML
    private TextField mngU_email;
    @FXML
    private TextField mngU_password;
    @FXML
    private TextField mngU_prior;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void userAdd() {
        String sql = "INSERT INTO user(uid, username, password, priority, name, email) value(?, ?, ?, ?, ?, ?)";

        Statement stmt = SQL.getStmt();

        try {
            Alert alert;
            if (mngU_uid.getText().isEmpty()
                || mngU_username.getText().isEmpty()
                || mngU_password.getText().isEmpty()
                || mngU_email.getText().isEmpty()
                || mngU_prior.getText().isEmpty()
                || mngU_name.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid user information");
                alert.showAndWait();
            } else {
                // check user if already exist
                String checkData = "select * from user where uid = '"+ mngU_uid.getText() +"'";
                result = stmt.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("User already exists");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, mngU_uid.getText());
                    prepare.setString(5, mngU_name.getText());
                    prepare.setString(2, mngU_username.getText());
                    prepare.setString(6, mngU_email.getText());
                    prepare.setString(3, mngU_password.getText());
                    prepare.setString(4, mngU_prior.getText());

                    prepare.execute();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("System notification");
                    alert.setHeaderText(null);
                    alert.setContentText("Added successfully!");
                    alert.showAndWait();

                    userShowListData();
                    clearSearch();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearSearch() {
        mngU_uid.setText("");
        mngU_name.setText("");
        mngU_username.setText("");
        mngU_email.setText("");
        mngU_password.setText("");
        mngU_prior.setText("");
    }

    public void userDelete() throws SQLException {

        String sql = "DELETE FROM user WHERE uid = '"
                + mngU_uid.getText()+"'";

        Statement stmt = SQL.getStmt();

        Alert alert;

        if (mngU_uid.getText().isEmpty()
                || mngU_name.getText().isEmpty()
                || mngU_username.getText().isEmpty()
                || mngU_email.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid user information");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("System notification");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this account?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)) {
                stmt.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("System notification");
                alert.setHeaderText(null);
                alert.setContentText("Deleted successfully!");
                alert.showAndWait();

                userShowListData();
                clearSearch();

            }
        }

    }

    public ObservableList<libraryUser> userListData() throws SQLException {
        ObservableList<libraryUser> listData = FXCollections.observableArrayList();
        String sql = "select * from user";
        Statement stmt = SQL.getStmt();

        result = stmt.executeQuery(sql);
        libraryUser libUser;
        while (result.next()) {
            if(result.getInt("priority") == 2) continue;
            libUser = new libraryUser(result.getString("name"), result.getString("email"), result.getString("username"), result.getString("password"), result.getInt("uid"), result.getInt("priority"));
            //libUser = new libraryUser(result.getString("name"), result.getString("email"), result.getString("username"), result.getInt("uid"));
            listData.add(libUser);
        }
        return listData;
    }

    private ObservableList<libraryUser> userList;

    public void userShowListData() throws SQLException {

        userList = userListData();

        mngU_col_uid.setCellValueFactory(new PropertyValueFactory<>("uid"));
        mngU_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        mngU_col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        mngU_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));

        mngU_tableView.setItems(userList);
    }

public void userSelect() {
        libraryUser libUser = mngU_tableView.getSelectionModel().getSelectedItem();
        int num = mngU_tableView.getSelectionModel().getSelectedIndex();

        if (num - 1 < - 1) {
            return;
        }

        mngU_name.setText(libUser.getName());
        mngU_username.setText(libUser.getUsername());
        mngU_email.setText(libUser.getEmail());
        mngU_uid.setText(String.valueOf(libUser.getUid()));
}

public void userSearch() {
        FilteredList<libraryUser> filter = new FilteredList<>(userList, e -> true);
        mngU_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateUser -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateUser.getName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateUser.getEmail().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateUser.getUsername().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (String.valueOf(predicateUser.getUid()).toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

    SortedList<libraryUser> sortedList = new SortedList<>(filter);
    sortedList.comparatorProperty().bind(mngU_tableView.comparatorProperty());
    mngU_tableView.setItems(sortedList);
}

    @FXML
    void initialize() throws SQLException {
        assert log1 != null : "fx:id=\"log1\" was not injected: check your FXML file 'ManageStudent.fxml'.";
        assert log11 != null : "fx:id=\"log11\" was not injected: check your FXML file 'ManageStudent.fxml'.";
        assert log12 != null : "fx:id=\"log12\" was not injected: check your FXML file 'ManageStudent.fxml'.";
        assert log13 !=  null : "fx:id=\"log13\" was not injected: check your FXML file 'ManageStudent.fxml'.";

        userShowListData();
        userSearch();
    }

    public void toHome(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
        super.changeSceneTo(home);
    }
}
