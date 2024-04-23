package dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connect(String DATABASE_URL){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            //System.out.println("Kết nối đến cơ sở dữ liệu thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi khi kết nối đến cơ sở dữ liệu: " + e.getMessage());
        }
        return connection;
    }
    public static void Disconnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e){
            System.out.println("không thể đóng " + e.getMessage());
        }
    }
}

