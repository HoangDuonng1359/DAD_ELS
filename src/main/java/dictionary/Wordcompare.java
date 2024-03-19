package dictionary;

import java.util.Comparator;
class Wordcompare implements Comparator<Word> {
    public int compare(Word a,Word b){
        return a.getTarget().compareTo(b.getTarget());
    }
}
