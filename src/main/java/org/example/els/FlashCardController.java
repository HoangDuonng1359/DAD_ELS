package org.example.els;

import Bookmark.RecentW;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;
import java.sql.SQLException;

public class FlashCardController extends baseFormController{
    @FXML
    private Label E_word;
    @FXML
    private WebView V_word;
    @FXML
    private Button ava_button;

    //static DictionaryManagement a= new DictionaryManagement();

    @Override
    public void initialize() throws SQLException {
        RecentW.initDB();
        initGame();
        SceneManage.setAvatar(ava_button, user.getAvata());
    }
    @FXML
    public void initGame() throws SQLException {
        String target= RecentW.getWord();
        //System.out.println(target);
        E_word.setText(target);
        V_word.getEngine().loadContent(RecentW.getExplain(target));
        //V_word.getEngine().loadContent(DictionaryManagementDatabase.Search(target,true));
        E_word.setVisible(true);
        V_word.setVisible(false);
    }
    @FXML
    public void handleMouseClickLabel(ActionEvent event){
        if(E_word.isVisible()==true){
            E_word.setVisible(false);
            V_word.setVisible(true);
        }
        else{
            E_word.setVisible(true);
            V_word.setVisible(false);
        }
    }
}
