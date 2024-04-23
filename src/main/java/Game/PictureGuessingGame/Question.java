package Game.PictureGuessingGame;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private Image image;
    private String ques;

    private String result;

    public Question(Image image, String result) {
        this.image = image;
        this.result = result;
    }

    public Question() {

    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getQues() {
        createques(result);
        return ques;
    }

    private void createques(String input) {
        char[] characters = input.toCharArray();
        List<Character> charList = new ArrayList<>();
        for (char c : characters) {
            charList.add(c);
            charList.add('/');
        }
        charList.remove(charList.size() - 1); // Loại bỏ dấu '/' cuối cùng
        Collections.shuffle(charList); // Xáo trộn các kí tự
        StringBuilder result = new StringBuilder();
        for (char c : charList) {
            result.append(c);
        }
        this.ques = result.toString();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean checkAnswer(String answer) {
        return answer.equals(result);
    }
}
