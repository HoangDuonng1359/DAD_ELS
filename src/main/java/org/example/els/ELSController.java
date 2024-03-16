package org.example.els;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private AnchorPane pane;
    @FXML
    protected WebView definitionView;
    @FXML
    public void openFormGoogle(ActionEvent event) throws IOException {
        SceneManage.showScene(stage,scene,fxmlLoader,event,"google_translate.fxml");
    }

}