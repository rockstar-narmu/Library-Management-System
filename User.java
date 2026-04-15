public class User {
    private static int counter = 1001;

    private String userId;
    private String name;
    private String password;

    public User(String name, String password) {
        this.userId = "U" + counter++;
        this.name = name;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void displayUser() {
        System.out.println(userId + " | " + name);
    }
}