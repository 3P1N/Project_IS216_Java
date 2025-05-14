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
import appgiaovan.GUI.Components.TimeWeather;
 public class CustomerMainScreen extends JFrame {

    public CustomerMainScreen(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        // Sidebar trái
        CustomerSidebar sidebar = new CustomerSidebar();
        //Khu vuc trung tâm
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Thêm vào JFrame
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
        //Thanh Weather
        TimeWeather CustomerTimeWeather= new TimeWeather("Ho Chi Minh 30 độ");
        JPanel statPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        statPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        statPanel.add(RoundedPanel.createStatBox("Tổng số đơn", "20", "", new Color(76, 175, 80)));
        statPanel.add(RoundedPanel.createStatBox("Đã giao", "5", "", new Color(33, 150, 243)));
        statPanel.add(RoundedPanel.createStatBox("Đang vận chuyển", "15", "", new Color(255, 152, 0)));

        mainPanel.add(statPanel, BorderLayout.NORTH);

        // Biểu đồ (sử dụng JFreeChart)
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(20, "Số lượng", "Tổng đơn");
        dataset.addValue(5, "Số lượng", "Đang giao");
        dataset.addValue(15, "Số lượng", "Đã giao");

        JFreeChart barChart = ChartFactory.createBarChart(
        "Thống kê đơn hàng",
        "Trạng thái",
        "Số đơn",
        dataset,
        PlotOrientation.VERTICAL,
        false, true, false
    );

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 300));
        mainPanel.add(chartPanel, BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Không thể cài đặt FlatLaf");
        }
        SwingUtilities.invokeLater(() -> {
            new CustomerMainScreen().setVisible(true);
        });
    }
    
}
