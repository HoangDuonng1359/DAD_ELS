package org.example.els;

import dictionary.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class addAndEditController extends baseFormController {
    @FXML
    private TextField e_Textfield;
    @FXML
    private TextField v_Textfield;
    @FXML
    private TextField noun_Textfield;
    @FXML
    private TextField verb_Textfield;
    @FXML
    private TextField adj_Textfield;
    @FXML
    private TextField adv_Textfield;
    @FXML
    private TextField prep_Textfield;
    @FXML
    private CheckBox check_Noun;
    @FXML
    private CheckBox check_Verb;
    @FXML
    private CheckBox check_Adj;
    @FXML
    private CheckBox check_Adv;
    @FXML
    private CheckBox check_Prep;
    public void addWord(ActionEvent event) {
        if(e_Textfield.getText()!=null&&v_Textfield.getText()!=null){
            StringBuilder target=new StringBuilder(e_Textfield.getText());
            StringBuilder explain=new StringBuilder(v_Textfield.getText());
            if(check_Noun.isSelected()){
                explain.append("\n Danh từ: "+noun_Textfield.getText());
            }
            if(check_Verb.isSelected()){
                explain.append("\n Động từ: "+verb_Textfield.getText());
            }
            if(check_Adj.isSelected()){
                explain.append("\n Tính từ: "+adj_Textfield.getText());
            }
            if(check_Adv.isSelected()){
                explain.append("\n Trạng từ: "+adv_Textfield.getText());
            }
            if(check_Prep.isSelected()){
                explain.append("\n Giới từ: "+prep_Textfield.getText());
            }
            DictionaryManagement a = new DictionaryManagement();
            a.insert(target.toString().trim().toLowerCase(),explain.toString());
        }


    }
}
