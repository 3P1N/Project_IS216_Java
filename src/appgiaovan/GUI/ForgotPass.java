/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.GUI;

import javax.swing.*;
import java.awt.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ForgotPass extends JFrame {
    public ForgotPass() {
        setTitle("Quên mật khẩu");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Nhập email hoặc username
        JTextField emailField = new JTextField();
        panel.add(new JLabel("Nhập email hoặc tên đăng nhập:"));
        panel.add(emailField);

        // Nút gửi
        JButton sendBtn = new JButton("Gửi yêu cầu");
        sendBtn.addActionListener(ev -> {
            String input = emailField.getText().trim();
            // TODO: Gọi backend gửi mail/OTP
            JOptionPane.showMessageDialog(this,
                    "Kiểm tra mail để được hướng dẫn",
                "Đã gửi", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        });
        panel.add(sendBtn);

        add(panel, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ForgotPass frame = new ForgotPass();
            frame.setVisible(true);
        });
    }
}

