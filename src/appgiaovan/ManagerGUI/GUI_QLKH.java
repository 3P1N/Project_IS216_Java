package appgiaovan.ManagerGUI;
import appgiaovan.Controller.QLKHController;
import appgiaovan.DAO.KhachHangDAO;
import appgiaovan.Entity.KhachHang;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUI_QLKH extends JFrame {
    private QLKHController controller;
    private JTable tblKhachHang;
    private JTextField txtSearch;

    public GUI_QLKH() throws ClassNotFoundException {
        controller = new QLKHController();
        setTitle("Quản Lý Khách Hàng");
        setSize(1300, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() throws ClassNotFoundException {
        // filter panel
        JPanel pnlTop = new JPanel();
        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(e -> {
            try {
                xuLiYeuCauTimKiemKhachHang();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GUI_QLKH.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        pnlTop.add(txtSearch);
        pnlTop.add(btnSearch);
        add(pnlTop, BorderLayout.NORTH);

        // table panel
        tblKhachHang = new JTable();
        add(new JScrollPane(tblKhachHang), BorderLayout.CENTER);

        // sidebar actions
        JButton btnAdd = new JButton("Thêm");
        btnAdd.addActionListener(e -> {
            FormThemKH form = null;
            try {
                form = new FormThemKH(this);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GUI_QLKH.class.getName()).log(Level.SEVERE, null, ex);
            }
            form.setVisible(true);
            try {
                hienThiDanhSachKhachHang();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GUI_QLKH.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        JButton btnEdit = new JButton("Sửa");
        btnEdit.addActionListener(e -> {
            try {
                xuLiLayThongTinKhachHang();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GUI_QLKH.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        JButton btnDelete = new JButton("Xóa");
        btnDelete.addActionListener(e -> {
            try {
                xuLiXoaKhachHang();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GUI_QLKH.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        JPanel pnlButtons = new JPanel();
        pnlButtons.add(btnAdd);
        pnlButtons.add(btnEdit);
        pnlButtons.add(btnDelete);
        add(pnlButtons, BorderLayout.SOUTH);

        // initial load
        hienThiDanhSachKhachHang();
    }

    public void hienThiDanhSachKhachHang() throws ClassNotFoundException {
        List<KhachHang> list = controller.layTatCaKhachHang();
        // convert to table model and set
        tblKhachHang.setModel(new KhachHangTableModel(list));
    }

    public void xuLiYeuCauTimKiemKhachHang() throws ClassNotFoundException {
        String kw = txtSearch.getText();
        List<KhachHang> list = controller.timKiemKhachHang(kw);
        tblKhachHang.setModel(new KhachHangTableModel(list));
    }

    public void xuLiLayThongTinKhachHang() throws ClassNotFoundException {
        int row = tblKhachHang.getSelectedRow();
        if (row < 0) return;
        int id = (int) tblKhachHang.getValueAt(row, 1); // column Mã KH
        FormSuaKH form = new FormSuaKH(this, controller.layThongTinKhachHang(id));
        form.setVisible(true);
        hienThiDanhSachKhachHang();
    }

    public void xuLiXoaKhachHang() throws ClassNotFoundException {
        int row = tblKhachHang.getSelectedRow();
        if (row < 0) return;
        int id = (int) tblKhachHang.getValueAt(row, 1);
        int choice = JOptionPane.showConfirmDialog(this, "Xóa khách hàng?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            controller.xoaKhachHang(id);
            hienThiDanhSachKhachHang();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        }
    }
        public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            try {
                new GUI_QLKH().setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GUI_QLKH.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
