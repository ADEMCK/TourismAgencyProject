package dao;

import core.Db;
import entity.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * Data access object for handling operations related to users in the database.
 */
public class UserDao {
    private final Connection con;

    /**
     * Constructs a new UserDao instance and initializes the database connection.
     */
    public UserDao() {
        this.con = Db.getInstance();
    }

    //Finds a user by their username and password.
    public User findByLogin(String username, String password) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_password = ?";
        try (PreparedStatement pr = this.con.prepareStatement(query)) {
            pr.setString(1, username);
            pr.setString(2, password);

            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    //Executes a given SQL query and returns a list of users.
    public ArrayList<User> selectByQuery(String query) {
        ArrayList<User> userList = new ArrayList<>();
        try (ResultSet rs = this.con.createStatement().executeQuery(query)) {
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    //Matches a ResultSet to a User object.
    public User match(ResultSet rs) throws SQLException {
        User obj = new User();
        obj.setUserId(rs.getInt("user_id"));
        obj.setUsername(rs.getString("user_name"));
        obj.setPassword(rs.getString("user_password"));
        obj.setRole(User.Role.valueOf(rs.getString("user_role")));
        return obj;
    }

    //Retrieves all users from the database.
    public ArrayList<User> findAll() {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM public.user ORDER BY user_id ASC";
        try (Statement st = this.con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    //Retrieves a user by their ID.
    public User getById(int id) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    //Saves a new user to the database.
    public boolean save(User user) {
        String query = "INSERT INTO public.user (user_name, user_password, user_role) VALUES (?,?,?)";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole().toString());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    //Updates an existing user in the database.
    public boolean update(User user) {
        String query = "UPDATE public.user SET user_name = ?, user_password = ?, user_role = ? WHERE user_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole().toString());
            pr.setInt(4, user.getUserId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    //Deletes a user from the database by their ID.
    public boolean delete(int id) {
        String query = "DELETE FROM public.user WHERE user_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
