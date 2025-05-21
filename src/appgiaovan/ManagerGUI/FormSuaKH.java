/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.ManagerGUI;

import appgiaovan.Controller.QLKHController;
import appgiaovan.Entity.KhachHang;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormSuaKH extends JDialog {
    private QLKHController controller;
    private KhachHang kh;
    private JTextField txtID, txtHoTen, txtSDT, txtEmail, txtCCCD, txtNgaySinh;
    private JComboBox<Character> cboGioiTinh;

    public FormSuaKH(Frame owner, KhachHang kh) throws ClassNotFoundException {
        super(owner, "Sửa Khách Hàng", true);
        this.controller = new QLKHController();
        this.kh = kh;
        initUI();
        hienThiThongTinKhachHang();
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
        JButton btnSave = new JButton("Lưu"); btnSave.addActionListener(e->onSave()); add(btnSave);
        JButton btnCancel = new JButton("Hủy"); btnCancel.addActionListener(e->dispose()); add(btnCancel);
        pack(); setLocationRelativeTo(getOwner());
    }

    public void hienThiThongTinKhachHang() {
        txtID.setText(String.valueOf(kh.getID_NguoiDung()));
        txtHoTen.setText(kh.getHoTen());
        txtSDT.setText(kh.getSDT());
        txtEmail.setText(kh.getEmail());
        txtCCCD.setText(kh.getCCCD());
        txtNgaySinh.setText(kh.getNgaySinh().toString());
        cboGioiTinh.setSelectedItem(kh.getGioiTinh());
    }

    public boolean kiemTraDinhDangThongTin() {
        return !txtHoTen.getText().isEmpty();
    }

    public void hienThiThongBao(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    private void onSave() {
        if (!kiemTraDinhDangThongTin()) {
            hienThiThongBao("Thông tin không hợp lệ"); return;
        }
        kh.setHoTen(txtHoTen.getText());
        kh.setSDT(txtSDT.getText());
        kh.setEmail(txtEmail.getText());
        kh.setCCCD(txtCCCD.getText());
        kh.setNgaySinh(java.sql.Date.valueOf(txtNgaySinh.getText()));
        kh.setGioiTinh((Character)cboGioiTinh.getSelectedItem());
        boolean ok = controller.suaKhachHang(kh);
        hienThiThongBao(ok?"Sửa thành công":"Sửa thất bại");
        if (ok) dispose();
    }
}
