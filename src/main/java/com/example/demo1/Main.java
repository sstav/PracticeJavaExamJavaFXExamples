package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private void CardGame(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 499, 353);
        stage.setScene(scene);
        stage.show();
    }

    private void MaxToTen(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MaxToTen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.setTitle("Game");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        CardGame(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}