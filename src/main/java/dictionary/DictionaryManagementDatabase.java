package dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DictionaryManagementDatabase {
    private static final String DATABASE_URL = "jdbc:sqlite:src\\Data\\dict_hh.db";
    public static ObservableList prexSearch(String text, boolean av) {
        String NAME_TABLE = null;
        if(av){
            NAME_TABLE = new String("av");
        }
        else{
            NAME_TABLE = new String("va");
        }
        String sql = "SELECT word FROM " + NAME_TABLE + " WHERE word LIKE ?";
        List<String> res = new ArrayList<String>();
        try (Connection conn = DatabaseConnection.connect(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + text + "%");// đối tượng thực thi truy vấn
            ResultSet rs = pstmt.executeQuery(); // lấy kết quả
            while (rs.next()){
                res.add(rs.getString("word"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        ObservableList<String> result = FXCollections.observableArrayList(res);
        return result;
    }

    public static String Search(String text, boolean av) throws SQLException {
        String NAME_TABLE = null;
        if(av){
            NAME_TABLE = new String("av");
        }
        else {
            NAME_TABLE = new String("va");
        }
        String sql = "SELECT word, html FROM "+ NAME_TABLE +" WHERE word = ?";
        Connection conn = DatabaseConnection.connect(DATABASE_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,text);
        ResultSet rs = pstmt.executeQuery();
        String res = new String();
        if(rs.next()){
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
