package org.example.els;

import Game.MultiChoice.Question;
import Game.MultiChoice.Quiz;
import Sound.sound_manage;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.util.Duration;
import user.Record;

import java.sql.SQLException;
import java.util.List;

import static Game.MultiChoice.importData.importFormFileCSV;
import static Game.MultiChoice.importData.insertFromDB;

public class MultiChoiceGameController extends baseFormController {
    @FXML
    private Label question_lable;
    @FXML
    private Label score_lable;
    @FXML
    private Label max_score_label;
    @FXML
    private Button answerA;
    @FXML
    private Button answerB;
    @FXML
    private Button answerC;
    @FXML
    private Button answerD;
    private List<Question> questions;
    private Quiz quiz ;
    private final Media mediaWorng = sound_manage.getMediaByNameFormSRC("src/media/worng_sound.mp3");
    private final Media mediaCorrecct = sound_manage.getMediaByNameFormSRC("src/media/correct_sound.mp3");

    @Override
    public void initialize() throws SQLException {
        Import_data_form_file();
        initgame();
    }

    private void Import_data_form_file() throws SQLException {
        //questions = importFormFileCSV("src/Data/questions.csv");
        questions = insertFromDB(DATABASE_URL);
    }

    private void initgame() throws SQLException {
        quiz = new Quiz(questions);
        setQuestion(quiz);
        setChoice(quiz.getCurrentQuestion().getChoice());
        setScore(quiz);
        int x = Record.getScore("multichoice",user);
        if(x!=-1){
            max_score_label.setText("max score: " + x);
        }
        answerA.setStyle("-fx-background-color: WHITE");
        answerB.setStyle("-fx-background-color: WHITE");
        answerC.setStyle("-fx-background-color: WHITE");
        answerD.setStyle("-fx-background-color: WHITE");
    }
    public void endgame(ActionEvent event) throws SQLException {
        StringBuilder str = new StringBuilder("End Game!\n Your score: ");
        str.append(quiz.getScore());
        Record.updateMaxScore("multichoice",quiz.getScore(),user);
        newAlert(stage, "Notification", "", str.toString());
        quiz.newQuiz();
        initgame();
    }
    private String createQuestion(Quiz quiz) {
        StringBuilder str = new StringBuilder();
        str.append("Question ");
        str.append(quiz.getQuestionNumber());
        str.append(": ");
        str.append(quiz.getCurrentQuestion().getQuestion());
        return str.toString();
    }

    private void setChoice(String[] choice) {
        answerA.setText(choice[0]);
        answerB.setText(choice[1]);
        answerC.setText(choice[2]);
        answerD.setText(choice[3]);
    }

    private void setQuestion(Quiz quiz) {
        question_lable.setText(createQuestion(quiz));
    }

    private void setScore(Quiz quiz) {
        StringBuilder score = new StringBuilder();
        score.append("score: ");
        score.append(quiz.getScore());
        score_lable.setText(score.toString());
    }

    private void newTurn() throws SQLException {
        if (!quiz.isFinished()) {
            quiz.nextQuestion();
            setQuestion(quiz);
            setChoice(quiz.getCurrentQuestion().getChoice());
            setScore(quiz);
            answerA.setStyle("-fx-background-color: WHITE");
            answerB.setStyle("-fx-background-color: WHITE");
            answerC.setStyle("-fx-background-color: WHITE");
            answerD.setStyle("-fx-background-color: WHITE");
        } else {
            StringBuilder str = new StringBuilder("End Game!\n Your score: ");
            str.append(quiz.getScore());
            Record.updateMaxScore("multichoice",quiz.getScore(),user);
            newAlert(stage, "Notification", "", str.toString());
            quiz.newQuiz();
            initgame();
        }
    }

    /**
     * if select correct Answer button change to green color, and blink in 3 second.
     *
     * @param button button was selected
     */
    private void correctAnswer(Button button) {
        sound_manage.playMedia(mediaCorrecct);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(button.styleProperty(), "-fx-background-color: WHITE")),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(button.styleProperty(), "-fx-background-color: #1eda5f"))
        );
        timeline.setCycleCount(2); // lặp lại 2 lần
        timeline.setOnFinished(event -> {
            try {
                newTurn();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        timeline.play();
    }

    /**
     * if select correct Answer button change to reb color, and blink in 3 second.
     * and blink button correct.
     *
     * @param button the button was selected
     */
    private void wrongAnswer(Button button, Button correct) {
        sound_manage.playMedia(mediaWorng);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(button.styleProperty(), "-fx-background-color: WHITE; -fx-text-fill: BLACK"),
                        new KeyValue(correct.styleProperty(), "-fx-background-color: WHITE; -fx-text-fill: BLACK")),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(button.styleProperty(), "-fx-background-color: RED; -fx-text-fill: WHITE"),
                        new KeyValue(correct.styleProperty(), "-fx-background-color: GREEN; -fx-text-fill: BLACK"))
        );
        timeline.setCycleCount(2); // lặp lại 2 lần
        timeline.setOnFinished(event -> {
            try {
                newTurn();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }); // sau khi nhấp nháy thì lượt chơi mới
        timeline.play();
    }

    /**
     * find the button containing the correct answer.
     *
     * @return button
     */
    private Button getButtonCorrect() {

        if (quiz.getCurrentQuestion().getAnswer().equals(answerA.getText())) {
            return answerA;
        }
        if (quiz.getCurrentQuestion().getAnswer().equals(answerB.getText())) {
            return answerB;
        }
        if (quiz.getCurrentQuestion().getAnswer().equals(answerC.getText())) {
            return answerC;
        }
        return answerD;
    }

    @FXML
    public void checkAnswer(ActionEvent event) {
        Button button = (Button) event.getSource();
        String strAnswer = button.getText();
        if (quiz.guess(strAnswer)) {
            button.setStyle("-fx-background-color: GREEN; -fx-text-fill: WHITE");
            correctAnswer(button);
        } else {
            button.setStyle("-fx-background-color: RED");
            getButtonCorrect().setStyle("-fx-background-color: GREEN; -fx-text-fill: WHITE");
            wrongAnswer(button, getButtonCorrect());
        }
    }
}
