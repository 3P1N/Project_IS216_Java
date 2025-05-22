/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.ManagerGUI;

import appgiaovan.Controller.QLKHController;
import appgiaovan.Entity.KhachHang;
import appgiaovan.Entity.TaiKhoan;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static appgiaovan.PasswordHashing.hashPassword;

public class FormThemKH extends JDialog {
    private QLKHController controller;
    private JTextField txtID, txtHoTen, txtSDT, txtEmail, txtCCCD, txtNgaySinh, txtTenDangNhap, txtMatKhau;
    private JComboBox<Character> cboGioiTinh;

    public FormThemKH(Frame owner) throws ClassNotFoundException {
        super(owner, "Thêm Khách Hàng", true);
        controller = new QLKHController();
        initUI();
        hienThiMaKhachHangMoi();
    }

    private void initUI() {
        setLayout(new GridLayout(8,2));
        add(new JLabel("Mã KH:")); txtID = new JTextField(); txtID.setEnabled(false); add(txtID);
        add(new JLabel("Họ Tên:")); txtHoTen = new JTextField(); add(txtHoTen);
        add(new JLabel("SĐT:")); txtSDT = new JTextField(); add(txtSDT);
        add(new JLabel("Email:")); txtEmail = new JTextField(); add(txtEmail);
        add(new JLabel("CCCD:")); txtCCCD = new JTextField(); add(txtCCCD);
        add(new JLabel("Ngày sinh (yyyy-MM-dd):")); txtNgaySinh = new JTextField(); add(txtNgaySinh);
        add(new JLabel("Giới tính:")); cboGioiTinh = new JComboBox<>(new Character[]{'M','F'}); add(cboGioiTinh);
        add(new JLabel("Tên đăng nhập:")); txtTenDangNhap = new JTextField(); add(txtTenDangNhap);
        add(new JLabel("Mật khẩu:")); txtMatKhau = new JTextField(); add(txtMatKhau);
        JButton btnSave = new JButton("Lưu"); btnSave.addActionListener(e->onSave()); add(btnSave);
        JButton btnCancel = new JButton("Hủy"); btnCancel.addActionListener(e->dispose()); add(btnCancel);
        pack(); setLocationRelativeTo(getOwner());
    }

    public void hienThiMaKhachHangMoi() throws ClassNotFoundException {
        txtID.setText(String.valueOf(controller.layMaKhachHangMoi()));
    }

    public boolean kiemTraDinhDangThongTin() {
        // simple validation
        if (txtHoTen.getText().isEmpty()||txtSDT.getText().isEmpty()) return false;
        return true;
    }

    public void hienThiThongBao(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    private void onSave() {
        if (!kiemTraDinhDangThongTin()) {
            hienThiThongBao("Thông tin không hợp lệ"); return;
        }
        KhachHang kh = new KhachHang();
        TaiKhoan tk = new TaiKhoan();
        kh.setID_NguoiDung(Integer.parseInt(txtID.getText()));
        kh.setHoTen(txtHoTen.getText());
        kh.setSDT(txtSDT.getText());
        kh.setEmail(txtEmail.getText());
        kh.setCCCD(txtCCCD.getText());
        kh.setNgaySinh(java.sql.Date.valueOf(txtNgaySinh.getText()));
        kh.setGioiTinh((Character)cboGioiTinh.getSelectedItem());
        tk.setTenTaiKhoan(txtTenDangNhap.getText());
        tk.setMatKhauMaHoa(hashPassword(txtMatKhau.getText()));
        boolean ok = controller.taoKhachHang(kh,tk);
        hienThiThongBao(ok?"Thêm thành công":"Thêm thất bại");
        if (ok) dispose();
    }
}

