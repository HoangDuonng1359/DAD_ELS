package dictionary;

import java.util.Scanner;

public class DictionaryManagement {
    static Trie root= Trie.getNewNode();
    public static void insertFromCommandline(){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for(int i=0;i<n;i++){
            String target=scan.next();
            target = target.trim();
            String explain= scan.nextLine();
            explain = explain.trim();
            Word res= new Word(target,explain);
            Trie.insert(root,res);
        }
    }
    public static void insert(String target,String explain){
        target = target.trim();
        explain = explain.trim();
        Word res= new Word(target,explain);
        Trie.insert(root,res);
    }
    public static void insertFromFile(){
        
    }
    public static void showAllWords(){
        Trie.dfs(root);
    }
    public static void prexSearch(String target){
        Trie ans= Trie.getNewNode();
        ans=Trie.search(root,target);
        if(ans==null){
            System.out.print("No Found");
        }
        else {
            Trie.dfs(ans);
        }
    }
    public static void Search(String target){
        Trie ans= Trie.getNewNode();
        ans=Trie.search(root,target);
        if(ans==null){
            System.out.print("No Found");
        }
        else {
            System.out.print(ans.res.getExplain());
        }
    }
    public static void remove(String target){
        Trie.remove(root,target);
    }
}
