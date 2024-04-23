package Game.MultiChoice;

import java.util.*;

public class Quiz {
    private List<Question> questionList;
    private int score;
    private int questionIndex;
    private List<Integer> listRandom;

    public Quiz(List<Question> questionList) {
        this.questionList = questionList;
        this.score = 0;
        listRandom = new ArrayList<Integer>();
        for (int i = 0; i < getLengthQuestionList(); i++) {
            listRandom.add(i);
        }
        this.questionIndex = random();

    }

    private int random() {
        Random ran = new Random();
        int index = listRandom.get(ran.nextInt(listRandom.size()));
        listRandom.remove(Integer.valueOf(index));
        return index;
    }
    public int getQuestionNumber(){
        return questionList.size() - listRandom.size();
    }
    public void addQuestion(Question question) {
        questionList.addLast(question);
        listRandom.add(getLengthQuestionList());
    }

    public Question getCurrentQuestion() {
        return questionList.get(questionIndex);
    }

    public int getScore() {
        return score;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public void newQuiz() {
        listRandom.clear();
        for (int i = 0; i < getLengthQuestionList(); i++) {
            listRandom.add(i);
        }
        this.questionIndex = random();
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

    public void nextQuestion() {
        questionIndex = random();
    }

    public boolean isFinished() {
        //return this.questionIndex >= (getLengthQuestionList() - 1);
        return listRandom.isEmpty();
    }
}
