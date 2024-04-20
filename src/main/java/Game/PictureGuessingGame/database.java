package Game.PictureGuessingGame;

import javafx.scene.image.Image;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public static List<Question> getAllQuestion(String DB_URL) {
        List<Question> res = new ArrayList<Question>();

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
                Question question = new Question();
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

    public static void insertSoundToDB(String DB_URL, String URL_FILE, String NAME_FILE) {
        String url = DB_URL;
        String sql = "INSERT INTO sounds_table (sounds,name) VALUES (?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Đọc dữ liệu âm thanh từ tệp và chuyển đổi thành mảng byte
            File soundFile = new File(URL_FILE); // Thay đổi đường dẫn đến tệp âm thanh của bạn
            byte[] soundData = new byte[(int) soundFile.length()];
            FileInputStream fis = new FileInputStream(soundFile);
            fis.read(soundData);
            fis.close();

            // Thêm dữ liệu âm thanh vào cơ sở dữ liệu
            pstmt.setBytes(1, soundData);
            pstmt.setString(2, NAME_FILE);
            pstmt.executeUpdate();
            System.out.println("Dữ liệu âm thanh đã được thêm vào cơ sở dữ liệu.");

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static final String DB_URL = "jdbc:sqlite:src\\Data\\database.db";
    public static void updateDatabaseWithImages(String folderPath) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            File folder = new File(folderPath);
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String fileName = file.getName();
                        String result = fileName.substring(0, fileName.lastIndexOf('.')); // Remove file extension
                        // Insert image and result into the database
                        insertToDatabase(Paths.get(file.getAbsolutePath()).toString(), result,DB_URL);
                    }
                }
                System.out.println("Images added to the database successfully.");
            } else {
                System.out.println("No files found in the specified folder.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        updateDatabaseWithImages("C:\\Users\\hoang\\OneDrive\\desktop\\pic");
    }
}
