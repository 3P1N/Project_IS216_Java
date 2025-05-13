package appgiaovan.ShipperGUI;

import appgiaovan.EmployeeGUI.EmployeeSidebar;
import appgiaovan.GUI.Components.RoundedPanel;
import appgiaovan.GUI.Components.MenuBar;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShipperMenu extends JPanel {

    public ShipperMenu() {
       /* setTitle("Shipper - 3P1N đơn vị giao vận");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());*/

        // Khai báo danh sách tiêu đề và icon
        java.util.List<String> items = Arrays.asList("Trang chủ","Quản lý đơn hàng", "Báo cáo", "Hỗ trợ", "Đăng xuất");
        java.util.List<String> icons = Arrays.asList("home.jpg","order.png", "report.png", "support.jpg", "logout.png");

        // Thiết lập layout cho Sidebar
        setLayout(new BorderLayout());

        // Tạo MenuBar và add vào chính JPanel này
        MenuBar menu = new MenuBar(items, icons);
        add(menu, BorderLayout.WEST);
        // Optionally set preferred size
        setPreferredSize(menu.getPreferredSize());

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ShipperMenu().setVisible(true);
        });
    }
}
