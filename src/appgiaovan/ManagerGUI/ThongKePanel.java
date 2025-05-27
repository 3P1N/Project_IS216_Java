package appgiaovan.ManagerGUI;

import appgiaovan.Controller.ThongKeController;
import appgiaovan.CustomerGUI.ThongTinCaNhanPanel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThongKePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnXuatPDF;
    private ThongKeController controller;
    private List<Map<String, Object>> duLieuThongKe;

    public ThongKePanel() {
//        this.duLieuThongKe = LayDuLieuThongKe();
        this.controller = new ThongKeController();
        setLayout(new BorderLayout());
        initUI();
    }

    private void initUI() {
        // Tiêu đề
        JLabel lblTitle = new JLabel("BÁO CÁO THỐNG KÊ ĐƠN HÀNG", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitle, BorderLayout.NORTH);

        // Bảng dữ liệu
        tableModel = new DefaultTableModel(new Object[]{"Doanh Thu", "Đơn hàng", "Đánh giá"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Đổ dữ liệu vào bảng
        doDuLieuVaoBang();

        // Nút xuất PDF
        btnXuatPDF = new JButton("Xuất PDF");
        btnXuatPDF.addActionListener(e -> xuatPDF());
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnXuatPDF);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private void doDuLieuVaoBang() {
        for (Map<String, Object> row : duLieuThongKe) {
            tableModel.addRow(new Object[]{
                    row.get("doanhthu"),
                    row.get("donhang"),
                    row.get("danhgia")
            });
        }
    }
    public List<Map<String, Object>> LayDuLieuThongKe(){
//        return controller.LayDuLieuThongKe();
    }
    private void xuatPDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file PDF");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.endsWith(".pdf")) {
                filePath += ".pdf";
            }
            PDFExporter.exportThongKePDF(filePath, duLieuThongKe);
        }
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
