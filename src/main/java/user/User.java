package user;

public class User {
    private int id;
    private String pass;

    private String name;

    public User(int id, String pass, String name) {
        this.id = id;
        this.pass = pass;
        this.name = name;
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
}
