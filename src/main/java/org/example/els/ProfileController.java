package org.example.els;

import Profile.user_management;
import dictionary.DictionaryManagementDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ProfileController extends baseFormController {
    @FXML
    private Label name_label;
    @FXML
    private ImageView avata_image;
    @FXML
    private Label numberEdit;
    @FXML
    private Label numberAdd;
    @FXML
    private Label numberRemove;

    @Override
    public void initialize() throws SQLException {
        name_label.setText(user.getName());
        if (user.getImage() != null) {
            avata_image.setImage(user.getImage());
        }
        numberEdit.setText("Word added:" + DictionaryManagementDatabase.numberChange("#") + "words");
        numberAdd.setText("Word edited:" + DictionaryManagementDatabase.numberChange("+") + " words");
        numberRemove.setText("Word removed:" + DictionaryManagementDatabase.numberChange("-") + " words");
        if(user.getAvata()!=null){
            avata_image.setImage(user.getImage());
        }
    }

    @FXML
    public void changeName(ActionEvent event) throws IOException {
        SceneManage.showScene(root, stage, scene, event, "changeName.fxml");
    }

    @FXML
    public void changeAvata(ActionEvent event) throws SQLException {
        Image image = user_management.changeAvt(stage);
        avata_image.setImage(image);
        user.setAvata(image);
    }

    @FXML
    public void changePassword(ActionEvent event) throws IOException {
        SceneManage.showScene(root, stage, scene, event, "changePassword.fxml");
    }


}
