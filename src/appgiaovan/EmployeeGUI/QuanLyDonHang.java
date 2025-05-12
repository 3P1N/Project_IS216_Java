package appgiaovan.EmployeeGUI;

import appgiaovan.GUI.Components.TableList;
import appgiaovan.GUI.Components.MenuBar;
import appgiaovan.GUI.Components.FilterPanel;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;

import javax.swing.table.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;

public class QuanLyDonHang extends JFrame {

    public QuanLyDonHang() {
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
        FilterPanel topPanel = new FilterPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel danh sách
        String[] columns = {"", "ID", "Khách hàng", "Sản phẩm", "ĐVT", "Giá", "SL"};
        Object[][] data = {
            {false, "<html><b style='color:#007bff;'>93900415</b><br><span style='color:gray;font-size:10px;'>10:48 15/04</span><br><span style='color:#007bff;'>Kho 2 - Tháo</span></html>",
                "<html><span style='color:#007bff;'>0254654141 ▼</span><br><b>Triệu Mạnh Tùng</b><br><span style='color:gray;'>Số 15, ngõ 207</span><br><span style='color:gray;'>Quận Hoàng Mai, Hà Nội</span></html>",
                "Váy hoa big size - XL - Đỏ thọ đen", "", "<html><b>400.000</b><br><span style='color:red;'>- 80.000</span></html>", 1},
            {false, "<html><b style='color:#007bff;'>93200103</b><br><span style='color:gray;font-size:10px;'>18:48 10/04</span><br><span style='color:#007bff;'>Kho 2 - Tháo</span></html>",
                "<html><span style='color:#007bff;'>0238024020 ▼</span><br><b>Vũ Tiến Tài</b><br><span style='color:gray;'>Số 34 Ngách 173/68 Hoàng Hoa Thám, Hà Nội</span><br><i style='color:gray;'>(Cập đồng)</i><br><span style='color:gray;'>Quận Hai Bà Trưng, Hà Nội</span></html>",
                "Váy hoa big size - Xanh Neon - Đỏ thọ đen", "", "<html><b>400.000</b><br><span style='color:red;'>- 40.000</span></html>", 2}
        };
        TableList listOrder = new TableList(columns, data);
        mainPanel.add(listOrder, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> new QuanLyDonHang().setVisible(true));
    }
}
