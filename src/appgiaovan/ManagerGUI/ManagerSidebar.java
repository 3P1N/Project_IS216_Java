/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.ManagerGUI;
import appgiaovan.GUI.Components.MenuBar;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 *
 * @author pc
 */
public class ManagerSidebar extends JPanel {
    public ManagerSidebar() {
        // Khai báo danh sách tiêu đề và icon
        List<String> items = Arrays.asList("Báo cáo", "Quản lý nhân viên", "Quản lý khách hàng","Xem báo cáo", "Xem bảng lương", "Đăng xuất");
        List<String> icons = Arrays.asList("report.png", "employee.png", "package.png","report.png", "salary.png", "logout.png");

        // Thiết lập layout cho Sidebar
        setLayout(new BorderLayout());

        // Tạo MenuBar và add vào chính JPanel này
        MenuBar menu = new MenuBar(items, icons);
        add(menu, BorderLayout.CENTER);

        // Optionally set preferred size
        setPreferredSize(menu.getPreferredSize());
    }
}



