package org.example.els;

import dictionary.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import user.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SigninController extends baseFormController {
    @FXML
    public void initialize() {

    }

    @FXML
    private TextField name;
    @FXML
    private PasswordField password;
    final String DB = "jdbc:sqlite:src\\Data\\database.db";

    @FXML
    private void sign_submit(ActionEvent event) throws SQLException {
        Connection conn = DatabaseConnection.connect(DB);
        String sql = "SELECT id , name ,password FROM user_table WHERE name = ? AND password = ?";
        PreparedStatement pt = conn.prepareStatement(sql);
        pt.setString(1, name.getText());
        pt.setString(2, password.getText());
        ResultSet rs = pt.executeQuery();
        if (rs.next()) {
            newAlert(stage, "Login", "", "Logged in successfully");
            user = new User(rs.getInt("id"), rs.getString("password"), rs.getString("name"));
            openFormHome(event);
        } else {
            newAlert(stage, "Login", "", "Logged in failed");
        }
        conn.close();
    }

    @FXML
    private void openFormSignUp(ActionEvent event) throws IOException {
        SceneManage.showScene(root, stage, scene, event, "signup.fxml");
    }
}