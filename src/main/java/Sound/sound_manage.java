package Sound;
import dictionary.DatabaseConnection;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sound_manage {
    private static String DATA_BASE = "jdbc:sqlite:src\\Data\\database.db";
    public static Media getMediaByNameFromDB (String namesound) throws SQLException, IOException {
        Connection conn = DatabaseConnection.connect(DATA_BASE);
        String sql = "SELECT sounds FROM sounds_table WHERE name = ?";
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setString(1,namesound);
        ResultSet rs = pr.executeQuery();
        Media media = null;
        if(rs.next()){
            byte[] soundBytes = rs.getBytes("sounds");
            File tempFile = File.createTempFile("temp", ".mp3");
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(soundBytes);
            fos.close();

            // Create a Media object from the temporary file
            media = new Media(tempFile.toURI().toString());
        }
        return media;
    }

    public static void playMedia(Media media){
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public static Media getMediaByNameFormSRC(String source){
        return new Media(new File(source).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.play();
    }
}
