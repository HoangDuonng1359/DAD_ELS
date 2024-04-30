package org.example.els;

import Network.manager_internet;
import googleTranslate.API_Google_translator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javazoom.jl.decoder.JavaLayerException;
import java.io.IOException;

import static googleTranslate.sound.get_Audio;

public class GoogleTranslateController extends baseFormController {
    @FXML
    private ComboBox<String> comboBox_lang_input;
    @FXML
    private ComboBox<String> comboBox_lang_out;
    @FXML
    private TextArea Text_area_input;
    @FXML
    private TextArea Text_area_out;
    protected String lang_input;
    protected String lang_out;
    @FXML
    public Button ava_button;
    /**
     * khởi tạo combobox
     */
    @FXML
    public void initialize() {
        comboBox_lang_input.setItems(API_Google_translator.listLANGUAGE);
        comboBox_lang_out.setItems(API_Google_translator.listLANGUAGE);
        comboBox_lang_input.setValue("English");
        comboBox_lang_out.setValue("Vietnamese");
        SceneManage.setAvatar(ava_button,user.getAvata());
            Text_area_input.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.isEmpty() && newValue.charAt(newValue.length() - 1) != ' ' && !newValue.equals(oldValue)) {
                    if (!oldValue.equals(newValue)) {
                        new Thread(() -> {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Platform.runLater(() -> {
                                lang_input = API_Google_translator.getLanguage(comboBox_lang_input.getValue());
                                lang_out = API_Google_translator.getLanguage(comboBox_lang_out.getValue());
                                try {
                                    if(manager_internet.checkConnect()){
                                    String output = API_Google_translator.translate(Text_area_input.getText(), lang_input, lang_out);
                                    Text_area_out.setText(output);
                                    }
                                    else {
                                        newAlert("network" , "","Please connect to the internet!");
                                    }
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        }).start();
                    }
                }
            });
    }
// Add a listener to the textProperty

    public void comboBoxChanged(ActionEvent event) {
        lang_input = comboBox_lang_input.getValue();
    }
    @FXML
    public void translateEvent(ActionEvent event) {
        if(manager_internet.checkConnect()){
            lang_input = API_Google_translator.getLanguage(comboBox_lang_input.getValue());
            lang_out = API_Google_translator.getLanguage(comboBox_lang_out.getValue());
            try {
                String output = API_Google_translator.translate(Text_area_input.getText(), lang_input, lang_out);
                Text_area_out.setText(output);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            newAlert("network" , "","Please connect to the internet!");
        }
    }

    @FXML
    public void playAudioOut(ActionEvent event) throws IOException, JavaLayerException {
        if(manager_internet.checkConnect()){
            lang_out = API_Google_translator.getLanguage(comboBox_lang_out.getValue());
            String text = Text_area_out.getText();
            if (!text.isEmpty()) {
                get_Audio(text, lang_out);
            }
        }
        else {
            newAlert("network" , "","Please connect to the internet!");
        }
    }

    @FXML
    public void playAudioIn(ActionEvent event) throws IOException, JavaLayerException {
        if(manager_internet.checkConnect()){
            lang_input = API_Google_translator.getLanguage(comboBox_lang_input.getValue());
            String text = Text_area_input.getText();
            if (!text.isEmpty()) {
                get_Audio(text, lang_input);
            }
        }
        else {
            newAlert("network" , "","Please connect to the internet!");
        }
    }
}
