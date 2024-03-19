package dictionary;

import java.util.ArrayList;

public class Dictionary {
    private static ArrayList<Word> words =new ArrayList<Word>();
    public static void addWord(Word word){
        words.add(word);
    }
    public static Word getWord(int loc) {
        return words.get(loc);
    }
    public static int getSize(){
        return words.size();
    }
    public static void sort(){
        words.sort(new Wordcompare());
    }
}
