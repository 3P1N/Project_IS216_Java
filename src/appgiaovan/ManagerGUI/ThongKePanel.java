package appgiaovan.ManagerGUI;

import appgiaovan.Controller.ThongKeController;
import appgiaovan.CustomerGUI.ThongTinCaNhanPanel;
import appgiaovan.DAO.DanhGiaDAO;
import appgiaovan.DAO.DoanhThuLoiNhuanDAO;
import appgiaovan.Entity.DoanhThuLoiNhuan;
import appgiaovan.Entity.TK_DanhGia;
import appgiaovan.Entity.TK_DonHang;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class ThongKePanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnXuatPDF;
    private ThongKeController controller;
    private List<Map<String, Object>> duLieuThongKe;

    public ThongKePanel() {
        try {
            this.controller = new ThongKeController();
        } catch (SQLException ex) {
            Logger.getLogger(ThongKePanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThongKePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.duLieuThongKe = controller.LayDuLieuThongKe();

        setLayout(new BorderLayout());

        // TabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Đơn Hàng", createDonHangPanel());
        tabbedPane.addTab("Đánh Giá", createDanhGiaPanel());
        tabbedPane.addTab("Doanh Thu", createDoanhThuPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createDonHangPanel() {
        List<TK_DonHang> list = null;
        try {
            list = controller.getListTKDonHang(); // Lấy dữ liệu
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ThongKePanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        JPanel panel = new JPanel(new BorderLayout());

        if (list == null || list.isEmpty()) {
            panel.add(new JLabel("Không có dữ liệu đơn hàng", JLabel.CENTER), BorderLayout.CENTER);
            return panel;
        }

        // --- Tạo bảng dữ liệu ở trên ---
        String[] columns = {"Ngày", "Tổng đơn hàng", "Đã giao", "Thất bại", "Đã huỷ"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho chỉnh sửa bảng
            }
        };
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (TK_DonHang tk : list) {
            Object[] row = {
                sdf.format(tk.getNgay()),
                tk.getTongSoDonHang(),
                tk.getSoLuongDaGiao(),
                tk.getSoLuongThatBai(),
                tk.getSoLuongDaHuy()
            };
            tableModel.addRow(row);
        }
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 150)); // chiều cao cho bảng

        panel.add(scrollPane, BorderLayout.NORTH);

        // --- Tạo dataset cho biểu đồ cột ---
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        SimpleDateFormat sdfChart = new SimpleDateFormat("dd/MM");
        for (TK_DonHang tk : list) {
            String dateStr = sdfChart.format(tk.getNgay());
            dataset.addValue(tk.getSoLuongDaGiao(), "Đã giao", dateStr);
            dataset.addValue(tk.getSoLuongThatBai(), "Thất bại", dateStr);
            dataset.addValue(tk.getSoLuongDaHuy(), "Đã huỷ", dateStr);
        }

        // Tạo biểu đồ cột
        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê đơn hàng theo ngày",
                "Ngày",
                "Số lượng",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Tuỳ chỉnh biểu đồ
        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        renderer.setSeriesPaint(0, new Color(76, 175, 80));  // Đã giao - xanh lá
        renderer.setSeriesPaint(1, new Color(244, 67, 54));  // Thất bại - đỏ
        renderer.setSeriesPaint(2, new Color(255, 152, 0));  // Đã huỷ - cam

        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        // Panel chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(chartPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createDanhGiaPanel() {
        List<TK_DanhGia> list = null;
        try {
            list = controller.getListTKDanhGia(); // Giả sử DAO tồn tại
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ThongKePanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        JPanel panel = new JPanel(new BorderLayout());

        if (list == null || list.isEmpty()) {
            panel.add(new JLabel("Không có dữ liệu đánh giá", JLabel.CENTER), BorderLayout.CENTER);
            return panel;
        }

        // Dùng dữ liệu gần nhất (cuối danh sách)
        TK_DanhGia latest = list.get(list.size() - 1);

        // Tạo PieDataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("1 sao", latest.getSoLuong1Sao());
        dataset.setValue("2 sao", latest.getSoLuong2Sao());
        dataset.setValue("3 sao", latest.getSoLuong3Sao());
        dataset.setValue("4 sao", latest.getSoLuong4Sao());
        dataset.setValue("5 sao", latest.getSoLuong5Sao());

        // Tạo PieChart
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Tỷ lệ đánh giá sao", dataset, true, true, false);

        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setSectionPaint("1 sao", new Color(244, 67, 54));
        plot.setSectionPaint("2 sao", new Color(255, 152, 0));
        plot.setSectionPaint("3 sao", new Color(255, 235, 59));
        plot.setSectionPaint("4 sao", new Color(76, 175, 80));
        plot.setSectionPaint("5 sao", new Color(33, 150, 243));
        plot.setLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
        plot.setSimpleLabels(true);
        plot.setCircular(true);

        // Panel biểu đồ
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(500, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel phía trên biểu đồ: Tổng lượt đánh giá + Ngày báo cáo
        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin tổng quan"));

        JLabel lblTong = new JLabel("Tổng lượt đánh giá:");
        JTextField txtTong = new JTextField(String.valueOf(latest.getTongLuotDanhGia()));
        txtTong.setEditable(false);

        JLabel lblNgay = new JLabel("Ngày báo cáo:");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        JTextField txtNgay = new JTextField(sdf.format(latest.getNgay()));
        txtNgay.setEditable(false);

        infoPanel.add(lblTong);
        infoPanel.add(txtTong);
        infoPanel.add(lblNgay);
        infoPanel.add(txtNgay);

        // Thêm vào panel chính
        panel.add(infoPanel, BorderLayout.NORTH);
        panel.add(chartPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createDoanhThuPanel() {
        List<DoanhThuLoiNhuan> list = null;
        try {
            list = new DoanhThuLoiNhuanDAO().getListDoanhThuLoiNhuan();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ThongKePanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        JPanel panel = new JPanel(new BorderLayout());

        // ======= PHẦN THÔNG TIN Ở TRÊN =========
        JPanel infoPanel = new JPanel(new GridLayout(2, 3, 10, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin báo cáo"));

        JLabel lblThang = new JLabel("Tháng báo cáo:");
        JTextField txtThang = new JTextField();
        txtThang.setEditable(false);

        JLabel lblTongDoanhThu = new JLabel("Tổng doanh thu:");
        JTextField txtTongDoanhThu = new JTextField();
        txtTongDoanhThu.setEditable(false);

        JLabel lblTongLoiNhuan = new JLabel("Tổng lợi nhuận:");
        JTextField txtTongLoiNhuan = new JTextField();
        txtTongLoiNhuan.setEditable(false);

        infoPanel.add(lblThang);
        infoPanel.add(lblTongDoanhThu);
        infoPanel.add(lblTongLoiNhuan);
        infoPanel.add(txtThang);
        infoPanel.add(txtTongDoanhThu);
        infoPanel.add(txtTongLoiNhuan);

        // Tính tổng giá trị
        double tongDoanhThu = 0;
        double tongLoiNhuan = 0;
        String thangBaoCao = "";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        SimpleDateFormat thangFormat = new SimpleDateFormat("MM/yyyy");

        if (list != null && !list.isEmpty()) {
            tongDoanhThu = list.stream().mapToDouble(DoanhThuLoiNhuan::getDoanhThu).sum();
            tongLoiNhuan = list.stream().mapToDouble(DoanhThuLoiNhuan::getLoiNhuan).sum();
            thangBaoCao = thangFormat.format(list.get(0).getNgay());
        }

        txtThang.setText(thangBaoCao);
        txtTongDoanhThu.setText(String.format("%.2f triệu", tongDoanhThu));
        txtTongLoiNhuan.setText(String.format("%.2f triệu", tongLoiNhuan));

        panel.add(infoPanel, BorderLayout.NORTH);

        // ======= BIỂU ĐỒ =========
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (DoanhThuLoiNhuan dtln : list) {
            String ngay = sdf.format(dtln.getNgay());
            dataset.addValue(dtln.getDoanhThu(), "Doanh thu", ngay);
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Biểu đồ Doanh thu (triệu VND)",
                "Ngày", "Giá trị", dataset,
                PlotOrientation.VERTICAL, true, true, false
        );

        // Tùy chỉnh biểu đồ
        CategoryPlot plot = lineChart.getCategoryPlot();
        plot.setBackgroundPaint(new GradientPaint(0, 0, new Color(245, 245, 245), 0, 600, Color.WHITE));
        plot.setOutlineVisible(false);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(new Color(200, 200, 200));
        plot.setDomainGridlinesVisible(false);

        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(33, 150, 243));
        renderer.setSeriesStroke(0, new BasicStroke(3f));
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-4, -4, 8, 8));

        renderer.setSeriesPaint(1, new Color(76, 175, 80));
        renderer.setSeriesStroke(1, new BasicStroke(3f));
        renderer.setSeriesShapesVisible(1, true);
        renderer.setSeriesShape(1, new java.awt.geom.Rectangle2D.Double(-4, -4, 8, 8));

        plot.setRenderer(renderer);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
        domainAxis.setAxisLineVisible(false);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
        rangeAxis.setAxisLineVisible(false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(900, 350));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(chartPanel, BorderLayout.CENTER);

        return panel;
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

            frame.add(new ThongKePanel(), BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
