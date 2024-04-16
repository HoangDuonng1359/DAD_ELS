package org.example.els;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class deleteWord extends baseFormController{
    @FXML
    private TextField word;
    public void deleteWord(ActionEvent event) {

        try {
            String s= word.getText();
            if(s==null){
                newAlert(stage,"Delete Word","","Vui lòng nhập từ");
            }
            else if(dictionaryManagement.Search(s).equals("NO FOUND")){
                newAlert(stage,"Delete Word","","Không tìm thấy từ");
            }
            else {
                dictionaryManagement.remove(s);
                newAlert(stage,"Delete Word","","Xóa từ thành công");
                bookwriter.append("- "+s+"\n");
                word.setText("");
            }
        } catch (IOException e) {
            System.out.println("IOException in deleteWord: " + e.getMessage());
        }
    }
}
