package Game.PictureGuessingGame;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class database {
    public static void insertToDatabase(String url, String result, String DB_ULR) {
        try {
            // Kết nối đến cơ sở dữ liệu SQLite
            Connection conn = DriverManager.getConnection(DB_ULR);
            // Tạo bảng để lưu trữ ảnh và văn bản
            // String createTableSQL = "CREATE TABLE IF NOT EXISTS ImageAndText (id INTEGER PRIMARY KEY, image BLOB, text TEXT)";
            // conn.createStatement().executeUpdate(createTableSQL);
            // Đọc ảnh từ tệp tin
            String imagePath = url;
            InputStream imageStream = new FileInputStream(imagePath);
            // Chuỗi văn bản
            String text = result;
            // Chuyển đổi dữ liệu hình ảnh thành mảng byte
            byte[] imageData = imageStream.readAllBytes();
            // Thêm ảnh và văn bản vào cơ sở dữ liệu
            String insertSQL = "INSERT INTO pgg (images, results) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setBytes(1, imageData);
            pstmt.setString(2, text);
            pstmt.executeUpdate();
            // Đóng kết nối
            conn.close();
            System.out.println("Đã thêm ảnh");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Question> getAllQuestion(String DB_URL){
        List<Question> res = new ArrayList<Question>();
        Question question = new Question();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT images, results FROM pgg")) {
            while (rs.next()) {
                // Lấy dữ liệu ảnh từ cơ sở dữ liệu
                byte[] imageData = rs.getBytes("images");
                // Chuyển đổi dữ liệu ảnh thành đối tượng hình ảnh (Image)
                InputStream is = new ByteArrayInputStream(imageData);
                Image image = new Image(is);
                // lấy câu trả lời tương ứng
                String result = rs.getString("results");
                question.setImage(image);
                question.setResult(result);
                res.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public static List<Image> getAllImage(String DB_URL) throws SQLException {
        List<Image> images = new ArrayList<Image>();
        // Kết nối đến cơ sở dữ liệu SQLite
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT images FROM pgg")) {
            while (rs.next()) {
                // Lấy dữ liệu ảnh từ cơ sở dữ liệu
                byte[] imageData = rs.getBytes("images");

                // Chuyển đổi dữ liệu ảnh thành đối tượng hình ảnh (Image)
                InputStream is = new ByteArrayInputStream(imageData);
                Image image = new Image(is);
                images.add(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }
    static final String DB_URL = "jdbc:sqlite:database.db";
    public static void main(String[] args) {
        insertToDatabase("src/main/resources/org/example/els/image/1.png","dog",DB_URL);
    }
}
