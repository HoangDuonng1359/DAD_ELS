package org.example.els;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import javafx.scene.web.WebView;

import java.io.IOException;

public class ELSApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ELSApplication.class.getResource("ELS-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 718, 478);
        stage.setTitle("ELSApplication");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}