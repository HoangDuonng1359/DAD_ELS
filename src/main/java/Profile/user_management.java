package Profile;

import Bookmark.bookmark;
import dictionary.DatabaseConnection;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.els.baseFormController;
import user.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class user_management {
    private User user;

    public user_management(User user) {
        this.user = user;
    }

    public String getName(){
        return user.getName();
    }

    public Image getAvata(){
        return user.getAvata();
    }

    public User changeName(String name) throws SQLException {
        Connection connection = DatabaseConnection.connect(baseFormController.DATABASE_URL);
        String sql = "UPDATE user_table SET name = ? WHERE id = ? ";
        PreparedStatement pr = connection.prepareStatement(sql);
        pr.setString(1,name);
        pr.setInt(2,user.getId());
        pr.executeUpdate();
        connection.close();
        user.setName(name);
        return user;
    }
    public User Change_password(String currentpass,String newpass) throws SQLException {
        if(user.getPass().equals(currentpass)){
            Connection connection = DatabaseConnection.connect(baseFormController.DATABASE_URL);
            String sql = "UPDATE user_table SET password = ? WHERE id = ? ";
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1,newpass);
            pr.setInt(2,user.getId());
            pr.executeUpdate();
            connection.close();
            user.setPass(newpass);
            return user;
        }
        else {
            return null;
        }
    }
    public static Image chooseImage(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        // Show open file dialog
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            // Load selected image
            Image image = new Image(file.toURI().toString());
            return image;
        } else
            return null;
    }
    public static Image changeAvt(Stage primaryStage) throws SQLException {
        Image image = chooseImage(primaryStage);
        if (image != null) {
            try {
                byte[] imageData = imageToBytes(image);
                // Database connection and update
                Connection conn = DatabaseConnection.connect(baseFormController.DATABASE_URL);
                String sql = "UPDATE user_table SET avata = ? WHERE id = ?";
                PreparedStatement pr = conn.prepareStatement(sql);
                pr.setBytes(1, imageData);
                pr.setInt(2, baseFormController.user.getId()); // You need to set the appropriate user id here
                pr.executeUpdate();
                conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

    public static byte[] imageToBytes(Image image) throws IOException {
        try (FileInputStream stream = new FileInputStream(new File(image.getUrl().replace("file:/", "")))) {
            byte[] bytes = new byte[stream.available()];
            stream.read(bytes);
            return bytes;
        }
    }
    public static boolean reset_default(User user){
        boolean check = true;
        Connection conn = DatabaseConnection.connect(baseFormController.DATABASE_URL);
        String sql = "DELETE from custom_dictionary where user_id = ?";
        PreparedStatement pr = null;
        try {
            pr = conn.prepareStatement(sql);
            pr.setInt(1,user.getId());
            pr.executeUpdate();
        } catch (SQLException e) {
            check = false;
            throw new RuntimeException(e);

        }
        sql = "DELETE from RecentList where user_id = ?";

        try{
            pr = conn.prepareStatement(sql);
            pr.setInt(1,user.getId());
            pr.executeUpdate();
        } catch (SQLException e) {
            check = false;
            throw new RuntimeException(e);
        }
        sql = "DELETE from max_score_game where user_id = ?";

        try{
            pr = conn.prepareStatement(sql);
            pr.setInt(1,user.getId());
            pr.executeUpdate();
        } catch (SQLException e) {
            check = false;
            throw new RuntimeException(e);
        }
        try {
            conn.close();
        } catch (SQLException e) {
            check = false;
            throw new RuntimeException(e);
        }
        try {
            check = bookmark.reset(user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }
}
