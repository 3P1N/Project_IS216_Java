/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.ManagerGUI;

/**
 *
 * @author pc
 */
import appgiaovan.ManagerGUI.ManagerSidebar;

import appgiaovan.GUI.Components.RoundedPanel;
import appgiaovan.GUI.Components.MenuBar;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.data.category.*;



public class ManagerMainScreen extends JPanel {
    public ManagerMainScreen() {
        
        setLayout(new BorderLayout());

        // Sidebar trái
      

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

 // Biểu đồ nâng cấp sử dụng JFreeChart với custom renderer
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // Dữ liệu mẫu
        dataset.addValue(0, "Doanh thu", "01/05");
        dataset.addValue(2, "Doanh thu", "04/05");
        dataset.addValue(8, "Doanh thu", "07/05");
        dataset.addValue(3, "Doanh thu", "10/05");
        dataset.addValue(6, "Doanh thu", "12/05");
        dataset.addValue(7, "Doanh thu", "15/05");

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Biểu đồ doanh thu (triệu VND)",
                "Ngày", "Doanh thu", dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        // Tùy chỉnh plot
        CategoryPlot plot = lineChart.getCategoryPlot();
        plot.setBackgroundPaint(new GradientPaint(0, 0, new Color(245, 245, 245), 0, 600, Color.WHITE));
        plot.setOutlineVisible(false);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(new Color(200, 200, 200));
        plot.setDomainGridlinesVisible(false);

        // Tùy chỉnh renderer
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(33, 150, 243));
        renderer.setSeriesStroke(0, new BasicStroke(3f));
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-4, -4, 8, 8));
        plot.setRenderer(renderer);

        // Tùy chỉnh trục
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
        domainAxis.setAxisLineVisible(false);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
        rangeAxis.setAxisLineVisible(false);

        // Thêm chart vào panel
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(900, 350));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(chartPanel, BorderLayout.CENTER);


        // Thêm vào JFrame
       
        add(mainPanel, BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Không thể cài đặt FlatLaf");
        }
        SwingUtilities.invokeLater(() -> {
            new ManagerMainScreen().setVisible(true);
        });
    }
}
