package Network;

import java.net.URL;
import java.net.URLConnection;

public class manager_internet {
    public static boolean checkConnect(){
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
