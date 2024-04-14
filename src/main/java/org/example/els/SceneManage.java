package org.example.els;

import dictionary.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManage {
    /**
     * open scene google translate and close current scene
     * @param root
     * @param stage
     * @param scene
     * @param event
     * @param file_fxml_source
     * @throws IOException
     */
    public static void showScene(Parent root,Stage stage, Scene scene, ActionEvent event, String file_fxml_source) throws IOException {
//        if (stage==null){
//            stage = new Stage();
//        }
//        fxmlLoader = new FXMLLoader(ELSApplication.class.getResource(file_fxml_source));
//        scene = new Scene(fxmlLoader.load(), 850, 600);
//        stage.setTitle("ELSApplication");
//        stage.setScene(scene);
//        stage.show();
//        //close current scene
//        ((Node)(event.getSource())).getScene().getWindow().hide();
        root = FXMLLoader.load(SceneManage.class.getResource(file_fxml_source));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ELSApplication");
        stage.setScene(scene);
        stage.show();
    }
    public static void changeButtonCssFile(Button button){
        button.getStylesheets().add("../resources/org/example/els/css/v2/button.css");
    }
}
