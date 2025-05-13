/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appgiaovan.CustomerGUI;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author nhant
 */
public class ThongTinCaNhan extends JFrame {

    public ThongTinCaNhan(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        // Sidebar trái
        CustomerSidebar sidebar = new CustomerSidebar();
        //Khu vuc trung tâm
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Thêm vào JFrame
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
        // Panel thông tin cá nhân ở giữa
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null); // Dùng layout tự do
        infoPanel.setBackground(Color.WHITE);

        // Tiêu đề
        JLabel lblTitle = new JLabel("Thông Tin Cá Nhân");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(20, 20, 300, 30);
        infoPanel.add(lblTitle);

        // Họ tên
        JLabel lblHoTen = new JLabel("Họ và tên:");
        lblHoTen.setBounds(20, 70, 100, 25);
        infoPanel.add(lblHoTen);

        JTextField txtHoTen = new JTextField("Nguyễn Văn A");
        txtHoTen.setBounds(130, 70, 200, 30);
        infoPanel.add(txtHoTen);

        // Số điện thoại
        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setBounds(20, 120, 100, 25);
        infoPanel.add(lblSDT);

        JTextField txtSDT = new JTextField("0123456789");
        txtSDT.setBounds(130, 120, 200, 30);
        infoPanel.add(txtSDT);

        // Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 170, 100, 25);
        infoPanel.add(lblEmail);

        JTextField txtEmail = new JTextField("email@example.com");
        txtEmail.setBounds(130, 170, 300, 30);
        infoPanel.add(txtEmail);

        // Địa chỉ
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setBounds(20, 220, 100, 25);
        infoPanel.add(lblDiaChi);

        JTextField txtDiaChi = new JTextField("123 Đường ABC, Quận XYZ");
        txtDiaChi.setBounds(130, 220, 400, 30);
        infoPanel.add(txtDiaChi);

        // Nút cập nhật
        JButton btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setBounds(130, 280, 120, 35);
        btnCapNhat.setBackground(new Color(0, 123, 255));
        btnCapNhat.setForeground(Color.WHITE);
        infoPanel.add(btnCapNhat);

        // Thêm infoPanel vào khu vực CENTER của mainPanel
        mainPanel.add(infoPanel, BorderLayout.CENTER);

    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Không thể cài đặt FlatLaf");
        }
        SwingUtilities.invokeLater(() -> {
            new ThongTinCaNhan().setVisible(true);
        });
    }
}
