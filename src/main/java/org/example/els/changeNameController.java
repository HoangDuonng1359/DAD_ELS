package org.example.els;

import Profile.user_management;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class changeNameController extends baseFormController{
    @FXML
    private TextField newName;
    @FXML
    private TextField password;
    private static user_management userManagement = new user_management(user);
    @FXML
    public void changeName(ActionEvent event) throws SQLException, IOException {
        if(password.getText().equals(user.getPass())){
            user = userManagement.changeName(newName.getText());
            newAlert("Change Name", "","Đổi tên thành công");
            backProfile(event);
        }
        else{
            newAlert("Change Name", "","Sai mật khẩu");
        }
    }
    @FXML
    public void backProfile(ActionEvent event) throws IOException {
        SceneManage.showScene(root,stage,scene,event,"Profile.fxml");
    }
}
