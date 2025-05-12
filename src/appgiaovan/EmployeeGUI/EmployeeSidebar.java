package appgiaovan.EmployeeGUI;

import appgiaovan.GUI.Components.MenuBar;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EmployeeSidebar extends JPanel {

    public EmployeeSidebar() {
        // Khai báo danh sách tiêu đề và icon
        List<String> items = Arrays.asList("Trang chủ", "Báo cáo", "Quản lý đơn hàng", "Quản lý gói hàng", "Đăng xuất");
        List<String> icons = Arrays.asList("home.png","report.png", "order.png", "package.png", "logout.png");

        // Thiết lập layout cho Sidebar
        setLayout(new BorderLayout());

        // Tạo MenuBar và add vào chính JPanel này
        MenuBar menu = new MenuBar(items, icons);
        add(menu, BorderLayout.CENTER);

        // Optionally set preferred size
        setPreferredSize(menu.getPreferredSize());
    }
}
