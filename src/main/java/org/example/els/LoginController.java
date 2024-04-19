package org.example.els;

import dictionary.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController extends baseFormController {
    @FXML
    public void initialize(){

    }
    @FXML
    private TextField name;
    @FXML
    private TextField password;
    final String DB = "jdbc:sqlite:src\\Data\\database.db";
    @FXML
    private void login_submit(ActionEvent event) throws SQLException {
        Connection conn = DatabaseConnection.connect(DB);
        String sql = "SELECT name,password FROM user_table WHERE name = ?";
        PreparedStatement pt = conn.prepareStatement(sql);
        pt.setString(1,name.getText());
        ResultSet rs = pt.executeQuery();
        if(rs.next()){
            if(rs.getString("password").equals(password.getText()))
            {
                newAlert(stage,"Login","","Logged in successfully");
                openFormHome(event);
            }
        }
        else{
            newAlert(stage,"Login","","Logged in failed");
        }
        conn.close();
    }
    @FXML
    private void openFormSignUp(ActionEvent event) throws IOException {
        SceneManage.showScene(root,stage,scene,event,"signup.fxml");
    }
}
