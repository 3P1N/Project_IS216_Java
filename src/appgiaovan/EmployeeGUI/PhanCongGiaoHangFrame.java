package appgiaovan.EmployeeGUI;

import appgiaovan.Controller.QLDonHangController;
import appgiaovan.Entity.*;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhanCongGiaoHangFrame extends JFrame {

    private QLDonHangController controller = new QLDonHangController();
    private TableDonHang listOrder;
    private TopPanelPCGH topPanel;

    public PhanCongGiaoHangFrame(Runnable onSuccess) throws SQLException, ClassNotFoundException {
        setTitle("Phân Công Giao Hàng");
        setSize(1300, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI(onSuccess);
    }

    private void initUI(Runnable onSuccess) throws SQLException, ClassNotFoundException {

        //main
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //thanh filter
        topPanel = new TopPanelPCGH();
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel danh sách
        String[] columns = DonHang.getTableHeaders();
        Object[][] data = new Object[0][columns.length];

        listOrder = new TableDonHang(columns, data);
        mainPanel.add(listOrder, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        topPanel.getBtnXacNhan().addActionListener(e -> {
            try {
                SelectShipper(onSuccess);
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        HienThiDSDonHangDangXuLy();
    }

    public void SelectShipper(Runnable onSuccess) throws SQLException, ClassNotFoundException {
        NhanVienGiaoHang nv = topPanel.getNVGiaoHang();
        List<Integer> listIdDonHang = new ArrayList<>();

        for (int i = 0; i < listOrder.getRowCount(); i++) {
            Boolean isChecked = (Boolean) listOrder.getValueAt(i, 0); // Cột 0 là checkbox
            if (Boolean.TRUE.equals(isChecked)) {
                // Lấy thông tin dòng được chọn
                Integer maDonHang = (Integer) listOrder.getValueAt(i, 1); // cột 1: mã ĐH
                listIdDonHang.add(maDonHang);

            }
        }
        controller.PhanCongGiaoHang(nv, listIdDonHang);
        JOptionPane.showMessageDialog(this, "Tạo đơn hàng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);

        HienThiDSDonHangDangXuLy();
    }

    public void HienThiDSDonHangDangXuLy() throws SQLException, ClassNotFoundException {
        DonHang donHang = new DonHang();
        donHang.setTrangThai("Đang xử lý");
        List<DonHang> dsDonHang = controller.LayDSDonHang(donHang);
        String[] columns = DonHang.getTableHeaders();
        Object[][] data = new Object[dsDonHang.size()][columns.length];

        for (int i = 0; i < dsDonHang.size(); i++) {
            data[i] = dsDonHang.get(i).toTableRow();
        }

        listOrder.setTableData(data);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Không thể cài đặt FlatLaf");
        }
        SwingUtilities.invokeLater(() -> {
            try {
                new PhanCongGiaoHangFrame(() -> System.out.println("Thêm gói hàng!")).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(PhanCongGiaoHangFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PhanCongGiaoHangFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
