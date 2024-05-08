package org.example.els;

import Game.PictureGuessingGame.Question;
import Game.PictureGuessingGame.Quiz;
import Game.PictureGuessingGame.database;
import Sound.sound_manage;
import googleTranslate.sound;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;
import user.Record;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PictureGuessingGameController extends baseFormController {
    @FXML
    private TextField text_input;
    @FXML
    private Label suggest_label;
    @FXML
    private Button submit_button;
    @FXML
    private ImageView image_View;
    @FXML
    private Label score;
    @FXML
    private Label max_score_label;
    @FXML
    private Label player_lable;
    @FXML
    private Button ava_button;

    private List<Question> questionList = database.getAllQuestion("jdbc:sqlite:src\\Data\\database.db");
    private Quiz quiz = new Quiz(questionList);

    private final Media mediaWorng = sound_manage.getMediaByNameFormSRC("src/media/worng_sound.mp3");
    private final Media mediaCorrecct = sound_manage.getMediaByNameFormSRC("src/media/correct_sound.mp3");


    @FXML
    public void initialize(){
        player_lable.setText("Player: " + user.getName());
        text_input.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                try {
                    baseSubmit();
                } catch (IOException | JavaLayerException | SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        SceneManage.setAvatar(ava_button, user.getAvata());
    }

    @FXML
    public void startGame(ActionEvent event) throws SQLException {
        quiz.newQuiz();
        int x = Record.getScore("pgg",user);
        if(x!=-1){
            max_score_label.setText("Max score: " + x);
        }
        image_View.setImage(quiz.getCurrentQuestion().getImage());
        suggest_label.setText(quiz.getCurrentQuestion().getQues());
        score.setText("Score:" + String.valueOf(quiz.getScore()));
    }

    @FXML
    public void endGame(ActionEvent event) throws SQLException {
        Record.updateMaxScore("pgg",quiz.getScore(),user);
        int x = Record.getScore("pgg",user);
        if(x!=-1){
            max_score_label.setText("Max score: " + x);
        }
        image_View.setImage(null);
        suggest_label.setText("");
        newAlert(stage, "End game", "", "End game \n Your score: " + quiz.getScore());
        quiz.newQuiz();
        image_View.setImage(quiz.getCurrentQuestion().getImage());
        suggest_label.setText(quiz.getCurrentQuestion().getQues());
    }

    private void baseSubmit() throws IOException, JavaLayerException, SQLException {
        String answer = text_input.getText();
        if (quiz.guess(answer)) {
            sound_manage.playMedia(mediaCorrecct);
        } else {
            sound_manage.playMedia(mediaWorng);
        }
        score.setText("Score:" + String.valueOf(quiz.getScore()));
        if (quiz.isFinished()) {
            Record.updateMaxScore("pgg", quiz.getScore(), user);
            image_View.setImage(null);
            suggest_label.setText("");
            newAlert(stage, "End game", "", "End game \n Your score: " + quiz.getScore());
            quiz.newQuiz();
            image_View.setImage(quiz.getCurrentQuestion().getImage());
            suggest_label.setText(quiz.getCurrentQuestion().getQues());
        } else {
            quiz.nextQuestion();
            image_View.setImage(quiz.getCurrentQuestion().getImage());
            suggest_label.setText(quiz.getCurrentQuestion().getQues());
        }
        text_input.clear();
    }

    @FXML
    public void submit(ActionEvent event) throws IOException, JavaLayerException, SQLException {
        baseSubmit();
        int x = Record.getScore("pgg",user);
        if(x!=-1){
            max_score_label.setText("Max score: " + x);
        }
    }

    @FXML
    public void key_submit(KeyEvent event) throws IOException, JavaLayerException, SQLException {
        if (event.getCode() == KeyCode.ENTER) {
            baseSubmit();
        }
    }

}
