package org.example.els;

import Bookmark.RecentW;
import dictionary.DictionaryManagementDatabase;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class ELSController extends baseFormController {
    @FXML
    public Button ava_button;
    @FXML
    public void initialize() throws SQLException {
        listView.setItems(RecentW.getHistory());
        search_field.textProperty().addListener((observable, oldText, newText) -> {
            // Nếu nội dung của trường tìm kiếm thay đổi, thực hiện đề xuất tìm kiếm
            try {
                handleSearch(newText.trim());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(newText.isEmpty()){
                try {
                    listView.setItems(RecentW.getHistory());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        av.setSelected(true);
        va.setSelected(false);
        SceneManage.setAvatar(ava_button,user.getAvata());
    }
    @FXML
    public void key_search(KeyEvent event) throws SQLException {
        if(event.getCode() == KeyCode.ENTER){
            definitionView.getEngine().loadContent(DictionaryManagementDatabase.Search(search_field.getText(),getmode(av,va)));
            RecentW.addDB(search_field.getText(),getmode(av,va));
        }
    }
    @FXML
    public void key_search_list_view(KeyEvent event) throws SQLException {
        StringBuilder target = new StringBuilder(listView.getSelectionModel(). getSelectedItems().toString());
        target.delete(0,1);
        target.deleteCharAt(target.length()- 1);
        if(event.getCode() == KeyCode.ENTER){
            definitionView.getEngine().loadContent(DictionaryManagementDatabase.Search(target.toString(),getmode(av,va)));
            RecentW.addDB(target.toString(),getmode(av,va));
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
    public void handleSearch(String searchTerm) throws SQLException {
        if (!searchTerm.isEmpty()) {
            ObservableList<String> searchResult = DictionaryManagementDatabase.prexSearch(searchTerm,getmode(av,va));

            //ObservableList<String> searchResult = DictionaryManagement.prexSearch(searchTerm);
            if (searchResult != null && !searchResult.isEmpty()) {
                listView.setItems(searchResult);
            } else {
                listView.getItems().clear();
            }
        } else {
            listView.getItems().clear();
        }
    }

}


