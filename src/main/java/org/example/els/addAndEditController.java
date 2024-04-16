package org.example.els;

import dictionary.DictionaryManagement;
import dictionary.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

import java.io.IOException;

public class addAndEditController extends baseFormController {
    @FXML
    private TextField e_Textfield;
    @FXML
    private HTMLEditor v_Textfield;
    @FXML
    private Label label;
    public void addWord(ActionEvent event) {
        try{
            String e = e_Textfield.getText();
            String v = v_Textfield.getHtmlText();
            if (!dictionaryManagement.Search(e).equals("NO FOUND")) {
                label.setText("Từ đã tồn tại");
            } else if (!e.isEmpty() && !v.isEmpty()) {
                dictionaryManagement.insert(e, v);
                bookwriter.append("+ "+e+" "+v+"\n");
                label.setText("Thêm từ thành công");
            } else {
                label.setText("Vui lòng nhập đầy đủ thông tin");
            }
        }
        catch (IOException e){
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
