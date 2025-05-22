/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appgiaovan.CustomerGUI;

import appgiaovan.DAO.KhachHangDAO;
import appgiaovan.Entity.KhachHang;
import appgiaovan.GUI.Components.RoundedButton;
import appgiaovan.GUI.Components.RoundedTextField;
import appgiaovan.GUI.Components.RoundedPanel;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import appgiaovan.CustomerGUI.DanhGiaForm;
import appgiaovan.Entity.DonHang;

public class ThongTinDonHang extends JFrame {
    
        private JTextField txtHoTen, txtSDT, txtEmail, txtCCCD, txtNgaySinh, txtGioiTinh;
        private JButton btnCapNhat;
        private DonHang donHang;
    public ThongTinDonHang() {
        setTitle("Chi Tiết Đơn Hàng");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        CustomerSidebar sidebar = new CustomerSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Chi Tiết Đơn Hàng");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(30, 20, 300, 30);
        mainPanel.add(lblTitle);

        // Mã đơn
        RoundedTextField txtMaDon = new RoundedTextField("DH123456");
        txtMaDon.setBorder(BorderFactory.createTitledBorder("Mã đơn hàng"));
        txtMaDon.setEditable(false);
        txtMaDon.setBounds(30, 70, 200, 50);
        mainPanel.add(txtMaDon);

        // Người gửi
        RoundedTextField txtNguoiGui = new RoundedTextField("Nguyễn Văn A");
        txtNguoiGui.setBorder(BorderFactory.createTitledBorder("Người gửi"));
        txtNguoiGui.setEditable(false);
        txtNguoiGui.setBounds(250, 70, 200, 50);
        mainPanel.add(txtNguoiGui);

        // Người nhận
        RoundedTextField txtNguoiNhan = new RoundedTextField("Trần Thị B");
        txtNguoiNhan.setBorder(BorderFactory.createTitledBorder("Người nhận"));
        txtNguoiNhan.setEditable(false);
        txtNguoiNhan.setBounds(470, 70, 200, 50);
        mainPanel.add(txtNguoiNhan);

        // Địa chỉ nhận
        RoundedTextField txtDiaChiNhan = new RoundedTextField("123 Đường ABC, Q.1");
        txtDiaChiNhan.setBorder(BorderFactory.createTitledBorder("Địa chỉ nhận"));
        txtDiaChiNhan.setEditable(false);
        txtDiaChiNhan.setBounds(30, 140, 640, 50);
        mainPanel.add(txtDiaChiNhan);

        // Trạng thái
        RoundedTextField txtTrangThai = new RoundedTextField("Đang giao");
        txtTrangThai.setBorder(BorderFactory.createTitledBorder("Trạng thái"));
        txtTrangThai.setEditable(false);
        txtTrangThai.setBounds(30, 210, 200, 50);
        mainPanel.add(txtTrangThai);

        // Loại dịch vụ
        RoundedTextField txtLoaiDV = new RoundedTextField("Hỏa tốc");
        txtLoaiDV.setBorder(BorderFactory.createTitledBorder("Loại dịch vụ"));
        txtLoaiDV.setEditable(false);
        txtLoaiDV.setBounds(250, 210, 200, 50);
        mainPanel.add(txtLoaiDV);

        // Phí vận chuyển
        RoundedTextField txtPhi = new RoundedTextField("25.000 VNĐ");
        txtPhi.setBorder(BorderFactory.createTitledBorder("Phí vận chuyển"));
        txtPhi.setEditable(false);
        txtPhi.setBounds(470, 210, 200, 50);
        mainPanel.add(txtPhi);

        // Hình thức thanh toán
        RoundedTextField txtThanhToan = new RoundedTextField("Thanh toán khi nhận");
        txtThanhToan.setBorder(BorderFactory.createTitledBorder("Hình thức thanh toán"));
        txtThanhToan.setEditable(false);
        txtThanhToan.setBounds(30, 280, 300, 50);
        mainPanel.add(txtThanhToan);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
        RoundedButton btnDanhGia = new RoundedButton("Đánh giá đơn hàng");
        btnDanhGia.setBounds(100, 360, 200, 45);
        btnDanhGia.setBackground(new Color(0x28a745)); // Xanh lá
        btnDanhGia.setForeground(Color.WHITE);
        RoundedButton btnHuy = new RoundedButton("Hủy");
        btnHuy.setBounds(420, 360, 200, 45);
        btnHuy.setBackground(Color.RED); // Đỏ
        btnHuy.setForeground(Color.WHITE);

        btnDanhGia.addActionListener(e -> {
        DanhGiaForm form = new DanhGiaForm(donHang.getIdDonHang(),donHang.getIdKhachHang());
        form.setVisible(true);
        });
        mainPanel.add(btnDanhGia);
        mainPanel.add(btnHuy);
    }
    

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("Không thể cài đặt FlatLaf");
        }

        SwingUtilities.invokeLater(() -> {
            new ThongTinDonHang().setVisible(true);
        });
    }

    private void hienDialogDanhGia() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

