package org.example.els;

import dictionary.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpController extends baseFormController {
    @Override
    public void initialize() {

    }

    @FXML
    private TextField name;
    @FXML
    private PasswordField password;
    final String DB = "jdbc:sqlite:src\\Data\\database.db";

    @FXML
    public void signup_submit(ActionEvent event) throws SQLException, IOException {
        Connection conn = DatabaseConnection.connect(DB);
        String sql2 = "SELECT name FROM user_table WHERE name = ?";
        PreparedStatement pt2 = conn.prepareStatement(sql2);
        pt2.setString(1, name.getText());
        ResultSet rs = pt2.executeQuery();
        if (rs.next()) {
            newAlert(stage, "Sign Up", "", "name already exists!");
        } else {
            String sql = "INSERT INTO user_table (name , password) VALUES (?,?)";
            PreparedStatement pt = conn.prepareStatement(sql);
            pt.setString(1, name.getText());
            pt.setString(2, password.getText());
            pt.executeUpdate();
            conn.close();
            newAlert(stage, "Sign Up", "", "Sign up success!");
            SceneManage.showScene(root, stage, scene, event, "signin.fxml");
        }
        conn.close();
    }
}
