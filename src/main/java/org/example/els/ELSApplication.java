package org.example.els;

import Game.FlashCard.RecentW;
import dictionary.DictionaryManagement;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;


public class ELSApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(ELSApplication.class.getResource("Home.fxml"));
        Scene scene = new Scene(root, 850, 600);
        RecentW.init();
        stage.setTitle("ELSApplication");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}