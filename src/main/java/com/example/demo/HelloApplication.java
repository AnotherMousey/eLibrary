package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static com.example.demo.BookAPI.getBookInfo;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, ParseException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        Query query = new Query();
        query.setInTitle("flowers");
        query.setInAuthor("keyes");
        getBookInfo(query);
    }

    public static void main(String[] args) {
        launch();
    }
}