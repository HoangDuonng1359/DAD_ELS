package org.example.els;

import dictionary.DictionaryManagement;
import dictionary.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
                newAlert(stage,"AddWord","","Từ đã tồn tại");
            } else if (!e.isEmpty() && !v.isEmpty()) {
                dictionaryManagement.insert(e, v);
                bookwriter.append("+ "+e+" "+v+"\n");
                newAlert(stage,"AddWord","","Thêm từ thành công");
                e_Textfield.setText("");
                v_Textfield.setHtmlText("");
            } else {
                newAlert(stage,"AddWord","","Vui lòng nhập đủ thông tin từ");
            }
        }
        catch (IOException e){
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
