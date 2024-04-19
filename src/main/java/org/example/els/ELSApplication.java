package org.example.els;

import Game.FlashCard.RecentW;
import dictionary.DictionaryManagement;
import dictionary.DictionaryManagementDatabase;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.Connection;


public class ELSApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(ELSApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(root);
        RecentW.init();
        stage.setTitle("ELSApplication");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                RecentW.closefile();
                baseFormController.closefile();
            }
        });

    }

    public static void main(String[] args) {
        launch();
    }
}