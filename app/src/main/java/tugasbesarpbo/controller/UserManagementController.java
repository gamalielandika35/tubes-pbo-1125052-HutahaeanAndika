
package tugasbesarpbo.controller;

import tugasbesarpbo.model.Role;
import tugasbesarpbo.model.User;
import tugasbesarpbo.service.UserService;
import tugasbesarpbo.view.UserManagementView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import tugasbesarpbo.util.SessionManager;

public class UserManagementController {

    private UserManagementView view;
    private UserService userService;

    public UserManagementController(UserManagementView view) {
        this.view = view;
        userService = new UserService();

        view.getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        view.getUpdateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });

        view.getDeleteButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });

        view.getClearButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        view.getUserTable().getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        selectUser();
                    }
                }
            }
        );

        loadUsers();
    }

    private void loadUsers() {
        try {
            view.getTableModel().setRowCount(0);

            List<User> users = userService.getAllUsers();

            for (User user : users) {
                Object[] row = {
                    user.getUserId(),
                    user.getFullName(),
                    user.getUsername(),
                    user.getRole(),
                    user.getPhone(),
                    user.isActive()
                };

                view.getTableModel().addRow(row);
            }

        } catch (SQLException e) {
            view.getStatusLabel().setText(
                "Gagal memuat data user."
            );
            e.printStackTrace();
        }
    }

    private void addUser() {
        String fullName = view.getFullNameField().getText().trim();
        String username = view.getUsernameField().getText().trim();
        String password = new String(
            view.getPasswordField().getPassword()
        );
        String phone = view.getPhoneField().getText().trim();
        Role role = Role.valueOf(
            view.getRoleComboBox().getSelectedItem().toString()
        );
        boolean active = view.getActiveCheckBox().isSelected();

        if (fullName.isEmpty() || username.isEmpty() || password.isEmpty()) {
            view.getStatusLabel().setText(
                "Nama, username, dan password wajib diisi."
            );
            return;
        }

        User user = new User(
            0,
            fullName,
            username,
            "",
            role,
            phone,
            active
        );

        try {
            userService.addUser(user, password);
            view.getStatusLabel().setText("User berhasil ditambahkan.");
            clearForm();
            loadUsers();

        } catch (SQLException e) {
            view.getStatusLabel().setText(
                "Gagal menambahkan user. Username mungkin sudah digunakan."
            );
            e.printStackTrace();
        }
    }

    private void updateUser() {
        int selectedRow = view.getUserTable().getSelectedRow();

        if (selectedRow == -1) {
            view.getStatusLabel().setText(
                "Pilih user yang ingin diubah."
            );
            return;
        }

        long userId = Long.parseLong(
            view.getTableModel().getValueAt(selectedRow, 0).toString()
        );

        String fullName = view.getFullNameField().getText().trim();
        String username = view.getUsernameField().getText().trim();
        String password = new String(
            view.getPasswordField().getPassword()
        );
        String phone = view.getPhoneField().getText().trim();
        Role role = Role.valueOf(
            view.getRoleComboBox().getSelectedItem().toString()
        );
        boolean active = view.getActiveCheckBox().isSelected();

        if (fullName.isEmpty() || username.isEmpty()) {
            view.getStatusLabel().setText(
                "Nama dan username wajib diisi."
            );
            return;
        }

        try {
            User existingUser = userService.getUserById(userId);

            if (existingUser == null) {
                view.getStatusLabel().setText("User tidak ditemukan.");
                return;
            }

            User user = new User(
                userId,
                fullName,
                username,
                existingUser.getPassword(),
                role,
                phone,
                active
            );

            userService.updateUser(user, password);
            view.getStatusLabel().setText("User berhasil diperbarui.");
            clearForm();
            loadUsers();

        } catch (SQLException e) {
            view.getStatusLabel().setText(
                "Gagal memperbarui user."
            );
            e.printStackTrace();
        }
    }

    
private void deleteUser() {
    int selectedRow = view.getUserTable().getSelectedRow();

    if (selectedRow == -1) {
        view.getStatusLabel().setText(
            "Pilih user yang ingin dinonaktifkan."
        );
        return;
    }

    long userId = Long.parseLong(
        view.getTableModel().getValueAt(selectedRow, 0).toString()
    );

    User currentUser = SessionManager.getInstance().getCurrentUser();

    if (currentUser != null && currentUser.getUserId() == userId) {
        view.getStatusLabel().setText(
            "Anda tidak dapat menonaktifkan akun sendiri."
        );
        return;
    }

    try {
        userService.deleteUser(userId);
        view.getStatusLabel().setText(
            "User berhasil dinonaktifkan."
        );
        clearForm();
        loadUsers();

    } catch (SQLException e) {
        view.getStatusLabel().setText(
            "Gagal menonaktifkan user."
        );
        e.printStackTrace();
    }
}


    private void selectUser() {
        int selectedRow = view.getUserTable().getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        view.getFullNameField().setText(
            view.getTableModel().getValueAt(selectedRow, 1).toString()
        );
        view.getUsernameField().setText(
            view.getTableModel().getValueAt(selectedRow, 2).toString()
        );
        view.getRoleComboBox().setSelectedItem(
            view.getTableModel().getValueAt(selectedRow, 3).toString()
        );

        Object phone = view.getTableModel().getValueAt(selectedRow, 4);
        view.getPhoneField().setText(phone == null ? "" : phone.toString());

        boolean active = Boolean.parseBoolean(
            view.getTableModel().getValueAt(selectedRow, 5).toString()
        );
        view.getActiveCheckBox().setSelected(active);

        view.getPasswordField().setText("");
    }

    private void clearForm() {
        view.getUserTable().clearSelection();
        view.getFullNameField().setText("");
        view.getUsernameField().setText("");
        view.getPasswordField().setText("");
        view.getPhoneField().setText("");
        view.getRoleComboBox().setSelectedIndex(0);
        view.getActiveCheckBox().setSelected(true);
        view.getStatusLabel().setText(" ");
    }
}
