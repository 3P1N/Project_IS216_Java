/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.GUI;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author ASUS
 */
public class LOGIN extends JFrame {

    public LOGIN() {
        setTitle("Đăng nhập - Đơn vị giao vận 3P1N");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Tạo một JPanel chính để chứa background và LoginPanel
        JPanel mainPanel = new JPanel(null);
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 900, 600); // Kích thước của toàn bộ frame
        add(mainPanel);

        // Load hình nền
        JLabel background = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/images/warehouse_11zon.jpg")));// Thay bằng hình bạn muốn
        URL imageUrl = getClass().getResource("/images/warehouse_11zon.jpg");
        System.out.println("Image URL: " + imageUrl);

        background.setBounds(0, 0, 900, 600);
        // mainPanel.add(background);

        // Panel chính để nhập login
        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(275, 100, 350, 350);
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setLayout(null);
        loginPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        // mainPanel.add(loginPanel);

        JLabel logo = new JLabel("3P1N - Đăng nhập");
        logo.setFont(new Font("Arial", Font.BOLD, 20));
        logo.setForeground(new Color(0, 102, 204));
        logo.setBounds(90, 45, 200, 20);
        loginPanel.add(logo);

        JTextField userField = new JTextField();
        userField.setBounds(30, 130, 290, 35);
        userField.setBorder(BorderFactory.createTitledBorder("Tên đăng nhập"));
        loginPanel.add(userField);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(30, 180, 290, 35);
        passField.setBorder(BorderFactory.createTitledBorder("Mật khẩu"));
        loginPanel.add(passField);

        JLabel forgot = new JLabel("Quên mật khẩu?");
        forgot.setBounds(200, 225, 120, 20);
        forgot.setForeground(Color.BLUE);
        forgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginPanel.add(forgot);
        forgot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Hiển thị form Quên mật khẩu
                SwingUtilities.invokeLater(() -> {
                    ForgotPass fp = new ForgotPass();
                    fp.setVisible(true);
                });
            }
        });

        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setBounds(30, 260, 290, 40);
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginPanel.add(loginButton);

        // Label "Bạn chưa có tài khoản?" 
        JLabel infoLabel = new JLabel("Bạn chưa có tài khoản?");
        infoLabel.setBounds(70, 310, 150, 20);
        loginPanel.add(infoLabel);

        // Label "Đăng ký ngay" - có thể click chuyển giao diện
        JLabel registerLabel = new JLabel("Đăng ký ngay");
        registerLabel.setBounds(210, 310, 100, 20);
        registerLabel.setForeground(Color.BLUE);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginPanel.add(registerLabel);

        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Hiển thị form Đăng ký
                SwingUtilities.invokeLater(() -> {
                    RegisterGUI fp = new RegisterGUI();
                    fp.setVisible(true);
                });
            }
        });

        mainPanel.add(loginPanel);
        mainPanel.add(background);

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Không thể cài đặt FlatLaf");
        }
        SwingUtilities.invokeLater(() -> {
            LOGIN frame = new LOGIN();
            frame.setVisible(true);
        });
    }
}
