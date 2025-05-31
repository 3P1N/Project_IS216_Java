/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appgiaovan.CustomerGUI;

import appgiaovan.Controller.DangKyController;
import appgiaovan.Controller.QLDonHangController;
import appgiaovan.Controller.QLKHController;
import appgiaovan.Entity.KhachHang;
import appgiaovan.GUI.Components.TimeWeather;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author nhant
 */
public class ThongTinCaNhanPanel extends JPanel {
        private JTextField txtHoTen, txtSDT, txtEmail, txtCCCD, txtNgaySinh, txtGioiTinh;
        private JButton btnCapNhat;
        private KhachHang kh=new KhachHang();
        private DangKyController controller=new DangKyController();
    public ThongTinCaNhanPanel(int ID_KhachHang) throws ClassNotFoundException{
        QLKHController qLKHController=new QLKHController();
        kh=qLKHController.layThongTinKhachHang(ID_KhachHang);
        setLayout(new BorderLayout());
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

        JTextField txtHoTen = new JTextField(kh.getHoTen());
        txtHoTen.setBounds(130, 70, 200, 30);
        infoPanel.add(txtHoTen);

        // Số điện thoại
        JLabel lblSDT = new JLabel("SDT:");
        lblSDT.setBounds(20, 120, 100, 25);
        infoPanel.add(lblSDT);

        JTextField txtSDT = new JTextField(kh.getSDT());
        txtSDT.setBounds(130, 120, 200, 30);
        infoPanel.add(txtSDT);
        
        // Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 170, 100, 25);
        infoPanel.add(lblEmail);

        JTextField txtEmail = new JTextField(kh.getEmail());
        txtEmail.setBounds(130, 170, 300, 30);
        infoPanel.add(txtEmail);
        //CCCD  
        JLabel lblCCCD  = new JLabel("CCCD :");
        lblCCCD .setBounds(20, 220, 100, 25);
        infoPanel.add(lblCCCD );

        JTextField txtCCCD  = new JTextField(kh.getCCCD());
        txtCCCD .setBounds(130, 220, 400, 30);
        infoPanel.add(txtCCCD );
        // NgaySinh
        JLabel lblNgaySinh = new JLabel("Ngày sinh:");
        lblNgaySinh.setBounds(20, 270, 100, 25);
        infoPanel.add(lblNgaySinh);
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
        JTextField txtNgaySinh = new JTextField(String.valueOf(sdf2.format(kh.getNgaySinh())));
        txtNgaySinh.setBounds(130, 270, 400, 30);
        infoPanel.add(txtNgaySinh);
        //Gioitinh
        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setBounds(20, 320, 100, 25);
        infoPanel.add(lblGioiTinh);
        
        
        JTextField txtGioiTinh = new JTextField(kh.getGioiTinh());
        txtGioiTinh.setBounds(130, 320, 400, 30);
        infoPanel.add(txtGioiTinh);
        // Nút cập nhật
        JButton btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setBounds(130, 370, 120, 35);
        btnCapNhat.setBackground(new Color(0, 123, 255));
        btnCapNhat.setForeground(Color.WHITE);
        infoPanel.add(btnCapNhat);
        btnCapNhat.addActionListener(e->{
            kh.setHoTen(txtHoTen.getText());
            kh.setSDT(txtSDT.getText());
            kh.setEmail(txtEmail.getText());
            kh.setCCCD(txtCCCD.getText());
            String ngaySinhStr = txtNgaySinh.getText(); // ví dụ chuỗi ngày sinh
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date ngaySinhDate = sdf.parse(ngaySinhStr);
                kh.setNgaySinh(ngaySinhDate);
            } catch (ParseException ex) {
                Logger.getLogger(ThongTinCaNhanPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            kh.setGioiTinh(txtGioiTinh.getText());
            if (!controller.KiemTraDinhDangCapNhat(kh)) {
                    JOptionPane.showMessageDialog(this,
                            "Định dạng đơn hàng không hợp lệ. Vui lòng kiểm tra lại.",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return; // Dừng lại, không thực hiện thêm
                }
            if(qLKHController.suaKhachHang(kh)){
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(this,
                            "Lỗi hệ thống",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
        });
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

            try {
                frame.add(new ThongTinCaNhanPanel(1), BorderLayout.CENTER);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThongTinCaNhanPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.setVisible(true);
        });
    }
}
