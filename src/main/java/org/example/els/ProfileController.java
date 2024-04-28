package org.example.els;

import Profile.user_management;
import dictionary.DictionaryManagementDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
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
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Series 1");
        series1.getData().add(new XYChart.Data<>("Multichoice", Record.getScore("multichoice",user)));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Series 2");
        series2.getData().add(new XYChart.Data<>("PG game", Record.getScore("pgg",user)));
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


}
