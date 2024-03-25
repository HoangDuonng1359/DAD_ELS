package Game;

public class Question {
    private String question;
    private String[] choice;
    private String answer;
    public Question(String ques, String[] choice , String answer){
        this.question = ques;
        this.answer = answer;
        this.choice = choice;
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String ques) {
        this.question = ques;
    }

    public String[] getChoice() {
        return choice;
    }

    public void setChoice(String[] choice) {
        this.choice = choice;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public boolean checkAnswer(String answer){
        return this.answer.equals(answer);
    }
}
