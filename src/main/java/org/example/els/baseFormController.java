//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.els;

import java.io.IOException;
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

public class baseFormController {
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
    @FXML
    private Label googleTranslate_label;
    @FXML
    private Label game_label;
    @FXML
    private Label addEdit_label;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    protected WebView definitionView;

    @FXML
    public void openFormGoogle(ActionEvent event) {
        try {
            SceneManage.showScene(stage, scene, fxmlLoader, event, "google_translate.fxml");
        } catch (IOException var3) {
            System.out.println("lỗi không mở được form");
        }

    }

    @FXML
    public void openFormAddAndEdit(ActionEvent event) {
        try {
            SceneManage.showScene(stage, scene, fxmlLoader, event, "addAndEdit.fxml");
        } catch (IOException var3) {
            System.out.println("lỗi không mở được form");
        }

    }

    @FXML
    public void openFormGame(ActionEvent event) {
        try {
            SceneManage.showScene(stage, scene, fxmlLoader, event, "game.fxml");
        } catch (IOException var3) {
            System.out.println("lỗi không mở được form");
        }

    }
    @FXML
    public void  openFormDictionary(ActionEvent event) {
        try {
            SceneManage.showScene(stage, scene,fxmlLoader, event, "ELS-view.fxml");
        }
        catch (IOException var3) {
            System.out.println("lỗi không mở được form");
        }
    }

    @FXML
    public void initialize() {
        this.dictionary_label.setVisible(false);
        this.googleTranslate_label.setVisible(false);
        this.game_label.setVisible(false);
        this.addEdit_label.setVisible(false);
    }

    @FXML
    public void eventHoverDictionary(MouseEvent event) {
        this.dictionary_label.setVisible(true);
    }

    @FXML
    public void eventHovergoogleTranslate(MouseEvent event) {
        this.googleTranslate_label.setVisible(true);
    }

    @FXML
    public void eventHovergame(MouseEvent event) {
        this.game_label.setVisible(true);
    }

    @FXML
    public void eventHoveraddEdit(MouseEvent event) {
        this.addEdit_label.setVisible(true);
    }

    @FXML
    public void eventExitDictionary(MouseEvent event) {
        this.dictionary_label.setVisible(false);
    }

    @FXML
    public void eventExitgoogleTranslate(MouseEvent event) {
        this.googleTranslate_label.setVisible(false);
    }

    @FXML
    public void eventExitgame(MouseEvent event) {
        this.game_label.setVisible(false);
    }

    @FXML
    public void eventExitaddEdit(MouseEvent event) {
        this.addEdit_label.setVisible(false);
    }
}