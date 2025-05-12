package appgiaovan.EmployeeGUI;

import appgiaovan.GUI.Components.TableList;
import appgiaovan.GUI.Components.MenuBar;
import appgiaovan.GUI.Components.FilterPanel;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;

public class QuanLyGoiHang extends JFrame {

    public QuanLyGoiHang() {
        setTitle("Quản Lý Đơn Hàng");
        setSize(1300, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        //Panel Menu

        EmployeeSidebar sidebar = new EmployeeSidebar();

        List<String> items = Arrays.asList("Báo cáo", "Quản lý đơn hàng", "Quản lý gói hàng", "Đăng xuất");
        List<String> icons = Arrays.asList("report.png", "order.png", "package.png", "logout.png");
        MenuBar menubar = new MenuBar(items, icons);
        add(menubar, BorderLayout.WEST);

        //main
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //thanh filter
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
        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Không thể cài đặt FlatLaf");
        }
        SwingUtilities.invokeLater(() -> new QuanLyGoiHang().setVisible(true));
    }
}
