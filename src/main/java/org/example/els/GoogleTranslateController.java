package org.example.els;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.IOException;


public class GoogleTranslateController {
    @FXML
    private Button dictionary_menu;
    @FXML
    private Button google_translate_menu;
    @FXML
    private Button game_menu;
    @FXML
    private Button buttonTranslate;
    @FXML
    private AnchorPane pane;
    @FXML
    private ComboBox<String> comboBox_lang_input;
    @FXML
    private ComboBox<String> comboBox_lang_out;
    @FXML
    private TextArea Text_area_input;
    @FXML
    private TextArea Text_area_out;
    @FXML
    private Label dictionary_label;
    // khai báo thêm lable còn lại
    protected String lang_input;
    protected String lang_out;

    protected String textInput;
    protected String textOutput;


    /**
     * khởi tạo combobox
     */
    @FXML
    public void initialize() {
        comboBox_lang_input.setItems(API_Google_translator.listLANGUAGE);
        comboBox_lang_out.setItems(API_Google_translator.listLANGUAGE);
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
    public void comboBoxChanged(ActionEvent event){
            lang_input = comboBox_lang_input.getValue();
    }

    public void translateEvent(ActionEvent event) {
        lang_input = API_Google_translator.getLanguage(comboBox_lang_input.getValue());
        lang_out = API_Google_translator.getLanguage(comboBox_lang_out.getValue());
        try {
            String output=API_Google_translator.translate(Text_area_input.getText(),lang_input,lang_out);
            Text_area_out.setText(output);
           // Text_area_out.setStyle("-fx-font-size: 2em;");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // System.out.println("hello");
    }

}
