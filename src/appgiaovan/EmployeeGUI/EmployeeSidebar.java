package appgiaovan.EmployeeGUI;

import appgiaovan.GUI.Components.MenuBar;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class EmployeeSidebar extends MenuBar {

    private static final List<String> items = Arrays.asList("Trang chủ", "Báo cáo", "Quản lý đơn hàng", "Quản lý gói hàng", "Đăng xuất");
    private static final List<String> icons = Arrays.asList("home.png", "report.png", "order.png", "package.png", "logout.png");

    public EmployeeSidebar() {
            super(EmployeeSidebar.items, EmployeeSidebar.icons);
    }
    
}
