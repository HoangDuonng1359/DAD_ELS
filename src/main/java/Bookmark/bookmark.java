package Bookmark;

import dictionary.DatabaseConnection;
import dictionary.DictionaryManagementDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.els.baseFormController;
import user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.els.SceneManage.newAlert;

public class bookmark {
    public static final String DATABASE_URL = "jdbc:sqlite:src\\Data\\database.db";

    public static void addWord(String target, String explain, User user, String MODE) {
        try {
            String se = Search(target, MODE, user.getId());
            if (!se.equals("NO FOUND")) {
                newAlert("AddWord", "", "Từ đã tồn tại");
            } else if (!target.isEmpty() && !explain.isEmpty()) {
                Connection connection = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
                String insertQuery = "INSERT INTO bookmark (target, explain,user_id,mode) VALUES (?, ?, ?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setString(1, target);
                insertStatement.setString(2, explain);
                insertStatement.setInt(3, user.getId());
                insertStatement.setString(4, MODE);
                insertStatement.executeUpdate();
                newAlert("AddWord", "", "Thêm từ vào bookmark thành công");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteWord(String word, String MODE, User user) {
        try {
            if (word == null) {
                newAlert("Delete Word", "", "Vui lòng chọn từ");
            } else {
                Connection conn = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
                String sql = "DELETE FROM bookmark WHERE target = ? AND mode = ? AND user_id = ?";
                PreparedStatement pr = conn.prepareStatement(sql);
                pr.setString(1, word);
                pr.setString(2, MODE);
                pr.setInt(3, user.getId());
                pr.executeUpdate();
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList prexSearch(String text, String MODE, User user) throws SQLException {
        // long startTime = System.nanoTime();
        List<String> res = new ArrayList<String>();
        Connection conn2 = DatabaseConnection.connect(DATABASE_URL);
        String sql;
        sql = "SELECT target FROM bookmark WHERE target LIKE ? AND mode = ? AND user_id = ? ORDER BY (LENGTH(target) - LENGTH(?)) ASC";
        PreparedStatement pr = conn2.prepareStatement(sql);
        pr.setString(1, text + "%");
        pr.setString(2, MODE);
        pr.setInt(3, user.getId());
        pr.setString(4, text);
        ResultSet rs1 = pr.executeQuery(); // lấy kết quả
        while (rs1.next()) {
            res.add(rs1.getString("target"));
        }
        conn2.close();
        ObservableList<String> result = FXCollections.observableArrayList(res);
        //  long endTime = System.nanoTime(); // Thời điểm kết thúc thực thi truy vấn
        // long duration = (endTime - startTime) / 1000000; // Chuyển đổi sang mili giây
        // System.out.println("Thời gian thực thi truy vấn: " + duration + " mili giây");
        return result;
    }

    public static String Search(String target, String MODE, int id) throws SQLException {
        Connection conn = DatabaseConnection.connect(DATABASE_URL);
        String sql = "SELECT * FROM bookmark where user_id = ? and target = ? and mode = ?";
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setInt(1, id);
        pr.setString(2, target);
        pr.setString(3, MODE);
        ResultSet rs = pr.executeQuery();
        if (rs.next()) {
            return rs.getString("explain");
        } else return "NO FOUND";
    }

    public static ObservableList show_all_word(String MODE) throws SQLException {
//        long startTime = System.nanoTime();
        String NAME_TABLE = MODE;
        List<String> res = new ArrayList<String>();
        Connection conn2 = DatabaseConnection.connect(DATABASE_URL);
        String sql = new String();
        sql = "SELECT target FROM bookmark WHERE mode = ? AND user_id = ?";
        PreparedStatement pr = conn2.prepareStatement(sql);
        pr.setString(1, MODE);
        pr.setInt(2, baseFormController.user.getId());
        ResultSet rs1 = pr.executeQuery(); // lấy kết quả
        while (rs1.next()) {
            res.add(rs1.getString("target"));
        }
        conn2.close();
        ObservableList<String> result = FXCollections.observableArrayList(res);
//        long endTime = System.nanoTime(); // Thời điểm kết thúc thực thi truy vấn
//        long duration = (endTime - startTime) / 1000000; // Chuyển đổi sang mili giây
//        System.out.println("Thời gian thực thi truy vấn: " + duration + " mili giây");
//        System.out.println(result.size() + " kết quả");
        return result;
    }

    public static boolean reset(int user_id) throws SQLException {
        Connection conn = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
        String sql = "DELETE FROM bookmark WHERE user_id = ?";
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setInt(1, user_id);
        int rowsAffected = pr.executeUpdate();
        pr.close();
        conn.close();
        return rowsAffected > 0;
    }
}
