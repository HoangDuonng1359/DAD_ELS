package Game.PictureGuessingGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.List;

public class test extends Application {

    // Đường dẫn đến cơ sở dữ liệu SQLite
    static final String DB_URL = "jdbc:sqlite:database.db";

    public static void main(String[] args) {
        database.insertToDatabase("src/main/resources/org/example/els/image/1.png","hello",DB_URL);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        List<Image> images = database.getAllImage(DB_URL);
        for(Image image : images){
            imageView.setImage(image);
        }
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
