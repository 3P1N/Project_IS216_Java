/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appgiaovan.CustomerGUI;
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
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author nhant
 */
public class CustomerGUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    public CustomerGUI(){
        setTitle("Giao diện chính");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        //Thêm sidebar
        CustomerSidebar sidebar = new CustomerSidebar();
        add(sidebar, BorderLayout.WEST);
        // Thêm panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        // Panel trung tâm hiển thị nội dung
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        // Thêm các trang panel
        contentPanel.add(new KhachHangMainPanel(),"Trang chủ");
        contentPanel.add(new TaoDonHangPanel(),"Tạo đơn hàng");
        contentPanel.add(new TraCuuDonHangPanel(),"Tra cứu đơn hàng");
        contentPanel.add(new ThongTinCaNhanPanel(),"Thông tin cá nhân");
        add(contentPanel,BorderLayout.CENTER);
        sidebar.addMenuClickListener((selectedName) -> {
            cardLayout.show(contentPanel, selectedName);
        });
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("Không thể cài đặt FlatLaf");
        }

        SwingUtilities.invokeLater(() -> {
            new CustomerGUI().setVisible(true);
        });
    }
    
}
