
package appgiaovan.ManagerGUI;

import appgiaovan.EmployeeGUI.*;
import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.GUI.Components.ThongTinCaNhan;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ManagerGUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;

    public ManagerGUI() throws SQLException, ClassNotFoundException {
        
        setTitle("Giao diện chính");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Danh sách tên và icon menu
        
        // Tạo menu
        ManagerSidebar sidebar = new ManagerSidebar();
        add(sidebar, BorderLayout.WEST);

        // Panel trung tâm hiển thị nội dung
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Thêm các trang nội dung
        contentPanel.add(new ManagerMainScreen(),"Trang chủ");
        contentPanel.add(new GUI_QLNVKho(),"Quản lý nhân viên kho");
        contentPanel.add(new GUI_QLShipper(),"Quản lý shipper");
        contentPanel.add(new GUI_QLKH(),"Quản lý khách hàng");

        contentPanel.add(new GUI_XemBaoCao(), "Xem báo cáo");
        contentPanel.add(new ThongKePanel(),"Báo cáo thống kê");

//        contentPanel.add(new QuanLyGoiHang(), "Quản lý gói hàng");

        add(contentPanel, BorderLayout.CENTER);

        // Khi chọn mục trong MenuBar thì đổi trang
        sidebar.addMenuClickListener((selectedName) -> {
            cardLayout.show(contentPanel, selectedName);
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Không thể cài đặt FlatLaf");
        }
        SwingUtilities.invokeLater(() -> {
            try {
                new ManagerGUI().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(ManagerGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ManagerGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
