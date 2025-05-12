/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appgiaovan.CustomerGUI;

import appgiaovan.EmployeeGUI.EmployeeMainScreen;
import appgiaovan.GUI.Components.RoundedPanel;
import appgiaovan.GUI.Components.MenuBar;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;/**
 *
 * @author nhant
 */
public class XemLsDonHang extends JFrame {

    public XemLsDonHang(){
    setTitle("Trang tổng quan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar trái
        CustomerSidebar sidebar = new CustomerSidebar();
        
        // Thêm vào JFrame
        add(sidebar, BorderLayout.WEST);
    }
    public static void main(String[] args) {
         try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Không thể cài đặt FlatLaf");
        }
        SwingUtilities.invokeLater(() -> {
            new XemLsDonHang().setVisible(true);
        });
    }
}
