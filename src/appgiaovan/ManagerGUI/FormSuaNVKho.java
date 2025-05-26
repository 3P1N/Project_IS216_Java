/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.ManagerGUI;

import appgiaovan.Controller.QLNVKhoController;

import appgiaovan.Entity.NhanVienKho;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormSuaNVKho extends JDialog {
    private QLNVKhoController controller;
    private NhanVienKho nv;
    private JTextField txtID, txtHoTen, txtSDT, txtEmail, txtCCCD, txtNgaySinh, txtDiaChi, txtIDKho, txtIDQuanLy, txtMucLuong;
    private JComboBox<String> cboGioiTinh;

    public FormSuaNVKho(NhanVienKho nv) throws ClassNotFoundException {
        JFrame frame = new JFrame("Quản Lý Nhân Viên Kho");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 600);
        frame.setLocationRelativeTo(null); // Center the frame
        super(frame, "Sửa Khách Hàng", true);
        this.controller = new QLNVKhoController();
        this.nv = nv;
        initUI();
        hienThiThongTinNhanVienKho();
    }

    private void initUI() {
        JPanel pnl = new JPanel(new GridBagLayout());
        pnl.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        pnl.setPreferredSize(new Dimension(650, 550));  // cùng kích thước với form Thêm
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = {
            "Mã nhân viên kho:", "Họ Tên:", "SĐT:", "Email:",
            "CCCD:", "Ngày sinh:", "Giới tính:", "Địa chỉ",
            "ID_Kho:", "ID_QuanLy:", "Mức lương:"
        };
        Component[] fields = {
            txtID = new JTextField(), txtHoTen = new JTextField(),
            txtSDT = new JTextField(), txtEmail = new JTextField(),
            txtCCCD = new JTextField(), txtNgaySinh = new JTextField(),
            cboGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"}),
            txtDiaChi = new JTextField(), txtIDKho = new JTextField(),
            txtIDQuanLy = new JTextField(), txtMucLuong = new JTextField()
            
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

    public void hienThiThongTinNhanVienKho() {
        txtID.setText(String.valueOf(nv.getID_NguoiDung()));
        txtHoTen.setText(nv.getHoTen());
        txtSDT.setText(nv.getSDT());
        txtEmail.setText(nv.getEmail());
        txtCCCD.setText(nv.getCCCD());
        txtNgaySinh.setText(nv.getNgaySinh().toString());
        cboGioiTinh.setSelectedItem(nv.getGioiTinh());
        txtDiaChi.setText(nv.getDiaChi());
        txtIDKho.setText(String.valueOf(nv.getID_Kho()));
        txtIDQuanLy.setText(String.valueOf(nv.getID_QuanLy()));
        txtMucLuong.setText(String.valueOf(nv.getMucLuong()));
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
        nv.setHoTen(txtHoTen.getText());
        nv.setSDT(txtSDT.getText());
        nv.setEmail(txtEmail.getText());
        nv.setCCCD(txtCCCD.getText());
        nv.setNgaySinh(java.sql.Date.valueOf(txtNgaySinh.getText()));
        nv.setGioiTinh(cboGioiTinh.getSelectedItem().toString());
        nv.setDiaChi(txtDiaChi.getText());
        nv.setID_Kho(Integer.parseInt(txtIDKho.getText()));
        nv.setID_QuanLy(Integer.parseInt(txtIDQuanLy.getText()));
        nv.setMucLuong(Double.parseDouble(txtMucLuong.getText()));
        boolean ok = controller.suaNhanVienKho(nv);
        hienThiThongBao(ok?"Sửa thành công":"Sửa thất bại");
        if (ok) dispose();
    }
    
}
