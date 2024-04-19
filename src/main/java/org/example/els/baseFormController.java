//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.els;

import java.io.*;
import java.sql.SQLException;

import dictionary.DictionaryManagement;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class baseFormController extends SceneManage {
    protected static Stage stage;
    protected static Scene scene;
    protected static Parent root;
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
    private Label home_label;
    @FXML
    private Label more_label;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    protected WebView definitionView;
    protected static DictionaryManagement dictionaryManagement = new DictionaryManagement();
    protected static BufferedWriter bookwriter;

    static {
        try {
            bookwriter = new BufferedWriter(new FileWriter("src/Data/BookmarkList.txt",true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected static BufferedReader bookreader;
    static {
        try {
            bookreader = new BufferedReader(new FileReader("src/Data/BookmarkList.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void openFormGoogle(ActionEvent event) {
        try {
            showScene(root,stage, scene, event, "google_translate.fxml");
        } catch (IOException var3) {
            System.out.println("lỗi không mở được form");
        }
    }

    @FXML
    public void openFormAddAndEdit(ActionEvent event) {
        try {
            showScene(root,stage, scene, event,  "addWord.fxml");
        } catch (IOException var3) {
            System.out.println("lỗi không mở được form");
        }

    }

    @FXML
    public void openFormGame(ActionEvent event) {
        try {
            showScene(root,stage, scene, event,  "game.fxml");
        } catch (IOException var3) {
            System.out.println("lỗi không mở được form");
        }

    }
    @FXML
    public void  openFormDictionary(ActionEvent event) {
        try {
            showScene(root,stage, scene, event,  "dictionary.fxml");
        }
        catch (IOException var3) {
            System.out.println("lỗi không mở được form");
        }
    }
    @FXML
    public void openFormHome(ActionEvent event){
        try {
            showScene(root,stage, scene, event,  "Home.fxml");
        }
        catch (IOException var3) {
            System.out.println("lỗi không mở được form");
        }
    }
    @FXML
    public void openFormMore(ActionEvent event){
        try {
            showScene(root,stage, scene, event,  "more.fxml");
        }
        catch (IOException var3) {
            System.out.println("lỗi không mở được form" + var3);
        }
    }
    @FXML
    public void openFormEdit(ActionEvent event){
        try {
            showScene(root,stage, scene, event,  "EditWord.fxml");
        }
        catch (IOException var3) {
            System.out.println("lỗi không mở được form" + var3);
        }
    }
    @FXML
    public void openFormDelete(ActionEvent event){
        try {
            showScene(root,stage, scene, event,  "deleteWord.fxml");
        }
        catch (IOException var3) {
            System.out.println("lỗi không mở được form" + var3);
        }
    }
    @FXML
    public void initialize() throws IOException, SQLException {
        this.dictionary_label.setVisible(false);
        this.googleTranslate_label.setVisible(false);
        this.game_label.setVisible(false);
        this.addEdit_label.setVisible(false);
        syncBookData();
    }
    public void syncBookData(){
        try {
            String str=new String();
            while ((str = bookreader.readLine()) != null) {
                String[] save = new String[5];
                save = str.split(" ",3);
                if(save[0].equals("+")){
                    dictionaryManagement.insert(save[1],save[2]);
                }
                else if(save[0].equals("-")){
                    dictionaryManagement.remove(save[1]);
                }
                else if(save[0].equals("#")){
                    dictionaryManagement.setExplain(save[1],save[2]);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to sync");
        }
    }
    public static void closefile(){
        try {
            bookwriter.close();
        } catch (IOException e) {
            System.out.println("baseFormController closefile IOException");
        }
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
    public void eventHoverHome(MouseEvent event){
        this.home_label.setVisible(true);
    }
    @FXML
    public void eventExitHome(MouseEvent event){
        this.home_label.setVisible(false);
    }
    @FXML
    public void eventHoverMore(MouseEvent event){
        this.more_label.setVisible(true);
    }
    @FXML
    public void eventExitMore(MouseEvent event){
        this.more_label.setVisible(false);
    }
    @FXML
    public void eventExitaddEdit(MouseEvent event) {
        this.addEdit_label.setVisible(false);
    }
    public void newAlert(Stage stage, String title , String headerText , String contentText ){
        Platform.runLater(()->{ // đảm bảo rằng Alert luôn được chạy trên luồng chính
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(stage); // khởi tạo trên stage hiện tại
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.setContentText(contentText);
            alert.showAndWait();
        });
    }
}