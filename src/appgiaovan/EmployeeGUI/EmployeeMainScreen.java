package appgiaovan.EmployeeGUI;

import appgiaovan.GUI.Components.RoundedPanel;
import appgiaovan.GUI.Components.MenuBar;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

public class EmployeeMainScreen extends JFrame {

    public EmployeeMainScreen() {
        setTitle("Trang tổng quan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar trái
        EmployeeSidebar sidebar = new EmployeeSidebar();
        

        // Khu vực trung tâm (dashboard)
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Các ô thống kê
        JPanel statPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        statPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        statPanel.add(RoundedPanel.createStatBox("DOANH THU", "0", "↓ 100%", new Color(76, 175, 80)));
        statPanel.add(RoundedPanel.createStatBox("BÁN LẺ", "0", "↓ 100% (0 hóa đơn)", new Color(33, 150, 243)));
        statPanel.add(RoundedPanel.createStatBox("ĐƠN HÀNG", "0", "↓ 100% (0 đơn)", new Color(255, 152, 0)));
        statPanel.add(RoundedPanel.createStatBox("TỒN KHO", "2.9 tỷ", "7.309 sản phẩm", new Color(121, 85, 72)));

        mainPanel.add(statPanel, BorderLayout.NORTH);

        // Biểu đồ (sử dụng JFreeChart)
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(0, "Doanh thu", "01");
        dataset.addValue(2, "Doanh thu", "04");
        dataset.addValue(8, "Doanh thu", "07");
        dataset.addValue(3, "Doanh thu", "08");
        dataset.addValue(6, "Doanh thu", "12");
        dataset.addValue(0, "Doanh thu", "20");
        dataset.addValue(7, "Doanh thu", "25");

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Biểu đồ doanh thu",
                "Ngày",
                "Doanh thu (triệu VND)",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(800, 300));
        mainPanel.add(chartPanel, BorderLayout.CENTER);

        // Thêm vào JFrame
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }

    

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Không thể cài đặt FlatLaf");
        }
        SwingUtilities.invokeLater(() -> {
            new EmployeeMainScreen().setVisible(true);
        });
    }
}
