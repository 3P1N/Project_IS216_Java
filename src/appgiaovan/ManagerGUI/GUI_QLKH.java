/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package appgiaovan.ManagerGUI;
//
//import appgiaovan.GUI.Components.TableList;
//import appgiaovan.GUI.Components.MenuBar;
//import appgiaovan.GUI.Components.FilterPanel;
//import com.formdev.flatlaf.FlatLightLaf;
//import javax.swing.*;
//
//import java.awt.*;
//
//public class GUI_QLKH extends JFrame {
//
//    public GUI_QLKH() {
//        setTitle("Quản Lý Khách Hàng");
//        setSize(1300, 600);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        initUI();
//    }
//
//    private void initUI() {
//        // Panel Menu
//        ManagerSidebar sidebar = new ManagerSidebar();
//        add(sidebar, BorderLayout.WEST);
//
//        // Main Panel
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BorderLayout());
//
//        // Thanh filter
//        FilterPanel topPanel = new FilterPanel();
//        mainPanel.add(topPanel, BorderLayout.NORTH);
//
//        // Panel danh sách khách hàng
//        String[] columns = {"", "Mã KH", "Tên Khách Hàng", "SĐT", "Email", "Địa chỉ", "Ngày đăng ký"};
//        Object[][] data = {
//            {false, "<html><b style='color:#007bff;'>KH001</b></html>",
//                "<html><b>Trần Văn Nam</b><br><span style='color:gray;'>Nam, 35 tuổi</span></html>",
//                "0912345678", "namtv@gmail.com", "Số 10, Đường Láng, Hà Nội", "10/01/2023"},
//            {false, "<html><b style='color:#007bff;'>KH002</b></html>",
//                "<html><b>Lê Thị Hoa</b><br><span style='color:gray;'>Nữ, 28 tuổi</span></html>",
//                "0987654321", "hoalt@gmail.com", "25 Nguyễn Trãi, Thanh Xuân, Hà Nội", "22/03/2023"}
//        };
//        TableList listCustomer = new TableList(columns, data);
//        mainPanel.add(listCustomer, BorderLayout.CENTER);
//        add(mainPanel, BorderLayout.CENTER);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new GUI_QLKH().setVisible(true));
//    }
//}

package appgiaovan.ManagerGUI;
import appgiaovan.Controller.QLKHController;
import appgiaovan.DAO.KhachHangDAO;
import appgiaovan.Entity.KhachHang;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class GUI_QLKH extends JFrame {
    private QLKHController controller;
    private JTable tblKhachHang;
    private JTextField txtSearch;

    public GUI_QLKH() {
        controller = new QLKHController();
        setTitle("Quản Lý Khách Hàng");
        setSize(1300, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        // filter panel
        JPanel pnlTop = new JPanel();
        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(e -> xuLiYeuCauTimKiemKhachHang());
        pnlTop.add(txtSearch);
        pnlTop.add(btnSearch);
        add(pnlTop, BorderLayout.NORTH);

        // table panel
        tblKhachHang = new JTable();
        add(new JScrollPane(tblKhachHang), BorderLayout.CENTER);

        // sidebar actions
        JButton btnAdd = new JButton("Thêm");
        btnAdd.addActionListener(e -> {
            FormThemKH form = new FormThemKH(this);
            form.setVisible(true);
            hienThiDanhSachKhachHang();
        });
        JButton btnEdit = new JButton("Sửa");
        btnEdit.addActionListener(e -> xuLiLayThongTinKhachHang());
        JButton btnDelete = new JButton("Xóa");
        btnDelete.addActionListener(e -> xuLiXoaKhachHang());

        JPanel pnlButtons = new JPanel();
        pnlButtons.add(btnAdd);
        pnlButtons.add(btnEdit);
        pnlButtons.add(btnDelete);
        add(pnlButtons, BorderLayout.SOUTH);

        // initial load
        hienThiDanhSachKhachHang();
    }

    public void hienThiDanhSachKhachHang() {
        List<KhachHang> list = controller.layTatCaKhachHang();
        // convert to table model and set
        tblKhachHang.setModel(new KhachHangTableModel(list));
    }

    public void xuLiYeuCauTimKiemKhachHang() {
        String kw = txtSearch.getText();
        List<KhachHang> list = controller.timKiemKhachHang(kw);
        tblKhachHang.setModel(new KhachHangTableModel(list));
    }

    public void xuLiLayThongTinKhachHang() {
        int row = tblKhachHang.getSelectedRow();
        if (row < 0) return;
        int id = (int) tblKhachHang.getValueAt(row, 1); // column Mã KH
        FormSuaKH form = new FormSuaKH(this, controller.layThongTinKhachHang(id));
        form.setVisible(true);
        hienThiDanhSachKhachHang();
    }

    public void xuLiXoaKhachHang() {
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
            new GUI_QLKH().setVisible(true);
        });
    }
}
