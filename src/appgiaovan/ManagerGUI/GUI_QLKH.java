package appgiaovan.ManagerGUI;

import appgiaovan.Controller.QLKHController;
import appgiaovan.Entity.KhachHang;
import java.awt.*;
import javax.swing.*;
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
        // Tạo sidebar và truyền this để xử lý bên trong
        ManagerSidebar sidebar = new ManagerSidebar(this);
        add(sidebar, BorderLayout.WEST);

        // Panel chứa nội dung khách hàng
        JPanel contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Top filter panel
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
        contentPanel.add(pnlTop, BorderLayout.NORTH);

        // Table panel
        tblKhachHang = new JTable();
        contentPanel.add(new JScrollPane(tblKhachHang), BorderLayout.CENTER);

        // Bottom buttons
        JPanel pnlButtons = new JPanel();
        JButton btnAdd = new JButton("Thêm");
        btnAdd.addActionListener(e -> onAdd());
        JButton btnEdit = new JButton("Sửa");
        btnEdit.addActionListener(e -> onEdit());
        JButton btnDelete = new JButton("Xóa");
        btnDelete.addActionListener(e -> onDelete());
        pnlButtons.add(btnAdd);
        pnlButtons.add(btnEdit);
        pnlButtons.add(btnDelete);
        contentPanel.add(pnlButtons, BorderLayout.SOUTH);

        // Load dữ liệu lần đầu
        hienThiDanhSachKhachHang();
    }

    private void onAdd() {
        try {
            FormThemKH form = new FormThemKH(this);
            form.setVisible(true);
            hienThiDanhSachKhachHang();
        } catch (Exception ex) {
            Logger.getLogger(GUI_QLKH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void onEdit() {
        try {
            int row = tblKhachHang.getSelectedRow();
            if (row < 0) return;
            int id = (int) tblKhachHang.getValueAt(row, 0);
            FormSuaKH form = new FormSuaKH(this, controller.layThongTinKhachHang(id));
            form.setVisible(true);
            hienThiDanhSachKhachHang();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_QLKH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void onDelete() {
        try {
            int row = tblKhachHang.getSelectedRow();
            if (row < 0) return;
            int id = (int) tblKhachHang.getValueAt(row, 0);
            int choice = JOptionPane.showConfirmDialog(this, "Xóa khách hàng?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                controller.xoaKhachHang(id);
                hienThiDanhSachKhachHang();
                JOptionPane.showMessageDialog(this, "Xóa thành công");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_QLKH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void hienThiDanhSachKhachHang() throws ClassNotFoundException {
        List<KhachHang> list = controller.layTatCaKhachHang();
        tblKhachHang.setModel(new KhachHangTableModel(list));
    }

    public void xuLiYeuCauTimKiemKhachHang() throws ClassNotFoundException {
        String kw = txtSearch.getText();
        List<KhachHang> list = controller.timKiemKhachHang(kw);
        tblKhachHang.setModel(new KhachHangTableModel(list));
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
