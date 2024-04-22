package Profile;

import dictionary.DatabaseConnection;
import javafx.scene.image.Image;
import org.example.els.baseFormController;
import user.User;

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
        return user.getImage();
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
}
