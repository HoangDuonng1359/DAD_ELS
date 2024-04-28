package org.example.els;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;


public class ELSApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signin.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        //RecentW.init();

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