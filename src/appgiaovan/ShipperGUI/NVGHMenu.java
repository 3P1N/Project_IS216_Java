package appgiaovan.ShipperGUI;

import appgiaovan.EmployeeGUI.EmployeeSidebar;
import appgiaovan.GUI.Components.RoundedPanel;
import appgiaovan.GUI.Components.MenuBar;
import appgiaovan.GUI.Components.MenuBar.MenuClickListener;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NVGHMenu extends JPanel {

    private MenuBar menu;

    public NVGHMenu(int idtk) throws SQLException, ClassNotFoundException {
        java.util.List<String> items = Arrays.asList("Trang chủ","Quản lý đơn hàng", "Báo cáo","Thông tin cá nhân", "Hỗ trợ", "Đăng xuất");
        java.util.List<String> icons = Arrays.asList("home.jpg","order.png", "report.png","employee.png", "support.jpg", "logout.png");

        setLayout(new BorderLayout());

        menu = new MenuBar(items, icons, idtk);
        add(menu, BorderLayout.WEST);
        setPreferredSize(menu.getPreferredSize());
    }

    public void addMenuClickListener(MenuClickListener listener) {
        menu.addMenuClickListener(listener); // Gọi tới MenuBar để gán listener
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new NVGHMenu(29).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(NVGHMenu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NVGHMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
