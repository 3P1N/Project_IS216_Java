
package appgiaovan.EmployeeGUI;

import appgiaovan.GUI.Components.ThongTinCaNhan;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class EmployeeGUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;

    public EmployeeGUI() {
        setTitle("Giao diện chính");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Danh sách tên và icon menu
        
        // Tạo menu
        EmployeeSidebar sidebar = new EmployeeSidebar();
        add(sidebar, BorderLayout.WEST);

        // Panel trung tâm hiển thị nội dung
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Thêm các trang nội dung
        contentPanel.add(new ThongTinCaNhan(),"Profile");
        contentPanel.add(new EmployeeMainPanel(),"Trang chủ");
        contentPanel.add(new BaoCao(), "Báo cáo");
        contentPanel.add(new QuanLyDonHangPanel(),"Quản lý đơn hàng");

        contentPanel.add(new QuanLyGoiHang(), "Quản lý gói hàng");

        add(contentPanel, BorderLayout.CENTER);

        // Khi chọn mục trong MenuBar thì đổi trang
        sidebar.addMenuClickListener((selectedName) -> {
            cardLayout.show(contentPanel, selectedName);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EmployeeGUI().setVisible(true);
        });
    }
}
