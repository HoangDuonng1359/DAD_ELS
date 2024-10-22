package org.example.els;

import dictionary.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class gameController extends baseFormController{
    protected static Stage stage;
    protected static Scene scene;
    protected static Parent root;
    @FXML
    public void openFormMultichoice(ActionEvent event){
        try {
            SceneManage.showScene(root,stage,scene,event,"multiChoiceGame.fxml");
        } catch (IOException e) {
            System.out.println("lỗi không mở được form");
        }
    }
    @FXML
    public void openFormFlashCard(ActionEvent event) throws SQLException {
        Connection conn = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
        String sql = "SELECT COUNT(*) FROM RecentList WHERE user_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,user.getId());
        ResultSet rs = ps.executeQuery();
        if(rs.getInt(1) == 0){
            System.out.println();
            newAlert(stage,"information","","NO WORD");
        }
        else {
            try {
                SceneManage.showScene(root,stage,scene,event,"FlashCard.fxml");
            } catch (IOException e) {
                System.out.println("lỗi không mở được form" + e.getMessage());
            }
        }
        conn.close();
    }
    @FXML
    public void openFormPGG(ActionEvent event){
        try {
            SceneManage.showScene(root,stage,scene,event,"PictureGuessingGame.fxml");
        } catch (IOException e) {
            System.out.println("lỗi không mở được form");
        }
    }
}
