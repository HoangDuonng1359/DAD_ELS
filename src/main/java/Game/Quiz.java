package Game;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private List<Question> questionList;
    private int score;
    private int questionIndex;

    public Quiz() {
        this.questionList = new ArrayList<Question>();
        this.score = 0;
        this.questionIndex = 0;
    }

    public Quiz(List<Question> questionList) {
        this.questionList = questionList;
        this.score = 0;
        this.questionIndex = 0;
    }

    public void addQuestion(Question question) {
        questionList.addLast(question);
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public Question getCurrentQuestion() {
        return questionList.get(questionIndex);
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public int getScore() {
        return score;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }
    public void newQuiz(){
        this.questionIndex = 0;
        this.score = 0;
    }
    public int getLengthQuestionList() {
        return questionList.size();
    }

    public boolean guess(String answer) {
        boolean ans = true;
        if (getCurrentQuestion().checkAnswer(answer)) {
            this.score += 1;
        } else {
            ans = false;
        }
        return ans;
    }
    public void nextQuestion(){
        questionIndex += 1;
    }
    public boolean isFinished() {
        return this.questionIndex >= (getLengthQuestionList()-1);
    }
}
