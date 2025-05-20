/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appgiaovan.CustomerGUI;

import appgiaovan.GUI.Components.TimeWeather;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author nhant
 */
public class ThongTinCaNhanPanel extends JPanel {
        private JTextField txtHoTen, txtSDT, txtEmail, txtCCCD, txtNgaySinh, txtGioiTinh;
        private JButton btnCapNhat;
    public ThongTinCaNhanPanel(){
        setLayout(new BorderLayout());
        // Sidebar trái
        CustomerSidebar sidebar = new CustomerSidebar();
        //Khu vuc trung tâm
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Thêm vào JFrame
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
        //CCCD  
        JLabel lblCCCD  = new JLabel("CCCD :");
        lblCCCD .setBounds(20, 220, 100, 25);
        infoPanel.add(lblCCCD );

        JTextField txtCCCD  = new JTextField("096205001324");
        txtCCCD .setBounds(130, 220, 400, 30);
        infoPanel.add(txtCCCD );
        // NgaySinh
        JLabel lblNgaySinh = new JLabel("Ngày sinh:");
        lblNgaySinh.setBounds(20, 270, 100, 25);
        infoPanel.add(lblNgaySinh);

        JTextField txtNgaySinh = new JTextField("24/08/2005");
        txtNgaySinh.setBounds(130, 270, 400, 30);
        infoPanel.add(txtNgaySinh);
        //Gioitinh
        JLabel lblGioiTinh = new JLabel("Địa chỉ:");
        lblGioiTinh.setBounds(20, 320, 100, 25);
        infoPanel.add(lblGioiTinh);

        JTextField txtGioiTinh = new JTextField("Giới tính");
        txtGioiTinh.setBounds(130, 320, 400, 30);
        infoPanel.add(txtGioiTinh);
        // Nút cập nhật
        JButton btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setBounds(130, 370, 120, 35);
        btnCapNhat.setBackground(new Color(0, 123, 255));
        btnCapNhat.setForeground(Color.WHITE);
        infoPanel.add(btnCapNhat);

        // Thêm infoPanel vào khu vực CENTER của mainPanel
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        //Thanh Weather
        
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Employee Main Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            frame.add(new ThongTinCaNhanPanel(), BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
