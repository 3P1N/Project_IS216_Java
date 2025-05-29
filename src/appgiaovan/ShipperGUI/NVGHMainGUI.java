/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.ShipperGUI;

import appgiaovan.EmployeeGUI.EmployeeSidebar;
import appgiaovan.GUI.Components.RoundedPanel;
import appgiaovan.GUI.Components.MenuBar;
import appgiaovan.GUI.Components.ThongTinCaNhan;
import appgiaovan.GUI.Components.TimeWeather;
import appgiaovan.GUI.LOGIN;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import appgiaovan.ShipperGUI.NVGHMenu;
import com.formdev.flatlaf.FlatLightLaf;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import appgiaovan.Controller.TokenController;

/**
 *
 * @author ASUS
 */
public class NVGHMainGUI extends JFrame{
    private CardLayout cardLayout;
    private JPanel contentPanel;

    public NVGHMainGUI(int idtk, int idToken) throws ClassNotFoundException, SQLException {
        
        setTitle("3P1N - Nhân viên giao hàng");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Danh sách tên và icon menu
        
        // Tạo menu
        NVGHMenu sidebar = new NVGHMenu(idtk);
        add(sidebar, BorderLayout.WEST);

        // Panel trung tâm hiển thị nội dung
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Thêm các trang nội dung
        //contentPanel.add(new ThongTinCaNhan(),"Profile");
        //contentPanel.add(new NVGHHomeGUI(idtk),"Trang chủ");
        
        contentPanel.add(new QuanLyDonHang(idtk),"Quản lý đơn hàng");
        contentPanel.add(new NVGHBaoCao(idtk), "Báo cáo");
        contentPanel.add(new ThongTinCaNhan(),"Thông tin cá nhân");
        contentPanel.add(new NVGHHotro(), "Hỗ trợ");
        //contentPanel.add(new LOGIN(), "Đăng xuất");

        add(contentPanel);

        // Khi chọn mục trong MenuBar thì đổi trang
        
        sidebar.addMenuClickListener((selectedName) -> {
            if (selectedName.equals("Đăng xuất")) {
                int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn đăng xuất không?",
                    "Xác nhận đăng xuất",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                    try {
                        try {
                            new TokenController().capNhatToken(idToken);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(NVGHMainGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(NVGHMainGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    SwingUtilities.invokeLater(() -> new LOGIN().setVisible(true));
                }
            } else {
                cardLayout.show(contentPanel, selectedName);
            }
        });

    }

//    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(new FlatLightLaf());
//        } catch (Exception ex) {
//            System.err.println("Không thể cài đặt FlatLaf");
//        }
//        SwingUtilities.invokeLater(() -> {
//            try {
//                try {
//                    new NVGHMainGUI(1).setVisible(true);
//                } catch (SQLException ex) {
//                    Logger.getLogger(NVGHMainGUI.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(NVGHMainGUI.class.getName()).log(Level.SEVERE, null, ex);
//            }
//           
//        });
//    }

}
