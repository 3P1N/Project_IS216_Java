package appgiaovan.EmployeeGUI;

import appgiaovan.GUI.Components.TableList;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuanLyGoiHang extends JPanel {

    public QuanLyGoiHang() throws SQLException, ClassNotFoundException {
        this.setLayout(new BorderLayout());
        initUI();
    }

    private void initUI() throws SQLException, ClassNotFoundException {
        // Panel Menu

        // main
        JPanel mainPanel = new JPanel(new BorderLayout());

        // thanh filter
        TopPanelQLGH topPanel = new TopPanelQLGH();
        mainPanel.add(topPanel, BorderLayout.NORTH);

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
        TableList listOrder = new TableList(columns, data);
        mainPanel.add(listOrder, BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);
    }

    // Dùng để test panel trong một JFrame
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản Lý Gói Hàng");
            try {
                frame.setContentPane(new QuanLyGoiHang());
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyGoiHang.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuanLyGoiHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.setSize(1300, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
