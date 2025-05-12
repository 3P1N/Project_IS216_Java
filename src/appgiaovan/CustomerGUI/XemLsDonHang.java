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
        //Khu vực trung tâm
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Thêm vào JFrame
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
        //TableList
        String[] columns = {"", "ID", "Số đơn hàng", "Người tạo", "Trạng thái", "Gửi từ", "Gửi đến"};
        Object[][] data = {
            {false, "<html><b style='color:#007bff;'>93900415</b><br><span style='color:gray;font-size:10px;'>10:48 15/04</span><br><span style='color:#007bff;'>Kho 2 - Tháo</span></html>",
                "<html></span><br><b>10</b><br><span></html>",
                "Nhân viên B", "Đang vận chuyển", "<html><b>Kho B</b><br></html>", "<html><b>Kho A</b><br></html>"},
            {false, "<html><b style='color:#007bff;'>93200103</b><br><span style='color:gray;font-size:10px;'>18:48 10/04</span><br><span style='color:#007bff;'>Kho 2 - Tháo</span></html>",
                "<html></span><br><b>10</b><br><span></html>",
                "Nhân viên A", "Đang vận chuyển", "<html><b>Kho A</b><br></html>", "<html><b>Kho B</b><br></html>"}
        };
        TableListDonHang listOrder = new TableListDonHang(columns, data);
        mainPanel.add(listOrder, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
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
