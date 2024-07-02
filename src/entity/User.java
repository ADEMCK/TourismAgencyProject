package entity;

/**
 * This class represents a user entity with attributes for user details.
 */
public class User {
    private int userId;
    private String username;
    private String password;
    private Role role;

    /**
     * Enum for user roles.
     */
    public enum Role {
        admin,
        employee
    }

    /**
     * Default constructor.
     */
    public User() {
    }

    //Constructor to initialize a User object with username, password, and role.
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    //Constructor to initialize a User object with user ID, username, password, and role.
    public User(int userId, String username, String password, Role role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getter and setter methods

    //Gets the unique identifier of the user.
    public int getUserId() {
        return userId;
    }

    //Sets the unique identifier of the user.
    public void setUserId(int userId) {
        this.userId = userId;
    }

    //Gets the username of the user.
    public String getUsername() {
        return username;
    }

    //Sets the username of the user.
    public void setUsername(String username) {
        this.username = username;
    }

    //Gets the password of the user.
    public String getPassword() {
        return password;
    }

    //Sets the password of the user.
    public void setPassword(String password) {
        this.password = password;
    }

    //Gets the role of the user.
    public Role getRole() {
        return role;
    }

    //Sets the role of the user.
    public void setRole(Role role) {
        this.role = role;
    }

    //Returns a string representation of the User object.
    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}