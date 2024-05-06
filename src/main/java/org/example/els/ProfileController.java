package org.example.els;

import Profile.user_management;
import dictionary.DictionaryManagementDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import user.Record;
import java.io.IOException;
import java.sql.SQLException;


public class ProfileController extends baseFormController {
    @FXML
    private Button ava_button_profile;
    @FXML
    private Label name_label;
    @FXML
    private Label numberEdit;
    @FXML
    private Label numberAdd;
    @FXML
    private Label numberRemove;
    @FXML
    private BarChart<String, Number>  chart;
    @Override
    public void initialize() throws SQLException {
       init();
    }

    private void init() throws SQLException {
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Series 1");
        int MultichoiceScore = Record.getScore("multichoice",user);
        if(MultichoiceScore == -1 ) {
            MultichoiceScore = 0;
        }
        series1.getData().add(new XYChart.Data<>("Multichoice", MultichoiceScore ));
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Series 2");
        int pgGame = Record.getScore("pgg",user);
        if(pgGame == -1 ) pgGame = 0;
        series2.getData().add(new XYChart.Data<>("PG game", pgGame));
        chart.setTitle("Record game");
        chart.getData().addAll(series1, series2);
        chart.getYAxis().setLabel("Score");
        name_label.setText(user.getName());
        if (user.getAvata() != null) {
            SceneManage.setAvatar(ava_button_profile,user.getAvata());
        }
        numberEdit.setText("Word added:" + DictionaryManagementDatabase.numberChange("+") + " words");
        numberAdd.setText("Word edited:" + DictionaryManagementDatabase.numberChange("#") + " words");
        numberRemove.setText("Word removed:" + DictionaryManagementDatabase.numberChange("-") + " words");
    }

    @FXML
    public void changeName(ActionEvent event) throws IOException {
        SceneManage.showScene(root, stage, scene, event, "changeName.fxml");
    }

    @FXML
    public void changeAvata(ActionEvent event) throws SQLException {
        Image image = user_management.changeAvt(stage);
        user.setAvata(image);
        SceneManage.setAvatar(ava_button_profile,user.getAvata());
    }

    @FXML
    public void changePassword(ActionEvent event) throws IOException {
        SceneManage.showScene(root, stage, scene, event, "changePassword.fxml");
    }

    @FXML
    public void reset_default(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Profile");
        alert.setHeaderText("");
        alert.setContentText("Do you want to return to default mode?");
        ButtonType buttonTypeOK = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("CANCEL");
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeOK) {
                if(user_management.reset_default(user)){
                    try {
                        init();
                        chart.getData().clear();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    newAlert("Profile","","successful");
                }else {
                    newErrorAlert("ProfileEnrror","","An error occurred!");
                }
            }
        });
    }
}
