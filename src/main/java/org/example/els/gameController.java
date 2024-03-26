package org.example.els;

import Game.Question;
import Game.Quiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class gameController extends baseFormController{
    protected static Stage stage;
    protected static Scene scene;
    protected static Parent root;
    @FXML
    private Label question_lable;
    @FXML
    private Label score_lable;
    @FXML
    private Button startButton;
    @FXML
    private Button answerA;
    @FXML
    private Button answerB;
    @FXML
    private Button answerC;
    @FXML
    private Button answerD;
    @FXML
    public void openFormMultichoice(ActionEvent event){
        try {
            SceneManage.showScene(root,stage,scene,event,"multiChoiceGame.fxml");
        } catch (IOException e) {
            System.out.println("lỗi không mở được form");
        }
    }
    private String createQuestion(Quiz quiz){
        StringBuilder str = new StringBuilder();
        str.append("Question: ");
        str.append(quiz.getQuestionIndex() + 1);
        str.append(": ");
        str.append(quiz.getCurrentQuestion().getQuestion());
        String[] choice = quiz.getCurrentQuestion().getChoice();
        for(String cho:choice){
            str.append("\n");
            str.append(cho);
        }
        return str.toString();
    }
    public void nextQuetion(ActionEvent event){

    }
    @FXML
    public void startGame(ActionEvent event){

    }
}
