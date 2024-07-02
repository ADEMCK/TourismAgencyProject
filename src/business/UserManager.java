package business;

import core.Helper;
import dao.UserDao;
import entity.User;

import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    // Constructor to initialize UserDao
    public UserManager() {
        this.userDao = new UserDao();
    }

    // Method to find a user by username and password
    public User findByLogin(String username, String password) {
        return this.userDao.findByLogin(username, password);
    }

    // Method to find all users
    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    // Method to format users for a table view
    public ArrayList<Object[]> getForTable(int size, ArrayList<User> userList) {
        ArrayList<Object[]> userRoleList = new ArrayList<>();
        for (User user : userList) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = user.getUserId();
            rowObject[i++] = user.getUsername();
            rowObject[i++] = user.getPassword();
            rowObject[i++] = user.getRole();
            userRoleList.add(rowObject);
        }
        return userRoleList;
    }

    // Method to get a user by their ID
    public User getById(int id) {
        return this.userDao.getById(id);
    }

    // Method to search for users by role
    public ArrayList<User> searchForTable(User.Role role) {
        String query = "SELECT * FROM public.user WHERE user_role = '" + role.toString() + "'";
        return this.userDao.selectByQuery(query);
    }

    // Method to save a new user
    public boolean save(User user) {
        if (this.getById(user.getUserId()) != null) {
            Helper.showMsg("error"); // Show error message if user already exists
            return false;
        }
        return this.userDao.save(user);
    }

    // Method to update an existing user
    public boolean update(User user) {
        if (this.getById(user.getUserId()) == null) {
            Helper.showMsg(user.getUserId() + " ID not found"); // Show error message if user ID not found
            return false;
        }
        return this.userDao.update(user);
    }

    // Method to delete a user by their ID
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID not found"); // Show error message if user ID not found
            return false;
        }
        return this.userDao.delete(id);
    }
}