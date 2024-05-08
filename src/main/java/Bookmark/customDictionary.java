package Bookmark;

import dictionary.DatabaseConnection;
import dictionary.DictionaryManagementDatabase;
import org.example.els.baseFormController;
import user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.els.SceneManage.newAlert;

public class customDictionary {
    public static boolean targetExists(Connection connection, String targetValue, User user, String MODE) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String selectQuery = "SELECT COUNT(*) FROM custom_dictionary WHERE target = ? AND user_id = ? AND mode= ?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, targetValue);
            preparedStatement.setInt(2, user.getId());
            preparedStatement.setString(3, MODE);
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

    public static void addWord(String target, String explain, User user, String MODE) {
        try {
            String se = DictionaryManagementDatabase.Search(target, MODE);
            if (!se.equals("NO FOUND") && !se.equals("you have deleted this word")) {
                //return 0;
                newAlert("AddWord", "", "Từ đã tồn tại");
            } else if (!target.isEmpty() && !explain.isEmpty()) {
                //dictionaryManagement.insert(e, v);
                //bookwriter.append("+ "+e+" "+v+"\n");
                Connection connection = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
                String insertQuery = "INSERT INTO custom_dictionary (target, type, explain,user_id,mode) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setString(1, target);
                insertStatement.setString(2, "+");
                insertStatement.setString(3, explain);
                insertStatement.setInt(4, user.getId());
                insertStatement.setString(5, MODE);
                insertStatement.executeUpdate();
                newAlert("AddWord", "", "Thêm từ thành công");
            } else {
                newAlert("AddWord", "", "Vui lòng nhập đủ thông tin từ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void editWord(String target, String explain, User user, String MODE) {
        try {
            if (!target.isEmpty() && !explain.isEmpty()) {
                //dictionaryManagement.insert(e, v);
                //bookwriter.append("+ "+e+" "+v+"\n");
                Connection connection = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
                if (customDictionary.targetExists(connection, target, user, MODE)) {
                    String updateQuery = "UPDATE custom_dictionary SET type = ?, explain = ? WHERE target = ? AND user_id = ? AND mode = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setString(1, "#");
                    updateStatement.setString(2, explain);
                    updateStatement.setString(3, target);
                    updateStatement.setInt(4, user.getId());
                    updateStatement.setString(5, MODE);
                    updateStatement.executeUpdate();
                    newAlert("Edit word", "", "Đã sửa thành công");
                } else {
                    String insertQuery = "INSERT INTO custom_dictionary (target, type, explain,user_id,mode) VALUES (?, ?, ?,?,?)";
                    PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                    insertStatement.setString(1, target);
                    insertStatement.setString(2, "#");
                    insertStatement.setString(3, explain);
                    insertStatement.setInt(4, user.getId());
                    insertStatement.setString(5, MODE);
                    insertStatement.executeUpdate();
                    newAlert("Edit word", "", "Đã sửa thành công");
                }
            } else {
                newAlert("editWord", "", "Vui lòng nhập đủ thông tin từ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteWord(String word, String MODE) {
        try {
            if (word == null) {
                newAlert("Delete Word", "", "Vui lòng nhập từ");
            }
            //else if(dictionaryManagement.Search(s).equals("NO FOUND")){
            else if (DictionaryManagementDatabase.Search(word, MODE).equals("NO FOUND")) {
                newAlert("Delete Word", "", "Không tìm thấy từ");
            } else {
                Connection conn = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
                if (customDictionary.targetExists(conn, word, baseFormController.user, MODE)) {
                    String updateQuery = "UPDATE custom_dictionary SET type = ? WHERE target = ? AND user_id = ? AND mode = ?";
                    PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
                    updateStatement.setString(1, "-");
                    updateStatement.setString(2, word);
                    updateStatement.setInt(3, baseFormController.user.getId());
                    updateStatement.setString(4, MODE);
                    updateStatement.executeUpdate();
                    newAlert("Delete word", "", "Xóa từ thành công");
                } else {
                    //dictionaryManagement.remove(s);
                    newAlert("Delete Word", "", "Xóa từ thành công");
                    //bookwriter.append("- "+s+"\n");
                    String sql = "INSERT INTO custom_dictionary (user_id,target,type, mode) VALUES (?,?,?,?)";
                    PreparedStatement pr = conn.prepareStatement(sql);
                    pr.setInt(1, baseFormController.user.getId());
                    pr.setString(2, word);
                    pr.setString(3, "-");
                    pr.setString(4, MODE);
                    pr.executeUpdate();
                    conn.close();
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
