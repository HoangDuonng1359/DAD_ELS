package org.example.els;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;

import static googleTranslate.sound.get_Audio;

public class GoogleTranslateController extends baseFormController {
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
    @FXML
    private Label googleTranslate_label;
    @FXML
    private Label game_label;
    @FXML
    private Label addEdit_label;
    // khai báo thêm lable còn lại
    protected String lang_input;
    protected String lang_out;

    protected String textInput;
    protected String textOutput;
    //  private ScheduledExecutorService executorService;

    /**
     * khởi tạo combobox
     */
    @FXML
    public void initialize() {
        comboBox_lang_input.setItems(API_Google_translator.listLANGUAGE);
        comboBox_lang_out.setItems(API_Google_translator.listLANGUAGE);
        dictionary_label.setVisible(false); // khởi tạo label này bị ẩn đi
        game_label.setVisible(false);
        googleTranslate_label.setVisible(false);
        addEdit_label.setVisible(false);
        // làm các lable còn lại Quang Anh nhá
//        Text_area_input.textProperty().addListener((observable, oldValue, newValue) -> {
//            if(!oldValue.equals(newValue)){
//                new Thread(() -> {
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Platform.runLater(() -> {
//                        lang_input = API_Google_translator.getLanguage(comboBox_lang_input.getValue());
//                        lang_out = API_Google_translator.getLanguage(comboBox_lang_out.getValue());
//                        try {
//                            String output=API_Google_translator.translate(Text_area_input.getText(),lang_input,lang_out);
//                            Text_area_out.setText(output);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });
//                }).start();
//            }
//
//        });
    }
// Add a listener to the textProperty

    @FXML
    public void eventHoverDictionary(MouseEvent event) {
        dictionary_label.setVisible(true); // khi rê chuột vào button thì lable hiện lên
    }

    @FXML
    public void eventHovergoogleTranslate(MouseEvent event) {
        googleTranslate_label.setVisible(true);
    }

    @FXML
    public void eventHovergame(MouseEvent event) {
        game_label.setVisible(true);
    }

    @FXML
    public void eventHoveraddEdit(MouseEvent event) {
        addEdit_label.setVisible(true);
    }


    @FXML
    public void eventExitDictionary(MouseEvent event) {
        dictionary_label.setVisible(false); // // khi rê chuột ra button thì lable ẩn đi
    }

    @FXML
    public void eventExitgoogleTranslate(MouseEvent event) {
        googleTranslate_label.setVisible(false);
    }

    @FXML
    public void eventExitgame(MouseEvent event) {
        game_label.setVisible(false);
    }

    @FXML
    public void eventExitaddEdit(MouseEvent event) {
        addEdit_label.setVisible(false);
    }

    public void comboBoxChanged(ActionEvent event) {
        lang_input = comboBox_lang_input.getValue();
    }

    public void translateEvent(ActionEvent event) {
        lang_input = API_Google_translator.getLanguage(comboBox_lang_input.getValue());
        lang_out = API_Google_translator.getLanguage(comboBox_lang_out.getValue());
        try {
            String output = API_Google_translator.translate(Text_area_input.getText(), lang_input, lang_out);
            Text_area_out.setText(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void playAudio(ActionEvent event) throws IOException, JavaLayerException {
        lang_out = API_Google_translator.getLanguage(comboBox_lang_out.getValue());
        String text = Text_area_out.getText();
        if(!text.isEmpty()){
            get_Audio(text,lang_out);
        }
    }
}
