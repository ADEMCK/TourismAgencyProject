package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

public class LoginView extends Layout {
    private JPanel container;
    private JTextField fld_user_name;
    private JPasswordField fld_password;
    private JButton btn_login;
    private JLabel lbl_welcome;
    private final UserManager userManager;

    public LoginView() {
        this.userManager = new UserManager();
        add(container);
        this.guiInitialize(550, 300);
        this.setVisible(true);
        // Add action listener to the login button
        btn_login.addActionListener(e -> {
            // Array of fields to check for empty values
            JTextField[] checkFieldList = {this.fld_user_name, this.fld_password};
            // Check if any fields are empty
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("Please fill in all fields."); // Show message to fill in all fields
            } else {
                // Attempt to find the user by login credentials
                User loginUser = this.userManager.findByLogin(this.fld_user_name.getText(), this.fld_password.getText());
                System.out.println("User information: " + loginUser);
                // If user is not found, show an error message
                if (loginUser == null) {
                    Helper.showMsg("User not found."); // Show message if user is not found
                } else {
                    // Check the role of the user and open the appropriate view
                    if (loginUser.getRole().toString().equals("admin")) {
                        AdminView adminView = new AdminView(loginUser);
                        dispose(); // Close the current window
                    }
                    if (loginUser.getRole().toString().equals("employee")) {
                        HotelView employee = new HotelView(loginUser);
                        dispose(); // Close the current window
                    }
                }
            }
        });
    }
}