package Game.FlashCard;

import dictionary.Trie;
import dictionary.Word;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class RecentW {
    private static List<String> history = new ArrayList<String>();
    private static HashSet<String> check= new HashSet<String>();
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void add(String s) {
        try{
            if(check.isEmpty()||!check.contains(s)){
                history.add(s);
                check.add(s);
                out.append(s+"\n");
            }
        }
        catch(IOException e){
            System.out.println("IOException");
        }
    }
    public static void init(){
        try{
            in = new BufferedReader(new FileReader("src/Data/RecentList.txt"));
            out = new BufferedWriter(new FileWriter("src/Data/RecentList.txt",true));
            String str=new String();
            while ((str = in.readLine()) != null) {
                history.add(str);
                check.add(str);
            }
            out.write("1");
        }
    catch (FileNotFoundException e) {
        System.out.println("File not found");
    }catch(IOException e){
        System.out.println("IOException");
    }

    }
    private static int randomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    public static void closefile() {
        try{
            out.close();
            in.close();
        }
        catch(IOException e){
            System.out.println("IOException");
        }
    }
    public static String getWord(){
        return history.get(randomNum(0, history.size()-1));
    }
}
