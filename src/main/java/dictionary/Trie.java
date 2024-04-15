package dictionary;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Trie {
    boolean isEndOfWord;
    Word res;
    TreeMap<Character,Trie>map;
    static Trie getNewNode() {
        Trie node = new Trie();
        node.isEndOfWord= false;
        node.res = new Word();
        node.map = new TreeMap<Character,Trie>();
        return node;
    }
    static void insert(Trie root,Word w){
        Trie temp = root;
        String save=w.getTarget().trim().toLowerCase();
        for(int i=0;i<save.length();i++){
            char x= save.charAt(i);
            if(temp.map.isEmpty()||!temp.map.containsKey(x)){
                temp.map.put(x,getNewNode());
            }
            temp= temp.map.get(x);
        }
        temp.isEndOfWord = true;
        temp.res=w;
    }
    static Trie search(Trie root,String str){
        if(root==null){
            return null;
        }
        str= str.trim().toLowerCase();
        Trie temp=root;
        for(int i=0;i<str.length();i++){
            if(temp.map.isEmpty()||!temp.map.containsKey((str.charAt(i)))){
                return null;
            }
            temp = temp.map.get(str.charAt(i));
        }
        if(!temp.isEndOfWord){
            return null;
        }
        return temp;
    }
    static void remove(Trie root,String str){
        if(root==null){
            return;
        }
        Trie temp=root;
        Trie parrent= getNewNode();
        str=str.trim().toLowerCase();
        for(int i=0;i<str.length();i++){
            if(temp.map.isEmpty()||!temp.map.containsKey((str.charAt(i)))){
                return;
            }
            parrent=temp;
            temp = temp.map.get(str.charAt(i));
        }
        temp.isEndOfWord=false;
        temp.res = new Word();
        if(temp.map.isEmpty()){
            parrent.map.remove(str.charAt(str.length()-1));
        }
    }
    static void dfs(Trie root, ArrayList<String> ans){
        if(root.isEndOfWord==true){
            ans.add(root.res.getTarget());
        }
        if(root.map.isEmpty())return;
        for(Map.Entry<Character,Trie> entry:root.map.entrySet()){
            dfs(entry.getValue(),ans);
        }
    }
}
