package org.example.els;

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
        try {
            //bookwriter.append("- "+s+"\n");
            Connection conn = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
            String sql = "INSERT INTO bookmark (user_id,target,explain,type) VALUES (?,?,?,?)";
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1,user.getId());
            pr.setString(2,e_word.getText());
            pr.setString(3,v_word.getHtmlText());
            pr.setString(4,"#");
            pr.executeUpdate();
            conn.close();
            //dictionaryManagement.setExplain(e_word.getText(),v_word.getHtmlText());
            //bookwriter.append("# "+e_word.getText()+" "+v_word.getHtmlText()+"\n");
            newAlert(stage,"Edit word","","Đã sửa thành công");
            e_word.setText("");
            v_word.setHtmlText("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
