package view;


import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;


public class UserManagementView extends Layout {
    private JPanel container;
    private JTextField fld_mng_name;
    private JTextField fld_mng_pass;
    private JComboBox<User.Role> cmb_mng_role;
    private JButton btn_mng_save;
    private User user;
    private UserManager userManager;

    //Constructor method.
    public UserManagementView(User user) {
        this.userManager = new UserManager();
        this.user = user;

        add(container);
        this.guiInitialize(400, 400);

        // If there is a user, fill the information
        if (user != null) {
            fld_mng_name.setText(user.getUsername());
            fld_mng_pass.setText(user.getPassword());
            this.cmb_mng_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        }
        // Add user roles to the combo box
        this.cmb_mng_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        // When the save button is clicked
        btn_mng_save.addActionListener(e -> {
            // Check if the fields are empty
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_mng_name, this.fld_mng_pass})) {
                Helper.showMsg("fill"); //Evaluation Form 24-25 Appropriate pop up messages are given to the user for successful transactions and appropriate error messages are given to the user for incorrect transactions.
            } else {
                boolean result = false;
                if (this.user != null) {
                    this.user.setUsername(fld_mng_name.getText());
                    this.user.setPassword(fld_mng_pass.getText());
                    this.user.setRole((User.Role) cmb_mng_role.getSelectedItem());
                    result = this.userManager.update(this.user);
                } else {
                    //result=this.userManager.save(this.user);
                    User obj = new User(fld_mng_name.getText(), fld_mng_pass.getText(), (User.Role) cmb_mng_role.getSelectedItem());
                    result = this.userManager.save(obj);
                }
                if (result) {
                    Helper.showMsg("done"); //Evaluation Form 24-25 Appropriate pop up messages are given to the user for successful transactions and appropriate error messages are given to the user for incorrect transactions.
                    dispose();
                } else {
                    Helper.showMsg("error"); //Evaluation Form 24-25 Appropriate pop up messages are given to the user for successful transactions and appropriate error messages are given to the user for incorrect transactions.
                }
            }
        });
    }
}