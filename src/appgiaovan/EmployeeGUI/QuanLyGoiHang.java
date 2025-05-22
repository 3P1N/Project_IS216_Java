package appgiaovan.EmployeeGUI;

import appgiaovan.Controller.QLGHController;
import appgiaovan.Entity.DonHang;
import appgiaovan.Entity.GoiHang;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuanLyGoiHang extends JPanel {

    private TopPanelQLGH topPanel ;
    private TableDonHang listOrder;
    private QLGHController controller = new QLGHController();
    public QuanLyGoiHang() throws SQLException, ClassNotFoundException, Exception {
        this.setLayout(new BorderLayout());
        initUI();
    }

    private void initUI() throws SQLException, ClassNotFoundException, Exception {
        // Panel Menu
        topPanel = new TopPanelQLGH();
        // main
        JPanel mainPanel = new JPanel(new BorderLayout());

        // thanh filter
        mainPanel.add(topPanel, BorderLayout.NORTH);

        String[] columns = GoiHang.getTableHeaders();
        Object[][] data = new Object[0][columns.length];
        listOrder = new TableDonHang(columns, data);
        mainPanel.add(listOrder, BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);
        
         topPanel.getaddButton().addActionListener(e -> {
            try {
                ThemGoiHang();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
         topPanel.getfilterButton().addActionListener(e -> {
            try {
                GoiHang goiHang = topPanel.getGoiHang();
                HienThiDSGoiHang(goiHang);
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        HienThiDSGoiHang();
    }

    void HienThiDSGoiHang() throws SQLException, ClassNotFoundException{
        java.util.List<GoiHang> dsGoiHang = controller.LayDSGoiHang();
        String[] columns = GoiHang.getTableHeaders();
        Object[][] data = new Object[dsGoiHang.size()][columns.length];

        for (int i = 0; i < dsGoiHang.size(); i++) {
            data[i] = dsGoiHang.get(i).toTableRow();
        }

        listOrder.setTableData(data);
    }
    
    void HienThiDSGoiHang(GoiHang goiHang) throws SQLException, ClassNotFoundException{
        java.util.List<GoiHang> dsGoiHang = controller.LayDSGoiHang(goiHang);
        String[] columns = GoiHang.getTableHeaders();
        Object[][] data = new Object[dsGoiHang.size()][columns.length];

        for (int i = 0; i < dsGoiHang.size(); i++) {
            data[i] = dsGoiHang.get(i).toTableRow();
        }

        listOrder.setTableData(data);
    }
    
    public void ThemGoiHang() throws SQLException, ClassNotFoundException{
        ThemGoiHangFrame themGoiHang = new ThemGoiHangFrame(() -> {
            try {
                HienThiDSGoiHang();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyGoiHang.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuanLyGoiHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        themGoiHang.setVisible(true);
    }
    // Dùng để test panel trong một JFrame
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Không thể cài đặt FlatLaf");
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản Lý Gói Hàng");
            try {
                frame.setContentPane(new QuanLyGoiHang());
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyGoiHang.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuanLyGoiHang.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyGoiHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.setSize(1300, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
