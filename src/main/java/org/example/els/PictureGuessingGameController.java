package org.example.els;

import Game.PictureGuessingGame.Question;
import Game.PictureGuessingGame.Quiz;
import Game.PictureGuessingGame.database;
import googleTranslate.sound;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javazoom.jl.decoder.JavaLayerException;

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
    private List<Question> questionList = database.getAllQuestion("jdbc:sqlite:database.db");
    private Quiz quiz = new Quiz(questionList);
    //private List<Image> images = database.getAllImage("jdbc:sqlite:database.db");

    public PictureGuessingGameController() throws SQLException {

    }
    @FXML
    public void initialize(){
        text_input.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                try {
                    baseSubmit();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    @FXML
    public void startGame(ActionEvent event) {
        quiz.newQuiz();
        image_View.setImage(quiz.getCurrentQuestion().getImage());
        suggest_label.setText(quiz.getCurrentQuestion().getQues());
        score.setText("Score:" + String.valueOf(quiz.getScore()));
    }

    @FXML
    public void endGame(ActionEvent event) {
        quiz.newQuiz();
    }
    private void baseSubmit() throws IOException, JavaLayerException {
        String answer = text_input.getText();
        if(quiz.guess(answer)){
            sound.get_Audio(quiz.getCurrentQuestion().getResult().toString(),"en");
        }
        else{
            sound.get_Audio("sai rồi cậu ơi","vi");
        }
        score.setText("Score:" + String.valueOf(quiz.getScore()));
        if (quiz.isFinished()) {
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
    }
    @FXML
    public void submit(ActionEvent event) throws IOException, JavaLayerException {
        baseSubmit();
    }
    @FXML
    public void key_submit(KeyEvent event) throws IOException, JavaLayerException {
        if(event.getCode() == KeyCode.ENTER){
            baseSubmit();
            System.out.println("hello");
        }
    }

}
