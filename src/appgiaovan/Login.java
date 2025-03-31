/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends LoginFrame {

    public Login() {
        super();
    }

    @Override
    protected void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
       
        new Thread(() -> {
            
        String username = UsernameField.getText();
        String password = new String(PasswordField.getPassword());

        // Hash the password (for simplicity, we are not hashing here)
        String passwordHash = PasswordHashing.hashPassword(password);; // In a real application, hash the password

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM Account WHERE USERNAME = ? AND PASSWORD_HASH = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, passwordHash);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    // Login successful
                    JOptionPane.showMessageDialog(Login.this, "Đăng nhập thành công!");
                    MainScreen mainscreen = new MainScreen();
                    mainscreen.setVisible(true);
                    dispose(); // Close the login frame
                } else {
                    // Invalid credentials
                    JOptionPane.showMessageDialog(Login.this, "Invalid username or password. Please try again.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(Login.this, "Database error: " + ex.getMessage());
        }
        }).start();
    }

    @Override
    protected void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        Register register = new Register();
        register.setVisible(true);
    }

}
