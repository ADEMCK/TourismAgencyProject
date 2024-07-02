package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
    private JPanel pnl_top;
    private JTabbedPane tabbedPane1;
    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_admin_logout;
    private JComboBox cmb_role_list;
    private JButton btn_rol_search;
    private JButton btn_user_list_clear;
    private JTable tbl_user;
    private JPopupMenu user_Menu;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private UserManager userManager;
    private User user;

    private Object[] col_user;

    public AdminView(User user) {
        this.userManager = new UserManager();
        add(container);
        this.guiInitialize(1000, 500); // Initializing GUI components with a specific size
        this.user = user;
        if (this.user == null) {
            dispose(); // Closes the window if no user is provided
        }
        btn_admin_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Closes the window
                LoginView loginView = new LoginView(); // Opens a new login view when logout button is clicked
            }
        });

        this.lbl_welcome.setText("Welcome: " + this.user.getRole()); // Sets a welcome message based on the user's role
        loadAdminPanelComponent(); // Loads components for the admin panel
        loadRoleFilter(); // Loads filter options for user roles
        loadRoleTable(null); // Loads the initial table of users
        loadAdminPanelComponent(); // Loads components for the admin panel again (repeated call)
    }

    // Loads filter options for user roles
    public void loadRoleFilter() {
        this.cmb_role_list.setModel(new DefaultComboBoxModel<>(User.Role.values())); // Sets the combo box with user roles
        this.cmb_role_list.setSelectedItem(null); // Clears the selected item
    }

    // Loads the table of users based on the given list
    public void loadRoleTable(ArrayList<Object[]> userList) {
        this.col_user = new Object[]{"ID", "Username", "Password", "Role"}; // Column headers for the user table
        if (userList == null) {
            userList = this.userManager.getForTable(col_user.length, userManager.findAll()); // Retrieves users for the table
        }
        this.createTable(this.tmdl_user, this.tbl_user, col_user, userList); // Creates the table with the given data
    }

    // Loads components specific to the admin panel
    public void loadAdminPanelComponent() {

        btn_rol_search.addActionListener(e -> {
            if (this.cmb_role_list.getSelectedItem() != null) {
                ArrayList<User> rolListBySearch = this.userManager.searchForTable((User.Role) cmb_role_list.getSelectedItem()); // Searches users based on selected role
                ArrayList<Object[]> modelRowListBySearch = this.userManager.getForTable(this.col_user.length, rolListBySearch); // Retrieves data for filtered table
                loadRoleTable(modelRowListBySearch); // Loads the filtered table
            }
        });
        this.btn_user_list_clear.addActionListener(e -> {
            this.cmb_role_list.setSelectedItem(null); // Clears selected role filter
            loadRoleTable(null); // Reloads the full user table
        });
        this.tableRowSelect(this.tbl_user); // Enables row selection in the user table
        this.user_Menu = new JPopupMenu(); // Creates a popup menu for user operations
        this.user_Menu.add("New").addActionListener(e -> {
            UserManagementView managementView = new UserManagementView(null); // Opens a new user management view for creating a new user
            managementView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoleTable(null); // Reloads the user table after closing the management view
                }
            });
        });
        this.user_Menu.add("Update").addActionListener(e -> {
            int selectUserdId = this.getTableSelectedRow(tbl_user, 0); // Retrieves the selected user ID
            UserManagementView managementView = new UserManagementView(this.userManager.getById(selectUserdId)); // Opens a management view for updating the selected user
            managementView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoleTable(null); // Reloads the user table after closing the management view
                }
            });
        });
        this.user_Menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("Are you sure?")) { // Confirms user deletion
                int selectBrandId = this.getTableSelectedRow(tbl_user, 0); // Retrieves the selected user ID for deletion
                if (this.userManager.delete(selectBrandId)) { // Deletes the user
                    Helper.showMsg("Operation successful"); // Shows success message
                    loadRoleTable(null); // Reloads the user table
                } else {
                    Helper.showMsg("Operation failed"); // Shows error message
                }
            }
        });
        this.tbl_user.setComponentPopupMenu(this.user_Menu); // Sets the popup menu for the user table
    }
}
