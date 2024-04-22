package Bookmark;

import dictionary.DatabaseConnection;
import dictionary.DictionaryManagementDatabase;
import javafx.event.ActionEvent;
import user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.els.SceneManage.newAlert;

public class bookmarkmanagement {
    public static boolean targetExists(Connection connection, String targetValue, User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String selectQuery = "SELECT COUNT(*) FROM bookmark WHERE target = ? AND user_id = ?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, targetValue);
            preparedStatement.setInt(2, user.getId());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }

        } finally {
            if (resultSet != null)
                resultSet.close();
            if (preparedStatement != null)
                preparedStatement.close();
        }
        return false;
    }

    public static void addWord(String target, String explain, User user) {
        try {
            String se = DictionaryManagementDatabase.Search(target, true);
            if (!se.equals("NO FOUND") && !se.equals("you have deleted this word")) {
                //return 0;
                newAlert("AddWord", "", "Từ đã tồn tại");
            } else if (!target.isEmpty() && !explain.isEmpty()) {
                //dictionaryManagement.insert(e, v);
                //bookwriter.append("+ "+e+" "+v+"\n");
                Connection connection = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
//                if (bookmarkmanagement.targetExists(connection, target,user)) {
//                    String updateQuery = "UPDATE bookmark SET type = ?, explain = ? WHERE target = ? AND user_id = ?";
//                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
//                    updateStatement.setString(1, "+");
//                    updateStatement.setString(2, explain);
//                    updateStatement.setString(3, target);
//                    updateStatement.setInt(4,user.getId());
//                    updateStatement.executeUpdate();
//                } else {
                String insertQuery = "INSERT INTO bookmark (target, type, explain,user_id) VALUES (?, ?, ?,?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setString(1, target);
                insertStatement.setString(2, "+");
                insertStatement.setString(3, explain);
                insertStatement.setInt(4, user.getId());
                insertStatement.executeUpdate();
                newAlert("AddWord", "", "Thêm từ thành công");
            } else {
                newAlert("AddWord", "", "Vui lòng nhập đủ thông tin từ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void editWord(String target, String explain, User user) {
        try {
            String se = DictionaryManagementDatabase.Search(target, true);
            if (!target.isEmpty() && !explain.isEmpty()) {
                //dictionaryManagement.insert(e, v);
                //bookwriter.append("+ "+e+" "+v+"\n");
                Connection connection = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
                if (bookmarkmanagement.targetExists(connection, target, user)) {
                    String updateQuery = "UPDATE bookmark SET type = ?, explain = ? WHERE target = ? AND user_id = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setString(1, "#");
                    updateStatement.setString(2, explain);
                    updateStatement.setString(3, target);
                    updateStatement.setInt(4, user.getId());
                    updateStatement.executeUpdate();
                    newAlert("Edit word","","Đã sửa thành công");
                } else {
                    String insertQuery = "INSERT INTO bookmark (target, type, explain,user_id) VALUES (?, ?, ?,?)";
                    PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                    insertStatement.setString(1, target);
                    insertStatement.setString(2, "#");
                    insertStatement.setString(3, explain);
                    insertStatement.setInt(4, user.getId());
                    insertStatement.executeUpdate();
                    newAlert("Edit word","","Đã sửa thành công");
                }
            } else {
                newAlert("editWord", "", "Vui lòng nhập đủ thông tin từ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
