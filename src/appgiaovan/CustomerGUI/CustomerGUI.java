/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appgiaovan.CustomerGUI;
import appgiaovan.Controller.DangKyController;
import appgiaovan.CustomerGUI.KHTaoDonHangPanel;
import appgiaovan.Entity.TaiKhoan;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author nhant
 */
public class CustomerGUI extends JFrame {
    private DangKyController dangKyController=new DangKyController();
    private CardLayout cardLayout;
    private JPanel contentPanel;
    public CustomerGUI(int ID_TaiKhoan) throws SQLException, ClassNotFoundException, Exception{
        int ID_KhachHang=dangKyController.layID_KhachHang(ID_TaiKhoan);
        setTitle("Giao diện chính");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        //Thêm sidebar
        CustomerSidebar sidebar = new CustomerSidebar(ID_TaiKhoan);
        add(sidebar, BorderLayout.WEST);
        // Thêm panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        // Panel trung tâm hiển thị nội dung
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        // Thêm các trang panel
        contentPanel.add(new KhachHangMainPanel(ID_KhachHang),"Trang chủ");
        contentPanel.add(new KHTaoDonHangPanel(ID_KhachHang),"Tạo đơn hàng");
        contentPanel.add(new TraCuuDonHangPanel(ID_KhachHang),"Tra cứu đơn hàng");
        contentPanel.add(new ThongTinCaNhanPanel(ID_KhachHang),"Thông tin cá nhân");
        add(contentPanel,BorderLayout.CENTER);
        sidebar.addMenuClickListener((selectedName) -> {
            cardLayout.show(contentPanel, selectedName);
        });
        
    }
    public int layID_KhachHang(int ID_TaiKhoan) throws SQLException{
        try {
            return dangKyController.layID_KhachHang(ID_TaiKhoan);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    /*public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("Không thể cài đặt FlatLaf");
        }

        SwingUtilities.invokeLater(() -> {
            try {
                new CustomerGUI(11).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(CustomerGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CustomerGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }*/
    
}
