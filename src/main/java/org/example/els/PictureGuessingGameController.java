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

    private List<Question> questionList = database.getAllQuestion("jdbc:sqlite:src\\Data\\database.db");
    private Quiz quiz = new Quiz(questionList);
    //private List<Image> images = database.getAllImage("jdbc:sqlite:database.db");

    public PictureGuessingGameController() throws SQLException {

    }
    @FXML
    public void initialize(){
        player_lable.setText("Player" + user.getName());
        text_input.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                try {
                    baseSubmit();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JavaLayerException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    @FXML
    public void startGame(ActionEvent event) {
        quiz.newQuiz();
        int x = Record.getScore("pgg",user);
        if(x!=-1){
            max_score_label.setText("max score: " + x);
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
            max_score_label.setText("max score: " + x);
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
        if(quiz.guess(answer)){
           // sound.get_Audio(quiz.getCurrentQuestion().getResult().toString(),"en");
        }
        else{
           // sound.get_Audio("sai rồi cậu ơi","vi");
        }
        score.setText("Score:" + String.valueOf(quiz.getScore()));
        if (quiz.isFinished()) {
            Record.updateMaxScore("pgg",quiz.getScore(),user);
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
    public void submit(ActionEvent event) throws IOException, JavaLayerException, SQLException {
        baseSubmit();
        int x = Record.getScore("pgg",user);
        if(x!=-1){
            max_score_label.setText("max score: " + x);
        }
    }
    @FXML
    public void key_submit(KeyEvent event) throws IOException, JavaLayerException, SQLException {
        if(event.getCode() == KeyCode.ENTER){
            baseSubmit();
            System.out.println("hello");
        }
    }

}
