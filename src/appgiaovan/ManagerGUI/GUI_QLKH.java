/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.ManagerGUI;

import appgiaovan.GUI.Components.TableList;
import appgiaovan.GUI.Components.MenuBar;
import appgiaovan.GUI.Components.FilterPanel;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;

import java.awt.*;

public class GUI_QLKH extends JFrame {

    public GUI_QLKH() {
        setTitle("Quản Lý Khách Hàng");
        setSize(1300, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        // Panel Menu
        ManagerSidebar sidebar = new ManagerSidebar();
        add(sidebar, BorderLayout.WEST);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Thanh filter
        FilterPanel topPanel = new FilterPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel danh sách khách hàng
        String[] columns = {"", "Mã KH", "Tên Khách Hàng", "SĐT", "Email", "Địa chỉ", "Ngày đăng ký"};
        Object[][] data = {
            {false, "<html><b style='color:#007bff;'>KH001</b></html>",
                "<html><b>Trần Văn Nam</b><br><span style='color:gray;'>Nam, 35 tuổi</span></html>",
                "0912345678", "namtv@gmail.com", "Số 10, Đường Láng, Hà Nội", "10/01/2023"},
            {false, "<html><b style='color:#007bff;'>KH002</b></html>",
                "<html><b>Lê Thị Hoa</b><br><span style='color:gray;'>Nữ, 28 tuổi</span></html>",
                "0987654321", "hoalt@gmail.com", "25 Nguyễn Trãi, Thanh Xuân, Hà Nội", "22/03/2023"}
        };
        TableList listCustomer = new TableList(columns, data);
        mainPanel.add(listCustomer, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI_QLKH().setVisible(true));
    }
}