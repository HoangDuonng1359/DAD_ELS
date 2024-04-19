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

    public static ObservableList prexSearch(String text, boolean av) {
        String NAME_TABLE = null;
        if (av) {
            NAME_TABLE = new String("av");
        } else {
            NAME_TABLE = new String("va");
        }
        String sql = "SELECT word FROM " + NAME_TABLE + " WHERE word LIKE ?";
        List<String> res = new ArrayList<String>();
        try (Connection conn = DatabaseConnection.connect(dict_hh_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + text + "%");// đối tượng thực thi truy vấn
            ResultSet rs = pstmt.executeQuery(); // lấy kết quả
            while (rs.next()) {
                res.add(rs.getString("word"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        ObservableList<String> result = FXCollections.observableArrayList(res);
        return result;
    }

    public static String Search(String text, boolean av) throws SQLException {

        String NAME_TABLE = null;
        if (av) {
            NAME_TABLE = new String("av");
        } else {
            NAME_TABLE = new String("va");
        }
        // nếu mà bookmark có từ này -> lấy type
        // -> if "-" then no found
        // -> if "+" or "#" then return explain
        // else bookmark không có -> search av
        String sql = new String();
        Connection conn1 = DatabaseConnection.connect(DATABASE_URL);
        sql = "SELECT type, target , explain FROM bookmark WHERE user_id = ? AND target =?";
        PreparedStatement pr = conn1.prepareStatement(sql);
        pr.setInt(1, baseFormController.user.getId());
        pr.setString(2, text);
        ResultSet resultSet = pr.executeQuery();
        if (resultSet.next()) {
            String type = resultSet.getString("type");
            if (type.equals("-")){
                conn1.close();
                return "you have deleted this word";
            }

            if (type.equals("+") || type.equals("#")){
                String res = resultSet.getString("explain");
                conn1.close();
                return res;
            }

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

    public static void main(String[] args) {
    }
}
