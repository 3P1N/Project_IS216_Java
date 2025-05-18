/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appgiaovan.CustomerGUI;

import appgiaovan.GUI.Components.RoundedPanel;
import appgiaovan.GUI.Components.MenuBar;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import appgiaovan.CustomerGUI.ThanhTimKiemDH;
import appgiaovan.GUI.Components.TimeWeather;
public class TraCuuDonHangPanel extends JPanel {

    public TraCuuDonHangPanel(){
        setSize(1000, 600);
        setLayout(new BorderLayout());
        
        //Khu vuc trung tâm
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Thêm vào JFrame
        ThanhTimKiemDH topPanel = new ThanhTimKiemDH();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        
        // Panel danh sách
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
        //Thanh Weather
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Employee Main Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            frame.add(new TraCuuDonHangPanel(), BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
    
}
