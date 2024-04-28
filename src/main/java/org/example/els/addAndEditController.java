package org.example.els;

import Bookmark.bookmarkmanagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.HTMLEditor;

public class addAndEditController extends baseFormController {
    @FXML
    private TextField e_Textfield;
    @FXML
    private HTMLEditor v_Textfield;
    @FXML
    private Label label;
    @FXML
    private Label label_target;
    @FXML
    private Label label_explain;
    @FXML
    public void initialize() {
        av.setSelected(true);
        va.setSelected(false);
    }
    @Override
    public void setmodeav(ActionEvent event){
        if(av.isSelected()){
            va.setSelected(false);
            label_target.setText("English");
            label_explain.setText("Vietnamese");
        }
        else {
            va.setSelected(true);
            label_target.setText("Tiếng việt");
            label_explain.setText("Tiếng anh");
        }
    }
    @Override
    public void setmodeva(ActionEvent event){
        if(va.isSelected()){
            av.setSelected(false);
            label_target.setText("Tiếng việt");
            label_explain.setText("Tiếng anh");
        }
        else {
            av.setSelected(true);
            label_target.setText("English");
            label_explain.setText("Vietnamese");
        }
    }
    public void addWord(ActionEvent event) {
       bookmarkmanagement.addWord(e_Textfield.getText(),v_Textfield.getHtmlText(),user,getmode(av,va));
    }
}
