package dictionary;


public class DictionaryCommandLine {
    public static void showAllWords(){
        int size= Dictionary.getSize();

        for(int i=0;i<size;i++) {
            Word res= Dictionary.getWord(i);
            System.out.println(res.getTarget()+" "+res.getExplain());
        }
    }
}
