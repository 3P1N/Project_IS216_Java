/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.ManagerGUI;

/**
 *
 * @author pc
 */
import appgiaovan.DAO.DoanhThuLoiNhuanDAO;
import appgiaovan.Entity.DoanhThuLoiNhuan;
import appgiaovan.ManagerGUI.ManagerSidebar;

import appgiaovan.GUI.Components.RoundedPanel;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.data.category.*;

public class ManagerMainScreen extends JFrame {

    private DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    public ManagerMainScreen() throws SQLException, ClassNotFoundException {
        setTitle("Quản lý - Home");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar trái
        ManagerSidebar sidebar = new ManagerSidebar();

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

        List<DoanhThuLoiNhuan> list = new DoanhThuLoiNhuanDAO().getListDoanhThuLoiNhuan();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");

        for (DoanhThuLoiNhuan dtln : list) {
            String ngay = sdf.format(dtln.getNgay());
            dataset.addValue(dtln.getDoanhThu(), "Doanh thu", ngay);
            dataset.addValue(dtln.getLoiNhuan(), "Lợi nhuận", ngay);
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Biểu đồ Doanh thu và Lợi nhuận (triệu VND)",
                "Ngày", "Giá trị", dataset,
                PlotOrientation.VERTICAL,
                true, // Hiển thị chú thích (legend) để phân biệt 2 dòng
                true, // Tooltips
                false // URLs
        );

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
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        int delay = 600; // 60000 ms = 1 phút
        new javax.swing.Timer(delay, e -> loadChartData()).start();

    }

    public void loadChartData() {
        try {
            List<DoanhThuLoiNhuan> list = new DoanhThuLoiNhuanDAO().getListDoanhThuLoiNhuan();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
            dataset.clear();  // Xóa dữ liệu cũ trước khi thêm mới

            for (DoanhThuLoiNhuan dtln : list) {
                String ngay = sdf.format(dtln.getNgay());
                dataset.addValue(dtln.getDoanhThu(), "Doanh thu", ngay);
                dataset.addValue(dtln.getLoiNhuan(), "Lợi nhuận", ngay);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Không thể cài đặt FlatLaf");
        }
        SwingUtilities.invokeLater(() -> {
            try {
                new ManagerMainScreen().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(ManagerMainScreen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ManagerMainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
