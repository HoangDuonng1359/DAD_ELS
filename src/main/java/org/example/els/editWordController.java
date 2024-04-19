package org.example.els;

import Bookmark.bookmarkmanagement;
import dictionary.DatabaseConnection;
import dictionary.DictionaryManagementDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class editWordController extends baseFormController{
    @FXML
    private TextField e_word;
    @FXML
    private HTMLEditor v_word;
    @FXML
    private Button commit;
    public void initialize() throws IOException, SQLException {
        super.initialize();
        commit.setVisible(false);
    }
    public void findWord(ActionEvent event) throws SQLException {
        String word = e_word.getText();
        if (word==null){
            newAlert(stage,"Find word","","Vui lòng nhập từ");
            return;
        }
        //String meaning= dictionaryManagement.Search(word);
        String meaning= DictionaryManagementDatabase.Search(word,true);
        if(meaning.equals("NO FOUND")){
            newAlert(stage,"Find word","","Không tìm thấy từ");
        }
        else {
            commit.setVisible(true);
            v_word.setHtmlText(meaning);
        }
    }

    public void editWord(ActionEvent event){
        //bookwriter.append("- "+s+"\n");
        bookmarkmanagement.editWord(e_word.getText(),v_word.getHtmlText(),user);
        e_word.setText("");
        v_word.setHtmlText("");
    }
}
