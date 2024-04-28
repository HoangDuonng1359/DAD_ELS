package Bookmark;

import dictionary.DatabaseConnection;
import dictionary.DictionaryManagementDatabase;
import javafx.event.ActionEvent;
import org.example.els.baseFormController;
import user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.els.SceneManage.newAlert;
import static org.example.els.baseFormController.getmode;

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

    public static void addWord(String target, String explain, User user,boolean av) {
        try {
            String se = DictionaryManagementDatabase.Search(target, av);
            if (!se.equals("NO FOUND") && !se.equals("you have deleted this word")) {
                //return 0;
                newAlert("AddWord", "", "Từ đã tồn tại");
            } else if (!target.isEmpty() && !explain.isEmpty()) {
                //dictionaryManagement.insert(e, v);
                //bookwriter.append("+ "+e+" "+v+"\n");
                Connection connection = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
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

    public static void editWord(String target, String explain, User user,boolean av) {
        try {
            String se = DictionaryManagementDatabase.Search(target, av);
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
    public static void deleteWord(String word , boolean av) {
        try {
            if(word==null){
                newAlert("Delete Word","","Vui lòng nhập từ");
            }
            //else if(dictionaryManagement.Search(s).equals("NO FOUND")){
            else if(DictionaryManagementDatabase.Search(word,av).equals("NO FOUND")){
                newAlert("Delete Word","","Không tìm thấy từ");
            }
            else {
                //dictionaryManagement.remove(s);
                newAlert("Delete Word","","Xóa từ thành công");
                //bookwriter.append("- "+s+"\n");
                Connection conn = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
                String sql = "INSERT INTO bookmark (user_id,target,type) VALUES (?,?,?)";
                PreparedStatement pr = conn.prepareStatement(sql);
                pr.setInt(1, baseFormController.user.getId());
                pr.setString(2,word);
                pr.setString(3,"-");
                pr.executeUpdate();
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
