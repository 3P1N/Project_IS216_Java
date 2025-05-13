package appgiaovan.ShipperGUI;

import appgiaovan.GUI.Components.TableList;
import appgiaovan.GUI.Components.MenuBar;
import appgiaovan.GUI.Components.RoundedButton;
import appgiaovan.GUI.Components.TimeWeather;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class ShipperProduct extends JFrame {

    public ShipperProduct() {
        setTitle("Quản Lý Đơn Hàng");
        setSize(1300, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel Menu
        ShipperMenu menu = new ShipperMenu();
        add(menu, BorderLayout.WEST);

        // MAIN PANEL: dùng BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 1) Header (thời gian + thời tiết)
        mainPanel.add(new TimeWeather("Hồ Chí Minh 30°C"), BorderLayout.NORTH);

        // 2) Trung tâm gồm filter + bảng, xếp dọc
        JPanel centerPanel = new JPanel(new BorderLayout());

        // 2.1) Thanh lọc lên trên
        FilterShipper filter = new FilterShipper();
        filter.setPreferredSize(new Dimension(0, 60));  // để layout manager tự co giãn chiều ngang
        centerPanel.add(filter, BorderLayout.NORTH);

        // 2.2) Bảng danh sách xuống dưới
        String[] columns = {"", "ID", "Họ tên", "Số điện thoại", "Địa chỉ", "Trạng thái", "Ghi chú"};
        Object[][] data = {
            {false, "<html><b style='color:#007bff;'>93900415</b><br><span style='color:gray;font-size:10px;'>10:48 15/04</span><br><span style='color:#007bff;'>Kho 2 - Tháo</span></html>",
                "<html><span style='color:#007bff;'>0254654141 ▼</span><br><b>Triệu Mạnh Tùng</b><br><span style='color:gray;'>Số 15, ngõ 207</span><br><span style='color:gray;'>Quận Hoàng Mai, Hà Nội</span></html>",
                "Váy hoa big size - XL - Đỏ thọ đen", "", "<html><b>400.000</b><br><span style='color:red;'>- 80.000</span></html>", 1},
            {false, "<html><b style='color:#007bff;'>93200103</b><br><span style='color:gray;font-size:10px;'>18:48 10/04</span><br><span style='color:#007bff;'>Kho 2 - Tháo</span></html>",
                "<html><span style='color:#007bff;'>0238024020 ▼</span><br><b>Vũ Tiến Tài</b><br><span style='color:gray;'>Số 34 Ngách 173/68 Hoàng Hoa Thám, Hà Nội</span><br><i style='color:gray;'>(Cập đồng)</i><br><span style='color:gray;'>Quận Hai Bà Trưng, Hà Nội</span></html>",
                "Váy hoa big size - Xanh Neon - Đỏ thọ đen", "", "<html><b>400.000</b><br><span style='color:red;'>- 40.000</span></html>", 2}
        };
        TableList listOrder = new TableList(columns, data);
        centerPanel.add(listOrder, BorderLayout.CENTER);

        // 3) Gắn centerPanel vào mainPanel
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Thêm mainPanel vào JFrame
        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (Exception ex) {
                System.err.println("Không thể cài đặt FlatLaf");
            }
            new ShipperProduct().setVisible(true);
        });
    }
}
