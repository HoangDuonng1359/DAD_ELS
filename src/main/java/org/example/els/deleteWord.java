package org.example.els;

import Bookmark.customDictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.sql.SQLException;

public class deleteWord extends baseFormController {
    @FXML
    private TextField word;
    @FXML
    private Label label_target;
    @FXML
    private Button ava_button;

    public void initialize() throws IOException, SQLException {
        av.setSelected(true);
        va.setSelected(false);
        SceneManage.setAvatar(ava_button, user.getAvata());
    }

    @Override
    public void setmodeav(ActionEvent event) {
        if (av.isSelected()) {
            va.setSelected(false);
            label_target.setText("English");
        } else {
            va.setSelected(true);
            label_target.setText("Tiếng việt");
        }
    }

    @Override
    public void setmodeva(ActionEvent event) {
        if (va.isSelected()) {
            av.setSelected(false);
            label_target.setText("Tiếng việt");
        } else {
            av.setSelected(true);
            label_target.setText("English");
        }
    }

    @FXML
    public void key_delete_enter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            customDictionary.deleteWord(word.getText(), getmode(av, va));
            word.setText("");
        }
    }
    @FXML
    public void deleteWord(ActionEvent event) {
        customDictionary.deleteWord(word.getText(), getmode(av, va));
        word.setText("");
    }
}
