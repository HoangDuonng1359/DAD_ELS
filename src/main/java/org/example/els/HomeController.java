package org.example.els;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController extends baseFormController{

    @FXML
    private Button ava_button;

    @FXML
    public void initialize(){
        SceneManage.setAvatar(ava_button,user.getAvata());
    }

}
