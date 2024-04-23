package org.example.els;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class moreController extends baseFormController{
    @FXML
    public void openFormProfile(ActionEvent event) throws IOException {
        SceneManage.showScene(root,stage,scene,event,"Profile.fxml");
    }
}
