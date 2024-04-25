package org.example.els;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManage {
    public static void showScene(Parent root, Stage stage, Scene scene, ActionEvent event, String file_fxml_source) throws IOException {
        root = FXMLLoader.load(SceneManage.class.getResource(file_fxml_source));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ELSApplication");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    public static void newAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
