package appgiaovan.EmployeeGUI;

import appgiaovan.GUI.Components.RoundedPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class EmployeeMainPanel extends JPanel {

    public EmployeeMainPanel() {
        setLayout(new BorderLayout());

        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Các ô thống kê
        JPanel statPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        statPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        statPanel.add(RoundedPanel.createStatBox("DOANH THU THÁNG", danggiao, "\u2193 100%", new Color(76, 175, 80)));
        statPanel.add(RoundedPanel.createStatBox("TỔNG ĐƠN HÀNG THÁNG", danggiao, "\u2193 100%", new Color(255, 152, 0)));
        statPanel.add(RoundedPanel.createStatBox("TỒN KHO", danggiao, "\u2193 100%", new Color(121, 85, 72)));

        mainPanel.add(statPanel, BorderLayout.NORTH);

        // Biểu đồ doanh thu
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

        // Thêm mainPanel vào JPanel gốc
        add(mainPanel, BorderLayout.CENTER);
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

            frame.add(new EmployeeMainPanel(), BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }


}
