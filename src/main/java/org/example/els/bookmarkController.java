package org.example.els;

import Bookmark.bookmark;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.SQLException;

public class bookmarkController extends DictionaryController{

    @Override
    public void initialize() throws SQLException {
        audio_button_in.setVisible(false);
        av.setSelected(true);
        va.setSelected(false);
        search_field.textProperty().addListener((observable, oldText, newText) -> {
            // Nếu nội dung của trường tìm kiếm thay đổi, thực hiện đề xuất tìm kiếm
            listView.getItems().clear();
            try {
                handleSearch(newText.trim());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(newText.isEmpty()){
                try {
                    listView.setItems(bookmark.show_all_word(getmode(av,va)));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        listView.setItems(bookmark.show_all_word(getmode(av,va)));
        SceneManage.setAvatar(ava_button, user.getAvata());
    }
    @Override
    public void handleSearch(String searchTerm) throws SQLException {
        if (!searchTerm.isEmpty()) {
            ObservableList<String> searchResult = bookmark.prexSearch(searchTerm, getmode(av, va), user);
            if (searchResult != null && !searchResult.isEmpty()) {
                listView.setItems(searchResult);
            } else {
                listView.getItems().clear();
            }
        } else {
            listView.getItems().clear();
        }
    }
    @FXML
    public void delete_word(ActionEvent event) throws SQLException {
        String target = "";
        if(listView.getSelectionModel().getSelectedItem()!=null)
        {
            target= listView.getSelectionModel().getSelectedItem().toString();
        }
        if(!target.isEmpty()){
            bookmark.deleteWord(target,getmode(av,va),user);
            newAlert("Delete", "", "Đã xóa khỏi bookmark");
        }
        listView.setItems(bookmark.show_all_word(getmode(av,va)));
        definitionView.getEngine().loadContent("");
        audio_button_in.setVisible(false);
    }
    public void setmodeav(ActionEvent event) throws SQLException {
        va.setSelected(!av.isSelected());
        listView.getItems().clear();
        listView.setItems(bookmark.show_all_word(getmode(av,va)));
    }
    public void setmodeva(ActionEvent event) throws SQLException {
        av.setSelected(!va.isSelected());
        listView.getItems().clear();
        listView.setItems(bookmark.show_all_word(getmode(av,va)));
    }
}
