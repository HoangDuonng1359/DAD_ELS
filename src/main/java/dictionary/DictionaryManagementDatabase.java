package dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.els.baseFormController;
import user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DictionaryManagementDatabase {
    public static final String dict_hh_URL = "jdbc:sqlite:src\\Data\\dict_hh.db";
    public static final String DATABASE_URL = "jdbc:sqlite:src\\Data\\database.db";

    public static ObservableList prexSearch(String text, String MODE) throws SQLException {
        // long startTime = System.nanoTime();
        String NAME_TABLE = MODE;
        List<String> res = new ArrayList<String>();
        Connection conn2 = DatabaseConnection.connect(DATABASE_URL);
        String sql = new String();
        sql = "SELECT type, target FROM bookmark WHERE target LIKE ? AND type = '+' AND mode = ? ORDER BY (LENGTH(target) - LENGTH(?)) ASC";
        PreparedStatement pr = conn2.prepareStatement(sql);
        pr.setString(1, text + "%");
        pr.setString(2, MODE);
        pr.setString(3, text);
        ResultSet rs1 = pr.executeQuery(); // lấy kết quả
        while (rs1.next()) {
            res.add(rs1.getString("target"));
        }
        conn2.close();
        sql = "SELECT word FROM " + NAME_TABLE + " WHERE  word LIKE ? ORDER BY (LENGTH(word) - LENGTH(?)) ASC";
        //SELECT * FROM av WHERE text LIKE ? ORDER BY (CASE WHEN text = ? THEN 1 ELSE 0 END) DESC"

        Connection conn = DatabaseConnection.connect(dict_hh_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, text + "%");// đối tượng thực thi truy vấn
        pstmt.setString(2, text);
        ResultSet rs = pstmt.executeQuery(); // lấy kết quả
        while (rs.next()) {
            res.add(rs.getString("word"));
        }
        conn.close();
        ObservableList<String> result = FXCollections.observableArrayList(res);
        //  long endTime = System.nanoTime(); // Thời điểm kết thúc thực thi truy vấn
        // long duration = (endTime - startTime) / 1000000; // Chuyển đổi sang mili giây
        // System.out.println("Thời gian thực thi truy vấn: " + duration + " mili giây");
        return result;
    }

    public static String Search(String text, String MODE) throws SQLException {

        String NAME_TABLE = MODE;
        // nếu mà bookmark có từ này -> lấy type
        // -> if "-" then no found
        // -> if "+" or "#" then return explain
        // else bookmark không có -> search av
        String sql;
        Connection conn1 = DatabaseConnection.connect(DATABASE_URL);
        sql = "SELECT type, target , explain FROM bookmark WHERE user_id = ? AND target =? AND mode = ?";
        PreparedStatement pr = conn1.prepareStatement(sql);
        pr.setInt(1, baseFormController.user.getId());
        pr.setString(2, text);
        pr.setString(3, MODE);
        ResultSet resultSet = pr.executeQuery();
        if (resultSet.next()) {
            String type = resultSet.getString("type");
            if (type.equals("-")) {
                conn1.close();
                return "you have deleted this word";
            }

            if (type.equals("+") || type.equals("#")) {
                String res = resultSet.getString("explain");
                conn1.close();
                return res;
            }

        }
        else {
            conn1.close();
        }
        sql = "SELECT word, html FROM " + NAME_TABLE + " WHERE word = ?";
        Connection conn = DatabaseConnection.connect(dict_hh_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, text);
        ResultSet rs = pstmt.executeQuery();
        String res = new String();
        if (rs.next()) {
            res = rs.getString("html");
        } else {
            return "NO FOUND";
        }
        conn.close();
        return res;
    }

    public static int numberChange(String type) throws SQLException {
        Connection conn = DatabaseConnection.connect(DATABASE_URL);
        String sql = "SELECT COUNT(*) FROM bookmark WHERE user_id=? and type = ?";
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setInt(1, baseFormController.user.getId());
        pr.setString(2, type);
        ResultSet rs = pr.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        conn.close();
        return count;
    }

    public static ObservableList  show_all_word(String MODE) throws SQLException {
//        long startTime = System.nanoTime();
        String NAME_TABLE = MODE;
        List<String> res = new ArrayList<String>();
        Connection conn2 = DatabaseConnection.connect(DATABASE_URL);
        String sql = new String();
        sql = "SELECT target FROM bookmark WHERE type = '+' AND mode = ?";
        PreparedStatement pr = conn2.prepareStatement(sql);
        pr.setString(1, MODE);
        ResultSet rs1 = pr.executeQuery(); // lấy kết quả
        while (rs1.next()) {
            res.add(rs1.getString("target"));
        }
        conn2.close();
        sql = "SELECT word FROM " + NAME_TABLE;
        //SELECT * FROM av WHERE text LIKE ? ORDER BY (CASE WHEN text = ? THEN 1 ELSE 0 END) DESC"

        Connection conn = DatabaseConnection.connect(dict_hh_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery(); // lấy kết quả
        while (rs.next()) {
            res.add(rs.getString("word"));
        }
        conn.close();
        ObservableList<String> result = FXCollections.observableArrayList(res);
//        long endTime = System.nanoTime(); // Thời điểm kết thúc thực thi truy vấn
//        long duration = (endTime - startTime) / 1000000; // Chuyển đổi sang mili giây
//        System.out.println("Thời gian thực thi truy vấn: " + duration + " mili giây");
//        System.out.println(result.size() + " kết quả");
        return result;
    }
}
