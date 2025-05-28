package appgiaovan.ManagerGUI;

import appgiaovan.Controller.ThongKeController;
import appgiaovan.CustomerGUI.ThongTinCaNhanPanel;
import appgiaovan.DAO.DanhGiaDAO;
import appgiaovan.DAO.DoanhThuLoiNhuanDAO;
import appgiaovan.Entity.DoanhThuLoiNhuan;
import appgiaovan.Entity.TK_DanhGia;
import appgiaovan.Entity.TK_DonHang;
import appgiaovan.report.ExportPDF;
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

        tabbedPane.addTab("Đơn Hàng", new ThongKeDonHangPanel());
        tabbedPane.addTab("Đánh Giá", new ThongKeDanhGiaPanel());
        tabbedPane.addTab("Doanh Thu",new ThongKeDoanhThuPanel());

        add(tabbedPane, BorderLayout.CENTER);
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
