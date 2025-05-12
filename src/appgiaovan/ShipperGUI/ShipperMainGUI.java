/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.ShipperGUI;

import appgiaovan.EmployeeGUI.EmployeeSidebar;
import appgiaovan.GUI.Components.RoundedPanel;
import appgiaovan.GUI.Components.MenuBar;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

public class ShipperMainGUI extends JFrame {

    public ShipperMainGUI() {
        setTitle("Shipper - 3P1N đơn vị giao vận");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

         // Khai báo danh sách tiêu đề và icon
        java.util.List<String> items = Arrays.asList("Quản lý đơn hàng","Báo cáo","Hỗ trợ", "Đăng xuất");
        java.util.List<String> icons = Arrays.asList("order.png", "report.png","support.jpg", "logout.png");

        // Thiết lập layout cho Sidebar
        setLayout(new BorderLayout());

        // Tạo MenuBar và add vào chính JPanel này
        MenuBar menu = new MenuBar(items, icons);
        add(menu, BorderLayout.EAST);

        // Optionally set preferred size
        setPreferredSize(menu.getPreferredSize());
        // Khu vực trung tâm (dashboard)
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Các ô thống kê
        JPanel statPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        statPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        statPanel.add(RoundedPanel.createStatBox("ĐÃ NHẬN", "0", "↓ 100%", new Color(76, 175, 80)));
        statPanel.add(RoundedPanel.createStatBox("GIAO THÀNH CÔNG", "0", "↓ 100% (0 hóa đơn)", new Color(33, 150, 243)));
        statPanel.add(RoundedPanel.createStatBox("GIAO THẤT BẠI", "0", "↓ 100% (0 đơn)", new Color(255, 152, 0)));
        statPanel.add(RoundedPanel.createStatBox("DOANH THU", "0", "", new Color(255, 152, 0)));

        mainPanel.add(statPanel, BorderLayout.NORTH);

        // Thêm vào JFrame
        add(menu, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ShipperMainGUI().setVisible(true);
        });
    }
}
