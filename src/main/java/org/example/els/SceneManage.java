package org.example.els;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManage {
    /**
     * open scene google translate and close current scene
     * @param stage
     * @param scene
     * @param fxmlLoader
     * @param event
     * @param file_fxml_source
     * @throws IOException
     */
    public static void showScene(Stage stage, Scene scene , FXMLLoader fxmlLoader, ActionEvent event, String file_fxml_source) throws IOException {
        if (stage==null){
            stage = new Stage();
        }
        fxmlLoader = new FXMLLoader(ELSApplication.class.getResource(file_fxml_source));
        scene = new Scene(fxmlLoader.load(), 850, 600);
        stage.setTitle("ELSApplication");
        stage.setScene(scene);
        stage.show();
        //close current scene
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
