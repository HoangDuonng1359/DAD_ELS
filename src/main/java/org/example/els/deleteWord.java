package org.example.els;

import dictionary.DatabaseConnection;
import dictionary.DictionaryManagementDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteWord extends baseFormController{
    @FXML
    private TextField word;
    public void deleteWord(ActionEvent event) {

        try {
            String s= word.getText();
            if(s==null){
                newAlert(stage,"Delete Word","","Vui lòng nhập từ");
            }
            //else if(dictionaryManagement.Search(s).equals("NO FOUND")){
            else if(DictionaryManagementDatabase.Search(s,true).equals("NO FOUND")){
                newAlert(stage,"Delete Word","","Không tìm thấy từ");
            }
            else {
                //dictionaryManagement.remove(s);
                newAlert(stage,"Delete Word","","Xóa từ thành công");
                //bookwriter.append("- "+s+"\n");
                Connection conn = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
                String sql = "INSERT INTO bookmark (user_id,target,type) VALUES (?,?,?)";
                PreparedStatement pr = conn.prepareStatement(sql);
                pr.setInt(1,user.getId());
                pr.setString(2,s);
                pr.setString(3,"-");
                pr.executeUpdate();
                conn.close();
                word.setText("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
