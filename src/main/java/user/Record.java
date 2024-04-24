package user;

import dictionary.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Record {
    public static int getScore(String gameColumn, User user) {
        int result = -1; // Khởi tạo kết quả mặc định là -1
        String sql = "SELECT " + gameColumn + " FROM max_score_game WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
             PreparedStatement pr = conn.prepareStatement(sql)) {
            pr.setInt(1, user.getId());
            try (ResultSet rs = pr.executeQuery()) {
                if (rs.next()) {
                    result = rs.getInt(gameColumn);
                }
            } finally {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void updateMaxScore(String gameColumn, int score, User user) throws SQLException {
        Connection conn = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
        if (getScore(gameColumn, user) != -1) {
            String sql = "UPDATE max_score_game SET " + gameColumn
                    + " = ? WHERE user_id = ? AND (" + gameColumn + " < ? OR " + gameColumn + " IS NULL)";
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, score);
            pr.setInt(2, user.getId());
            pr.setInt(3, score);
            pr.executeUpdate();
            conn.close();
        } else {
            String sql = "INSERT INTO max_score_game (multichoice , user_id) values (?,?)";
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, score);
            pr.setInt(2, user.getId());
            pr.executeUpdate();
            conn.close();
        }
    }
}
