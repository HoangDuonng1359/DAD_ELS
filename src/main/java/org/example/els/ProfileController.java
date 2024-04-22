package org.example.els;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ProfileController extends  baseFormController{
    @FXML
    private Label name_label;
    @FXML
    private ImageView avata_image;
    @Override
    public void initialize(){
        name_label.setText(user.getName());
        if(user.getImage()!=null){
            avata_image.setImage(user.getImage());
        }
    }
    @FXML
    public void changeName(ActionEvent event) throws IOException {
        SceneManage.showScene(root,stage,scene,event,"changeName.fxml");
    }
    @FXML
    public void changeAvata(ActionEvent event){

    }
    @FXML
    public void changePassword(ActionEvent event){

    }

}
