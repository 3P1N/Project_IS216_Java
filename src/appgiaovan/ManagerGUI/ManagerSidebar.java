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
public class ManagerSidebar extends MenuBar {
    private static final List<String> items = Arrays.asList("Trang chủ", "Báo cáo", "Quản lý đơn hàng", "Quản lý gói hàng", "Đăng xuất");
    private static final List<String> icons = Arrays.asList("home.png", "report.png", "order.png", "package.png", "logout.png");

    public ManagerSidebar() {
        super(ManagerSidebar.items, ManagerSidebar.icons);
    }
}



