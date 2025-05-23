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
    private static final List<String>  items = Arrays.asList("Trang chủ","Quản lý nhân viên", "Quản lý khách hàng","Xem báo cáo", "Báo cáo thống kê", "Xem bảng lương", "Đăng xuất");
      
    private static final List<String>  icons = Arrays.asList("home.png", "employee.png", "customer.png","report.png", "statistic.png", "salary.png", "logout.png");

    public ManagerSidebar() {
         super(ManagerSidebar.items, ManagerSidebar.icons);
    }
   
    
}



