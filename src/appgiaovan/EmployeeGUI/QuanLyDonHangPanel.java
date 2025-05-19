package appgiaovan.EmployeeGUI;

import appgiaovan.Controller.QLDonHangController;
import appgiaovan.Entity.DonHang;
import appgiaovan.GUI.Components.TableList;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuanLyDonHangPanel extends JPanel {

    private final QLDonHangController controller = new QLDonHangController();
    private final TableList listOrder;
    private final TopPanelQLGH topPanel = new TopPanelQLGH();

    public QuanLyDonHangPanel() throws SQLException, ClassNotFoundException {
        setLayout(new BorderLayout());

        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel lọc (filter)
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Khởi tạo bảng rỗng ban đầu
        String[] columns = DonHang.getTableHeaders();
        Object[][] data = new Object[0][columns.length];

        listOrder = new TableList(columns, data);
        mainPanel.add(listOrder, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        
        //Gán sự kiện thêm đơn hàng
        topPanel.getaddButton().addActionListener(e -> {
            
            try {
                ThemDonHang();
                HienThiDanhSach();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        //Gán sự kiện tìm kiếm đơn hàng
        topPanel.getfilterButton().addActionListener(e ->{
            try {
                HienThiDanhSach();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        

        // Hiển thị danh sách ngay khi mở panel
        HienThiDanhSach();
    }
    
    public void ThemDonHang() throws SQLException, ClassNotFoundException{
        ThemDonHangFrame themDH = new ThemDonHangFrame(() -> {
            try {
                HienThiDanhSach();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Quản Lý Đơn Hàng Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1300, 600);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            try {
                frame.add(new QuanLyDonHangPanel(), BorderLayout.CENTER);
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.setVisible(true);
        });
    }
}
