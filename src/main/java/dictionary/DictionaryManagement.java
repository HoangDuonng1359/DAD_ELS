package dictionary;

import java.util.Scanner;

public class DictionaryManagement {
    static Trie root= Trie.getNewNode();
    public static void insertFromCommandline(){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for(int i=0;i<n;i++){
            String target=scan.next();
            String explain= scan.nextLine();
            Word res= new Word(target,explain);
            Trie.insert(root,res);
        }
    }
    public static void showAllWords(){
        Trie.dfs(root);
    }

    public static void main(String[] args) {
        insertFromCommandline();
        showAllWords();
    }
}
