package appgiaovan.EmployeeGUI;

import appgiaovan.Controller.QLDonHangController;
import appgiaovan.Entity.DonHang;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuanLyDonHangPanel extends JPanel {

    private final QLDonHangController controller = new QLDonHangController();
    private TableDonHang listOrder;
    private TopPanelQLDH topPanel;

    public QuanLyDonHangPanel() throws SQLException, ClassNotFoundException {
        setLayout(new BorderLayout());

        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel lọc (filter)
        topPanel = new TopPanelQLDH();
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Khởi tạo bảng rỗng ban đầu
        String[] columns = DonHang.getTableHeaders();
        Object[][] data = new Object[0][columns.length];

        listOrder = new TableDonHang(columns, data);
        mainPanel.add(listOrder, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        //Gán sự kiện thêm đơn hàng
        topPanel.getaddButton().addActionListener(e -> {

            try {
                ThemDonHang();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //Gán sự kiện tìm kiếm đơn hàng
        topPanel.getfilterButton().addActionListener(e -> {
            try {
                DonHang dh = topPanel.getDonHang();

                HienThiDanhSach(dh);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        topPanel.getupdateButton().addActionListener(e -> {
            try {
                XuLySuaDonHang();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        topPanel.getPhanCongButton().addActionListener(e-> PhanCongGiaoHang());

        // Hiển thị danh sách ngay khi mở panel
        HienThiDanhSach();
    }
    
    public void PhanCongGiaoHang(){
        
    }

    public void XuLySuaDonHang() throws SQLException, ClassNotFoundException, Exception  {
        for (int i = 0; i < listOrder.getRowCount(); i++) {
            Boolean isChecked = (Boolean) listOrder.getValueAt(i, 0); // Cột 0 là checkbox
            if (Boolean.TRUE.equals(isChecked)) {
                // Lấy thông tin dòng được chọn
                Integer maDonHang = (Integer) listOrder.getValueAt(i, 1); // cột 1: mã ĐH
                System.out.println(maDonHang);

                // Gọi hàm xử lý
                SuaDonHang(maDonHang);

            }
        }
    }

    public void SuaDonHang(int idDonHang) throws SQLException, ClassNotFoundException, Exception {
        SuaDonHangFrame suaDonHangFrame = new SuaDonHangFrame(idDonHang, () -> {
            try {
                HienThiDanhSach();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void ThemDonHang() throws SQLException, ClassNotFoundException, Exception {
        ThemDonHangFrame themDH = new ThemDonHangFrame(() -> {
            try {
                HienThiDanhSach();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

//        themDH.setVisible(true);
    }

    public final void HienThiDanhSach() throws SQLException, ClassNotFoundException {
        List<DonHang> dsDonHang = controller.LayDSDonHang();
        String[] columns = DonHang.getTableHeaders();
        Object[][] data = new Object[dsDonHang.size()][columns.length];

        for (int i = 0; i < dsDonHang.size(); i++) {
            data[i] = dsDonHang.get(i).toTableRow();
        }

        listOrder.setTableData(data);
    }

    public final void HienThiDanhSach(DonHang dh) throws SQLException, ClassNotFoundException {
        List<DonHang> dsDonHang = controller.LayDSDonHang(dh);
        String[] columns = DonHang.getTableHeaders();
        Object[][] data = new Object[dsDonHang.size()][columns.length];

        for (int i = 0; i < dsDonHang.size(); i++) {
            data[i] = dsDonHang.get(i).toTableRow();
        }

        listOrder.setTableDataDonHang(dsDonHang);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Không thể cài đặt FlatLaf");
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Quản Lý Đơn Hàng Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1300, 600);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            try {
                frame.add(new QuanLyDonHangPanel(), BorderLayout.CENTER);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Không thể kết nối cơ sở dữ liệu!");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.setVisible(true);
        });
    }
}
