package Game.PictureGuessingGame;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

        }
        Random random = new Random();
        for(int i = charList.size() - 1; i > 0 ; i--) {
            int j = random.nextInt(i + 1);
            char temp = charList.get(i);
            charList.set(i, charList.get(j));
            charList.set(j, temp);
        }

        StringBuilder result = new StringBuilder();
        for (char s : charList) {
            result.append(s);
            result.append('/');
        }
        result.deleteCharAt(result.length() - 1); // loại bỏ dấu '/' cuối cùng
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
