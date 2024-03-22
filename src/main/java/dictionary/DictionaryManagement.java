package dictionary;

import java.io.*;
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
        try{
            BufferedReader in= new BufferedReader(new FileReader("src/Data/E_V.txt"));
            String str=new String();
            while ((str = in.readLine()) != null) {
                String[] ans= str.split("<",2);
                Word res = new Word(ans[0],"<"+ans[1]);
                Trie.insert(root,res);
            }
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }catch(IOException e){
            System.out.println("IOException");
        }

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
