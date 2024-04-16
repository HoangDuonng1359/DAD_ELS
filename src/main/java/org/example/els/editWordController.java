package org.example.els;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

import java.io.IOException;

public class editWordController extends baseFormController{
    @FXML
    private TextField e_word;
    @FXML
    private HTMLEditor v_word;
    @FXML
    private Button commit;
    public void initialize() throws IOException {
        super.initialize();
        commit.setVisible(false);
    }
    public void findWord(ActionEvent event) {
        String word = e_word.getText();
        if (word==null){
            newAlert(stage,"Find word","","Vui lòng nhập từ");
            return;
        }
        String meaning= dictionaryManagement.Search(word);
        if(meaning.equals("NO FOUND")){
            newAlert(stage,"Find word","","Không tìm thấy từ");
        }
        else {
            commit.setVisible(true);
            v_word.setHtmlText(meaning);
        }
    }
    public void editWord(ActionEvent event){
        try {
            dictionaryManagement.setExplain(e_word.getText(),v_word.getHtmlText());
            bookwriter.append("# "+e_word.getText()+" "+v_word.getHtmlText()+"\n");
            newAlert(stage,"Edit word","","Đã sửa thành công");
            e_word.setText("");
            v_word.setHtmlText("");
        } catch (IOException e) {
            System.out.println("IOException in editword: " + e.getMessage());
        }
    }
}
