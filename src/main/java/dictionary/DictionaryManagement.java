package dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
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
    public static ObservableList showAllWords(){

        ArrayList<String> res= new ArrayList<>();
        Trie.dfs(root,res);
        ObservableList<String> r= FXCollections.observableArrayList(res);
        return r;
    }
    public static ObservableList prexSearch(String target){
        Trie ans= Trie.getNewNode();
        ans=Trie.search(root,target);
        if(ans==null){
            return null;
        }
        ArrayList<String> res= new ArrayList<>();
        Trie.dfs(ans,res);
        ObservableList<String> r= FXCollections.observableArrayList(res);
        return r;
    }
    public static String Search(String target){
        Trie ans= Trie.getNewNode();
        ans=Trie.search(root,target);
        if(ans==null){
            return new String("NO FOUND");
        }
        else {
           return ans.res.getExplain();
        }
    }
    public static void remove(String target){
        Trie.remove(root,target);
    }
}
