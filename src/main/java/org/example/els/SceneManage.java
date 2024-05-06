package org.example.els;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
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
    public static void setAvatar(Button button, Image image) {
        if(image!=null) {
            button.getStylesheets().clear();
            double width =button.getPrefWidth();
            double height = button.getPrefHeight();
            double centerX = width / 2;
            double centerY = height / 2;
            double radius = Math.min(centerX, centerY);
            Circle clip = new Circle(centerX, centerY, radius);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            imageView.setClip(clip);
            button.setGraphic(imageView);
           // button.setStyle("-fx-background-image: url(../resources/org/example/els/css/avatar_button.css)");
            button.getStylesheets().add(SceneManage.class.getResource("/org/example/els/css/avatar_button.css").toExternalForm());
        }
    }
    public static void newErrorAlert(String title, String headerText , String ContentText){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(ContentText);
        alert.showAndWait();
    }
}
