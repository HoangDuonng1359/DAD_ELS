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
import javafx.scene.image.Image;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import user.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class ELSApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        Parent root = FXMLLoader.load(ELSApplication.class.getResource("signin.fxml"));
        Scene scene = new Scene(root);
        //RecentW.init();
        //RecentW.initDB();
        stage.setTitle("ELSApplication");
        stage.setScene(scene);
        stage.getIcons().add(new Image(ELSApplication.class.getResourceAsStream("/org/example/els/image/APP LOGO.png")));
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
               // RecentW.closefile();
                //baseFormController.closefile();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}