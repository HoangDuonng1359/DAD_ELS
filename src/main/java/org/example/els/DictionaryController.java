package org.example.els;

import Bookmark.RecentW;
import Network.manager_internet;
import dictionary.DictionaryManagementDatabase;
import googleTranslate.sound;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;
import java.sql.SQLException;

public class DictionaryController extends baseFormController {
    @FXML
    public Button ava_button;
    @FXML
    private Button audio_button_in;

    private String target;

    @Override
    public void initialize() throws SQLException {
        audio_button_in.setVisible(false);

        search_field.textProperty().addListener((observable, oldText, newText) -> {
            // Nếu nội dung của trường tìm kiếm thay đổi, thực hiện đề xuất tìm kiếm
            listView.getItems().clear();
            try {
                handleSearch(newText.trim());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (newText.isEmpty()) {
                try {
                    listView.setItems(RecentW.getHistory(getmode(av,va)));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        av.setSelected(true);
        va.setSelected(false);
        listView.setItems(RecentW.getHistory(getmode(av,va)));
        SceneManage.setAvatar(ava_button, user.getAvata());
    }
    @Override
    public void setmodeav(ActionEvent event) throws SQLException {
        if(av.isSelected()){
            va.setSelected(false);
        }
        else {
            va.setSelected(true);
        }
        listView.getItems().clear();
        listView.setItems(RecentW.getHistory(getmode(av,va)));
    }
    @Override
    public void setmodeva(ActionEvent event) throws SQLException {
        if (va.isSelected()) {
            av.setSelected(false);

        } else {
            av.setSelected(true);
        }
        listView.getItems().clear();
        listView.setItems(RecentW.getHistory(getmode(av,va)));
    }
    @FXML
    public void playAudio(ActionEvent event) throws IOException, JavaLayerException {
        if(manager_internet.checkConnect()){
            if (target != null) {
                sound.get_Audio(target, (getmode(av, va).equals("av")) ? "en" : "vi");
            }
        }
        else {
            newAlert("network" , "","Please connect to the internet!");
        }
    }

    @FXML
    public void key_search(KeyEvent event) throws SQLException {
        if (event.getCode() == KeyCode.ENTER && !search_field.getText().isEmpty()) {
            String explain = DictionaryManagementDatabase.Search(search_field.getText().trim(), getmode(av, va));
            definitionView.getEngine().loadContent(explain);
            RecentW.addDB(search_field.getText(), getmode(av, va));
            if (!explain.equals("NO FOUND") && !explain.equals("you have deleted this word")) {
                audio_button_in.setVisible(true);
                target = search_field.getText();
            } else {
                audio_button_in.setVisible(false);
            }
        }
    }

    @FXML
    public void key_search_list_view(KeyEvent event) throws SQLException {
        StringBuilder target = new StringBuilder(listView.getSelectionModel().getSelectedItems().toString());
        target.delete(0, 1);
        target.deleteCharAt(target.length() - 1);
        if (event.getCode() == KeyCode.ENTER && !target.isEmpty()) {
            String explain = DictionaryManagementDatabase.Search(target.toString(), getmode(av, va));
            definitionView.getEngine().loadContent(explain);
            RecentW.addDB(target.toString(), getmode(av, va));
            if (!explain.equals("NO FOUND") && !explain.equals("you have deleted this word")) {
                audio_button_in.setVisible(true);
                this.target = target.toString();
            }
        } else {
            audio_button_in.setVisible(false);
        }
    }

    @FXML
    public void handleMouseClickListView(MouseEvent event) throws SQLException {
        StringBuilder target = new StringBuilder(listView.getSelectionModel().getSelectedItems().toString());
        target.delete(0, 1);
        target.deleteCharAt(target.length() - 1);
        if (!target.isEmpty()) {
            String explain = DictionaryManagementDatabase.Search(target.toString(), getmode(av, va));
            definitionView.getEngine().loadContent(explain);
            RecentW.addDB(target.toString(), getmode(av, va));
            if (!explain.equals("NO FOUND") && !explain.equals("you have deleted this word")) {
                audio_button_in.setVisible(true);
                this.target = target.toString();
            }
        }else {
            audio_button_in.setVisible(false);
        }
    }

    @FXML
    public void handleMouseClickButtonSearch(MouseEvent event) throws SQLException {
        String target = search_field.getText().trim();
        if (!target.isEmpty()) {
            String explain = DictionaryManagementDatabase.Search(target, getmode(av, va));
            definitionView.getEngine().loadContent(explain);
            RecentW.addDB(search_field.getText(), getmode(av, va));
            if (!explain.equals("NO FOUND") && !explain.equals("you have deleted this word")) {
                audio_button_in.setVisible(true);
                this.target = target;
            }
        } else
        {
            audio_button_in.setVisible(false);
        }
    }

    public void handleSearch(String searchTerm) throws SQLException {
        if (!searchTerm.isEmpty()) {
            ObservableList<String> searchResult = DictionaryManagementDatabase.prexSearch(searchTerm, getmode(av, va));

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


