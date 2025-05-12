/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appgiaovan.CustomerGUI;

import appgiaovan.GUI.Components.MenuBar;
import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author nhant
 */
public class CustomerSidebar extends JPanel {

    public CustomerSidebar() {
        // Khai báo danh sách tiêu đề và icon
        List<String> items = Arrays.asList("Trang chủ", "Tạo đơn hàng", "Tra cứu đơn hàng", "Đăng xuất");
        List<String> icons = Arrays.asList("report.png", "order.png", "package.png", "logout.png");

        // Thiết lập layout cho Sidebar
        setLayout(new BorderLayout());

        // Tạo MenuBar và add vào chính JPanel này
        MenuBar menu = new MenuBar(items, icons);
        add(menu, BorderLayout.CENTER);

        // Optionally set preferred size
        setPreferredSize(menu.getPreferredSize());
    }
}

