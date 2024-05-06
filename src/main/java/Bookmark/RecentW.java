package Bookmark;

import dictionary.DatabaseConnection;
import dictionary.DictionaryManagementDatabase;
import dictionary.Trie;
import dictionary.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.els.SceneManage;
import org.example.els.baseFormController;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RecentW {
    private static List<String> history;
    private static HashSet<String> check = new HashSet<String>();
    private static BufferedReader in;
    private static BufferedWriter out;
    private static HashMap<String, String> historyDB = new HashMap<>();

    public static void add(String s) {
        try {
            if (check.isEmpty() || !check.contains(s)) {
                history.add(s);
                check.add(s);
                out.append(s + "\n");
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public static ObservableList getHistory(String MODE) throws SQLException {
        historyDB.clear();
        Connection conn = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
        String sql = "SELECT user_id, target, explain FROM RecentList WHERE user_id = ? AND mode = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, baseFormController.user.getId());
        ps.setString(2, MODE);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            historyDB.put(rs.getString("target"), rs.getString("explain"));
        }
        conn.close();
        return FXCollections.observableArrayList(new ArrayList<>(historyDB.keySet()));
    }

    public static void addDB(String s, String MODE) throws SQLException {
        String ex = DictionaryManagementDatabase.Search(s, MODE);
        Connection conn = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
        PreparedStatement ps = null;
        if (!ex.equals("NO FOUND") && !ex.equals("you have deleted this word")) {
            String sql = "INSERT INTO RecentList (user_id, target, explain,mode) SELECT ?, ?, ?,? WHERE NOT EXISTS (SELECT 1 FROM RecentList WHERE target = ? AND user_id = ? AND mode = ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, baseFormController.user.getId());
            ps.setString(2, s);
            ps.setString(3, ex);
            ps.setString(4, MODE);
            ps.setString(5, s);
            ps.setInt(6, baseFormController.user.getId());
            ps.setString(7, MODE);
            ps.executeUpdate();
        }
        if (conn != null) {
            conn.close();
        }
    }


    public static void init() {
        try {
            in = new BufferedReader(new FileReader("src/Data/RecentList.txt"));
            out = new BufferedWriter(new FileWriter("src/Data/RecentList.txt", true));
            String str = new String();
            while ((str = in.readLine()) != null) {
                history.add(str);
                check.add(str);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public static void initDB() throws SQLException {
        Connection conn = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
        String sql = "SELECT user_id, target, explain FROM RecentList WHERE user_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, baseFormController.user.getId());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            historyDB.put(rs.getString("target"), rs.getString("explain"));
        }
        conn.close();
        history = new ArrayList<>(historyDB.keySet());
    }

    private static int randomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static void closefile() {
        try {
            out.close();
            in.close();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public static String getWord() {
        return history.get(randomNum(0, history.size()));
    }

    public static String getExplain(String s) {
        return historyDB.get(s);
    }
}
