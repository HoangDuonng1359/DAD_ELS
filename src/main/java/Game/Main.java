package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Question> questions = new ArrayList<Question>();
        String[] choice = {"A","B","C","D"};
        Question question = new Question("i ... duo",choice,"A");
        Question question2 = new Question("i ... duo",choice,"B");
        questions.add(question);
        questions.add(question2);
        Quiz quiz = new Quiz(questions);
        Scanner scanner = new Scanner(System.in);
        System.out.println("hello");
        while (!quiz.isFinished()){
            Question ques = quiz.getCurrentQuestion();
            System.out.println("câu hỏi số: "+ (quiz.getQuestionIndex()+1));
            System.out.println(ques.getQuestion());
            String ans = scanner.nextLine();
            quiz.guess(ans);
            System.out.println("điểm của bạn là" + quiz.getScore());
        }
    }
}
