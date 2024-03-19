package dictionary;

import java.util.Scanner;

public class DictionaryManagement {
    public static void insertFromCommandline(){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for(int i=0;i<n;i++){
            String target=scan.next();
            String explain= scan.nextLine();
            Word res= new Word(target,explain);
            Dictionary.addWord(res);
        }
    }

    public static void main(String[] args) {
        
    }
}
