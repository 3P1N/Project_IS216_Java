 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.ManagerGUI;





import appgiaovan.EmployeeGUI.EmployeeSidebar;
import appgiaovan.GUI.Components.TableList;
import com.formdev.flatlaf.FlatLightLaf;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

public class BaoCaoThongKe extends JFrame {

    private JTextField txtStartDate, txtEndDate;
    private TableList tblRevenue, tblCost, tblProfit, tblFeedback, tblOrders, tblShipper;
    private DefaultCategoryDataset dsRevenue, dsCost, dsProfit, dsOrders, dsShipper;
    private DefaultPieDataset dsFeedback;
    private ChartPanel chartPanel;

    public BaoCaoThongKe() throws SQLException, ClassNotFoundException {
        setTitle("Báo Cáo Thống Kê");
        setSize(1400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        FlatLightLaf.setup();
        initUI();
    }

    private void initUI() throws SQLException, ClassNotFoundException {
        EmployeeSidebar sidebar = new EmployeeSidebar();
        add(sidebar, BorderLayout.WEST);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Doanh thu", createStatPanel("Doanh thu", dsRevenue = new DefaultCategoryDataset(), tblRevenue = new TableList(new String[]{"Ngày","Doanh thu"}, new Object[][]{}), ChartFactory::createLineChart));
        tabs.addTab("Chi phí", createStatPanel("Chi phí", dsCost = new DefaultCategoryDataset(), tblCost = new TableList(new String[]{"Ngày","Chi phí"}, new Object[][]{}), ChartFactory::createBarChart));
        tabs.addTab("Lợi nhuận", createStatPanel("Lợi nhuận", dsProfit = new DefaultCategoryDataset(), tblProfit = new TableList(new String[]{"Ngày","Lợi nhuận"}, new Object[][]{}), ChartFactory::createAreaChart));
        tabs.addTab("Phản hồi KH", createPiePanel());
        tabs.addTab("Đơn hàng", createStatPanel("Đơn hàng", dsOrders = new DefaultCategoryDataset(), tblOrders = new TableList(new String[]{"Ngày","Số đơn"}, new Object[][]{}), ChartFactory::createBarChart));
        tabs.addTab("Đánh giá Shipper", createStatPanel("Đánh giá TB", dsShipper = new DefaultCategoryDataset(), tblShipper = new TableList(new String[]{"Shipper","Đánh giá"}, new Object[][]{}), ChartFactory::createBarChart));

        add(tabs, BorderLayout.CENTER);
    }

    // Generic panel for time-series stats
    private JPanel createStatPanel(String title, DefaultCategoryDataset dataset, TableList table, BiChartFactory factory) {
        JPanel panel = new JPanel(new BorderLayout(10,10));
        JPanel top = new JPanel();
        top.add(new JLabel("Từ ngày (yyyy-MM-dd):")); txtStartDate = new JTextField(8); top.add(txtStartDate);
        top.add(new JLabel("Đến ngày (yyyy-MM-dd):")); txtEndDate = new JTextField(8); top.add(txtEndDate);
        JButton btnLoad = new JButton("Tải dữ liệu");
        btnLoad.addActionListener(e -> loadTimeSeries(dataset, table, title));
        top.add(btnLoad);
        panel.add(top, BorderLayout.NORTH);
        panel.add(table, BorderLayout.WEST);
        JFreeChart chart = factory.create(title + " theo thời gian", "Ngày", title, dataset);
        chartPanel = new ChartPanel(chart);
        panel.add(chartPanel, BorderLayout.CENTER);
        JPanel bottom = new JPanel();
        bottom.add(new JButton("Tạo báo cáo"));
        bottom.add(new JButton("Gửi báo cáo"));
        panel.add(bottom, BorderLayout.SOUTH);
        return panel;
    }

    // Panel for pie chart feedback
    private JPanel createPiePanel() {
        JPanel panel = new JPanel(new BorderLayout(10,10));
        JPanel top = new JPanel();
        top.add(new JLabel("Từ ngày (yyyy-MM-dd):")); JTextField d1 = new JTextField(8); top.add(d1);
        top.add(new JLabel("Đến ngày (yyyy-MM-dd):")); JTextField d2 = new JTextField(8); top.add(d2);
        JButton btn = new JButton("Tải dữ liệu");
        btn.addActionListener(e -> loadFeedbackData(d1.getText(), d2.getText()));
        top.add(btn);
        panel.add(top, BorderLayout.NORTH);
        String[] cols = {"Phản hồi","Số lượng"}; tblFeedback = new TableList(cols, new Object[][]{});
        panel.add(tblFeedback, BorderLayout.WEST);
        dsFeedback = new DefaultPieDataset();
        JFreeChart pie = ChartFactory.createPieChart("Phản hồi khách hàng", dsFeedback);
        panel.add(new ChartPanel(pie), BorderLayout.CENTER);
        JPanel bot = new JPanel(); bot.add(new JButton("Tạo báo cáo")); bot.add(new JButton("Gửi báo cáo"));
        panel.add(bot, BorderLayout.SOUTH);
        return panel;
    }

    private void loadTimeSeries(DefaultCategoryDataset ds, TableList tbl, String seriesKey) {
        try {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate s = LocalDate.parse(txtStartDate.getText(), f);
            LocalDate e = LocalDate.parse(txtEndDate.getText(), f);
            ds.clear();
            Object[][] rows = IntStream.rangeClosed(0, (int)s.until(e).getDays())
                .mapToObj(i -> {
                    LocalDate d = s.plusDays(i);
                    double v = Math.random()*10000;
                    ds.addValue(v, seriesKey, d.toString());
                    return new Object[]{d.toString(), (long)v};
                }).toArray(Object[][]::new);
            //tbl.setData(rows);
        } catch(Exception ex) { JOptionPane.showMessageDialog(this, "Lỗi định dạng ngày"); }
    }

    private void loadFeedbackData(String d1, String d2) {
        // Giả lập
        dsFeedback.clear();
        dsFeedback.setValue("Tích cực", 40);
        dsFeedback.setValue("Trung tính", 30);
        dsFeedback.setValue("Tiêu cực", 10);
        Object[][] rows = {{"Tích cực",40},{"Trung tính",30},{"Tiêu cực",10}};
        //tblFeedback.setData(rows);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new BaoCaoThongKe().setVisible(true));
//    }

    @FunctionalInterface
    interface BiChartFactory {
        JFreeChart create(String title, String categoryAxis, String valueAxis, DefaultCategoryDataset ds);
    }
}
