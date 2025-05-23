package appgiaovan.ManagerGUI;

import appgiaovan.GUI.Components.MenuBar;
import appgiaovan.GUI.Components.MenuBar.MenuClickListener;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import appgiaovan.ManagerGUI.GUI_QLKH;
//import appgiaovan.ManagerGUI.GUI_QLNVKho;
//import appgiaovan.ManagerGUI.GUI_QLNVGiaoHang;
//import appgiaovan.ManagerGUI.GUI_BaoCao;
//import appgiaovan.ManagerGUI.GUI_ThongKe;
//import appgiaovan.ManagerGUI.GUI_BangLuong;

public class ManagerSidebar extends JPanel {
    private final MenuBar menu;

    public ManagerSidebar(JFrame parent) {
        setLayout(new BorderLayout());
        List<String> items = Arrays.asList(
            "Quản lý nhân viên", "Quản lý khách hàng", "Xem báo cáo",
            "Báo cáo thống kê", "Xem bảng lương", "Đăng xuất"
        );
        List<String> icons = Arrays.asList(
            "employee.png", "customer.png", "report.png",
            "statistic.png", "salary.png", "logout.png"
        );
        menu = new MenuBar(items, icons);
        add(menu, BorderLayout.CENTER);
        setPreferredSize(menu.getPreferredSize());

        // Đăng ký xử lý menu click ngay trong đây
        menu.addMenuClickListener(menuName -> {
            // Đóng frame hiện tại
            parent.dispose();
            try {
                switch (menuName) {
//                    case "Quản lý nhân viên":
//                        new GUI_QLNVKho().setVisible(true);
//                        break;
                    case "Quản lý khách hàng":
                        new GUI_QLKH().setVisible(true);
                        break;
//                    case "Xem báo cáo":
//                        new GUI_BaoCao().setVisible(true);
//                        break;
//                    case "Báo cáo thống kê":
//                        new GUI_ThongKe().setVisible(true);
//                        break;
//                    case "Xem bảng lương":
//                        new GUI_BangLuong().setVisible(true);
//                        break;
//                    case "Đăng xuất":
//                        System.exit(0);
//                        break;
                    default:
                        break;
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ManagerSidebar.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}