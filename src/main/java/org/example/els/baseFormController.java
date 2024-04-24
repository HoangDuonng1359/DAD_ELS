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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import user.User;

public class baseFormController extends SceneManage {
    protected static Stage stage;
    protected static Scene scene;
    protected static Parent root;
    @FXML
    protected WebView definitionView;
    @FXML
    protected ListView listView;
    @FXML
    protected TextField search_field;
    @FXML
    public CheckBox av;
    @FXML
    public CheckBox va;
    public static String DATABASE_URL = "jdbc:sqlite:src\\Data\\database.db";
   // protected static DictionaryManagement dictionaryManagement = null;
    protected static BufferedWriter bookwriter;
    public static User user = null;
//    static {
//        try {
//            bookwriter = new BufferedWriter(new FileWriter("src/Data/BookmarkList.txt",true));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    protected static BufferedReader bookreader;
//    static {
//        try {
//            bookreader = new BufferedReader(new FileReader("src/Data/BookmarkList.txt"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    @FXML
    public void openProfile(ActionEvent event) {
        try {
            showScene(root,stage, scene, event, "profile.fxml");
        } catch (IOException var3) {
            System.out.println("lỗi không mở được form");
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
    }
//    public void syncBookData(){
//        try {
//            String str=new String();
//            while ((str = bookreader.readLine()) != null) {
//                String[] save = new String[5];
//                save = str.split(" ",3);
//                if(save[0].equals("+")){
//                    //dictionaryManagement.insert(save[1],save[2]);
//                }
//                else if(save[0].equals("-")){
//                    //dictionaryManagement.remove(save[1]);
//                }
//                else if(save[0].equals("#")){
//                   // dictionaryManagement.setExplain(save[1],save[2]);
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Failed to sync");
//        }
//    }
    public static void closefile(){
        try {
            bookwriter.close();
        } catch (IOException e) {
            System.out.println("baseFormController closefile IOException");
        }
    }
    public static void newAlert(Stage stage, String title , String headerText , String contentText ){
        Platform.runLater(()->{ // đảm bảo rằng Alert luôn được chạy trên luồng chính
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(stage); // khởi tạo trên stage hiện tại
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.setContentText(contentText);
            alert.showAndWait();
        });
    }
    public static boolean getmode(CheckBox av, CheckBox va){
        if(av.isSelected()) return true;
        else return false;
    }
    @FXML
    public void setmodeav(ActionEvent event){
        if(av.isSelected()){
            va.setSelected(false);
        }
        else {
            va.setSelected(true);
        }
        av.setSelected(true);
        va.setSelected(false);
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
}