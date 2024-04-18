package dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:sqlite:src\\Data\\dict_hh.db";
    private static DatabaseConnection instance = null;
    private Connection connection = null;
    DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            System.out.println("Kết nối đến cơ sở dữ liệu thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi khi kết nối đến cơ sở dữ liệu: " + e.getMessage());
        }
    }

    // Phương thức để lấy ra đối tượng kết nối
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Phương thức để lấy ra kết nối
    public Connection getConnection() {
        return connection;
    }

    // Phương thức để đóng kết nối
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Kết nối đã được đóng.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đóng kết nối: " + e.getMessage());
        }
    }
}

