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
        JPanel pnl = new JPanel(new GridBagLayout());
        pnl.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        pnl.setPreferredSize(new Dimension(450, 350));  // cùng kích thước với form Thêm
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = {
            "Mã KH:", "Họ Tên:", "SĐT:", "Email:",
            "CCCD:", "Ngày sinh:", "Giới tính:"
        };
        Component[] fields = {
            txtID = new JTextField(), txtHoTen = new JTextField(),
            txtSDT = new JTextField(), txtEmail = new JTextField(),
            txtCCCD = new JTextField(), txtNgaySinh = new JTextField(),
            cboGioiTinh = new JComboBox<>(new Character[]{'M','F'})
        };
        txtID.setEnabled(false);

        for (int i = 0; i < labels.length; i++) {
            c.gridx = 0; c.gridy = i; c.weightx = 0.2;
            pnl.add(new JLabel(labels[i]), c);
            c.gridx = 1; c.weightx = 0.8;
            pnl.add(fields[i], c);
        }

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        JButton btnSave = new JButton("Lưu");    btnSave.addActionListener(e->onSave());
        JButton btnCancel = new JButton("Hủy");  btnCancel.addActionListener(e->dispose());
        btnPanel.add(btnSave); btnPanel.add(btnCancel);

        c.gridx = 0; c.gridy = labels.length;
        c.gridwidth = 2; c.anchor = GridBagConstraints.EAST;
        pnl.add(btnPanel, c);

        setContentPane(pnl);
        pack();
        setLocationRelativeTo(getOwner());
        setResizable(false);
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
