package org.example.els;

import Game.FlashCard.RecentW;
import dictionary.DictionaryManagement;
import dictionary.DictionaryManagementDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class ELSController extends baseFormController {
    protected static Stage stage;
    protected static Scene scene;
    protected static Parent root;
    @FXML
    public CheckBox av;
    @FXML
    public CheckBox va;

    @FXML
    public void initialize() {
        search_field.textProperty().addListener((observable, oldText, newText) -> {
            // Nếu nội dung của trường tìm kiếm thay đổi, thực hiện đề xuất tìm kiếm
            handleSearch(newText.trim());
        });
        av.setSelected(true);
        va.setSelected(false);
    }
    @FXML
    public void setmodeav(ActionEvent event){
        if(av.isSelected()){
            va.setSelected(false);
        }
        else {
            va.setSelected(true);
        }

    }
    @FXML
    public void setmodeva(ActionEvent event){
        if(va.isSelected()){
            av.setSelected(false);
        }
        else {
            av.setSelected(true);
        }
    }
    @FXML
    public void handleMouseClickListView (MouseEvent event) throws SQLException {

        StringBuilder target = new StringBuilder(listView.getSelectionModel(). getSelectedItems().toString());
        target.delete(0,1);
        target.deleteCharAt(target.length()- 1);
       // definitionView.getEngine().loadContent(dictionaryManagement.Search(target.toString()));
         definitionView.getEngine().loadContent(DictionaryManagementDatabase.Search(target.toString(),getmode(av,va)));
        RecentW.addDB(target.toString(),getmode(av,va));
    }
    @FXML
    public void handleMouseClickButtonSearch(MouseEvent event) throws SQLException {
        String target = new String(search_field.getText());
        definitionView.getEngine().loadContent(DictionaryManagementDatabase.Search(target,getmode(av,va)));
        RecentW.addDB(target, getmode(av,va));
    }
    public void handleSearch(String searchTerm) {
        if (!searchTerm.isEmpty()) {
            ObservableList<String> searchResult = DictionaryManagementDatabase.prexSearch(searchTerm,getmode(av,va));
            //ObservableList<String> searchResult = DictionaryManagement.prexSearch(searchTerm);
            if (searchResult != null && !searchResult.isEmpty()) {
                listView.setItems(searchResult);
            } else {
                listView.getItems().clear();
                ObservableList<String> items = FXCollections.observableArrayList("");
                listView.setItems(items);
            }
        } else {
            listView.getItems().clear();
            ObservableList<String> items = FXCollections.observableArrayList("");
            listView.setItems(items);
        }
    }

}


