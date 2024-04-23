package org.example.els;

import Profile.user_management;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class changePasswordController extends baseFormController{
    @FXML
    private TextField Current_password;
    @FXML
    private TextField new_password;
    @FXML
    public void backProfile(ActionEvent event) throws IOException {
        SceneManage.showScene(root,stage,scene,event,"Profile.fxml");
    }
    private static user_management userManagement = new user_management(user);
    @FXML
    public void Change_password(ActionEvent event) throws SQLException, IOException {
        if(Current_password.getText().equals(user.getPass())){
            user = userManagement.Change_password(Current_password.getText(),new_password.getText());
            newAlert("Change password", "","Đổi mật khẩu thành công");
            backProfile(event);
        }
        else{
            newAlert("Change password", "","Sai mật khẩu");
        }
    }
}
