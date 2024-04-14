package org.example.els;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

    public class Main extends Application {

        @Override
        public void start(Stage primaryStage) {
            TextArea textArea = new TextArea();

            textArea.textProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("TextArea was changed!");
            });

            VBox vbox = new VBox(textArea);
            Scene scene = new Scene(vbox, 200, 100);
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

