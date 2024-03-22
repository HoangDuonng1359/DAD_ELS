package org.example.els;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;

public class ELSController {
    protected static Stage stage;
    protected static Scene scene;
    protected static FXMLLoader fxmlLoader;
    @FXML
    private Button dictionary_menu;
    @FXML
    private Button google_translate_menu;
    @FXML
    private Button game_menu;
    @FXML
    private Button edit_menu;
    @FXML
    private Label dictionary_label;
    // khởi tạo thêm lable còn lại
    @FXML
    private AnchorPane anchorPane;
    @FXML
    protected WebView definitionView;
    @FXML
    public void openFormGoogle(ActionEvent event){
        try {
            SceneManage.showScene(stage,scene,fxmlLoader,event,"google_translate.fxml");
        } catch (IOException e) {
            System.out.println("lỗi không mở được form");
        }
    }
    @FXML
    public void openFormAddAndEdit(ActionEvent event){
        try {
            SceneManage.showScene(stage,scene,fxmlLoader,event,"addAndEdit.fxml");
        } catch (IOException e) {
            System.out.println("lỗi không mở được form");
        }
    }
    @FXML
    public void initialize() {
        dictionary_label.setVisible(false); // khởi tạo label này bị ẩn đi
        // làm các lable còn lại Quang Anh nhá
    }
    @FXML
    public void eventHoverDictionary(MouseEvent event){
        dictionary_label.setVisible(true); // khi rê chuột vào button thì lable hiện lên
    }

    @FXML
    public void eventExitDictionary(MouseEvent event){
        dictionary_label.setVisible(false); // // khi rê chuột ra button thì lable ẩn đi
    }

    // thêm các eventHovergoogleTranslate_label ...
}