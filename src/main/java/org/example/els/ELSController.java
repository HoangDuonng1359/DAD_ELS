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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private Label dictionary_label;

    @FXML
    private Label googleTranslate_label;
    @FXML
    private Label game_label;
    @FXML
    private Label addEdit_label;


    @FXML
    public void openFormGoogle(ActionEvent event){
        try {
            SceneManage.showScene(root,stage,scene,event,"google_translate.fxml");
        } catch (IOException e) {
            System.out.println("lỗi không mở được form: " + e.getMessage());
        }
    }
    @FXML
    public void openFormAddAndEdit(ActionEvent event){
        try {
            SceneManage.showScene(root,stage,scene,event,"addWord.fxml");
        } catch (IOException e) {
            System.out.println("lỗi không mở được form");
        }
    }
    @FXML
    public void initialize() {
        dictionary_label.setVisible(false);
        googleTranslate_label.setVisible(false);
        game_label.setVisible(false);
        addEdit_label.setVisible(false);
       // listView.setItems(dictionaryManagement.showAllWords());
        search_field.textProperty().addListener((observable, oldText, newText) -> {
            // Nếu nội dung của trường tìm kiếm thay đổi, thực hiện đề xuất tìm kiếm
            handleSearch(newText.trim());
        });
    }
    @FXML
    public void handleMouseClickListView (MouseEvent event) throws SQLException {

        StringBuilder target = new StringBuilder(listView.getSelectionModel(). getSelectedItems().toString());
        target.delete(0,1);
        target.deleteCharAt(target.length()- 1);
       // definitionView.getEngine().loadContent(dictionaryManagement.Search(target.toString()));
         definitionView.getEngine().loadContent(DictionaryManagementDatabase.Search(target.toString(),true));
        RecentW.addDB(target.toString(),true);
    }
    @FXML
    public void handleMouseClickButtonSearch(MouseEvent event) throws SQLException {
        String target = new String(search_field.getText());
        definitionView.getEngine().loadContent(DictionaryManagementDatabase.Search(target,true));
        RecentW.addDB(target, true);
    }
    public void handleSearch(String searchTerm) {
        if (!searchTerm.isEmpty()) {
            ObservableList<String> searchResult = DictionaryManagementDatabase.prexSearch(searchTerm,true);
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


