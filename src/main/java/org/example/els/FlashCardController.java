package org.example.els;

import Game.FlashCard.RecentW;
import dictionary.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;

import java.io.IOException;

import static Game.FlashCard.RecentW.getWord;

public class FlashCardController extends baseFormController{
    @FXML
    private Label E_word;
    @FXML
    private WebView V_word;
    static DictionaryManagement a= new DictionaryManagement();
    public void initialize() throws IOException {
        initgame();
    }
    public void initgame(){
        String target= RecentW.getWord();
        E_word.setText(target);
        V_word.getEngine().loadContent(a.Search(target));
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
