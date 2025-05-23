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

import javax.swing.table.*;
import java.awt.*;

public class QuanLyNhanVien extends JFrame {

    public QuanLyNhanVien() {
        setTitle("Quản Lý Nhân Viên");
        setSize(1300, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        // Panel Menu
        ManagerSidebar sidebar = new ManagerSidebar(this);
        add(sidebar, BorderLayout.WEST);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Thanh filter
        FilterPanel topPanel = new FilterPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel danh sách nhân viên
        String[] columns = {"", "Mã NV", "Tên Nhân Viên", "Chức Vụ", "SĐT", "Email"};
        Object[][] data = {
            {false, "<html><b style='color:#007bff;'>NV001</b><br><span style='color:gray;font-size:10px;'>Vào: 10/04/2021</span></html>",
                "<html><b>Nguyễn Văn A</b><br><span style='color:gray;'>29 tuổi</span></html>",
                "Nhân viên kho", "0905123456", "nguyenvana@gmail.com"},
            {false, "<html><b style='color:#007bff;'>NV002</b><br><span style='color:gray;font-size:10px;'>Vào: 12/06/2022</span></html>",
                "<html><b>Trần Thị B</b><br><span style='color:gray;'>31 tuổi</span></html>",
                "Nhân viên giao hàng", "0905987654", "tranthib@gmail.com"}
        };
        TableList listEmployee = new TableList(columns, data);
        mainPanel.add(listEmployee, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuanLyNhanVien().setVisible(true));
    }
}