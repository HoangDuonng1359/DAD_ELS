package user;

import javafx.scene.image.Image;

public class User {
    private int id;
    private String pass;

    private String name;
    private Image avata;
    public User(int id, String pass, String name) {
        this.id = id;
        this.pass = pass;
        this.name = name;
    }

    public Image getAvata() {
        return avata;
    }

    public void setAvata(Image avata) {
        this.avata = avata;
    }

    public int getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
