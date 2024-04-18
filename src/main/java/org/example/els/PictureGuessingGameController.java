package org.example.els;

import Game.PictureGuessingGame.Question;
import Game.PictureGuessingGame.Quiz;
import Game.PictureGuessingGame.database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;
import java.util.List;

public class PictureGuessingGameController extends baseFormController {
    @FXML
    private TextField text_input;
    @FXML
    private Label suggest_label;
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

    @FXML
    public void submit(ActionEvent event) {
        String answer = text_input.getText();
        quiz.guess(answer);
        score.setText("Score:" + String.valueOf(quiz.getScore()));
        if (quiz.isFinished()) {
            image_View.setImage(null);
            suggest_label.setText("");
            quiz.newQuiz();
            newAlert(stage, "End game", "", "End game \n Your score: " + quiz.getScore());
        } else {
            quiz.nextQuestion();
        }
    }
}
