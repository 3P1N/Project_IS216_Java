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
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author pc
 */
public class ManagerSidebar extends MenuBar {
    private static final List<String>  items = Arrays.asList("Trang chủ", "Quản lý nhân viên kho","Quản lý shipper", "Quản lý khách hàng","Xem báo cáo", "Báo cáo thống kê", "Xem bảng lương", "Đăng xuất");
      
    private static final List<String>  icons = Arrays.asList("home.png", "employee.png", "employee.png", "customer.png","report.png", "statistic.png", "salary.png", "logout.png");

    public ManagerSidebar() throws SQLException, ClassNotFoundException {
        super(ManagerSidebar.items, ManagerSidebar.icons, 1);
    }



    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame("Giao diện Quản Lý");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1300, 600);
            frame.setLocationRelativeTo(null); // căn giữa màn hình

            // Thêm sidebar vào frame
            ManagerSidebar sidebar = new ManagerSidebar();
            frame.add(sidebar, BorderLayout.WEST); // hoặc CENTER tùy theo layout bạn muốn
            frame.setVisible(true);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }


    
}



