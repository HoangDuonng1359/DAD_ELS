package org.example.els;

import Bookmark.bookmarkmanagement;
import dictionary.DatabaseConnection;
import dictionary.DictionaryManagementDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

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
    @FXML
    private Label label_target;
    @FXML
    private Label label_explain;

    public void initialize() throws IOException, SQLException {
        commit.setVisible(false);
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
    public void findWord(ActionEvent event) throws SQLException {
        String word = e_word.getText();
        if (word==null){
            newAlert(stage,"Find word","","Vui lòng nhập từ");
            return;
        }
        //String meaning= dictionaryManagement.Search(word);
        String meaning= DictionaryManagementDatabase.Search(word,getmode(av,va));
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
        bookmarkmanagement.editWord(e_word.getText(),v_word.getHtmlText(),user,getmode(av,va));
        e_word.setText("");
        v_word.setHtmlText("");
    }
}
