package appgiaovan.ManagerGUI;

import appgiaovan.EmployeeGUI.*;
import appgiaovan.GUI.Components.TableList;
import appgiaovan.GUI.Components.MenuBar;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;

public class XemBaoCao extends JFrame {

    public XemBaoCao() {
        setTitle("Quản Lý Đơn Hàng");
        setSize(1300, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        //Panel Menu

        ManagerSidebar sidebar = new ManagerSidebar();

        List<String> items = Arrays.asList("Báo cáo", "Quản lý đơn hàng", "Quản lý gói hàng", "Đăng xuất");
        List<String> icons = Arrays.asList("report.png", "order.png", "package.png", "logout.png");
        MenuBar menubar = new MenuBar(items, icons);
        add(menubar, BorderLayout.WEST);

        //main
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //thanh filter
        TopPanelTGH topPanel = new TopPanelTGH();
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel danh sách
        String[] columns = {"", "ID", "Tên nhân viên","Vai trò", "Thông tin", "Ngày báo cáo"};
        Object[][] data = {
            {false, "<html><b style='color:#007bff;'>93200103</b></html>",
                "<html></span><br><b>Nguyễn Văn B</b><br><span></html>","Nhân viên giao hàng",
                "<html><b>50 đơn hàng đã giao</b></html>", "10-02-2025"},
            {false, "<html><b style='color:#007bff;'>93200103</b></html>",
                "<html></span><br><b>Nguyễn Văn A</b><br><span></html>","Nhân viên kho",
                "<html><b>50 đơn hàng đã tạo</b><br><b>10 gói hàng đã tạo</b></html>", "10-02-2025"}
        };
        TableList listOrder = new TableList(columns, data);
        mainPanel.add(listOrder, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> new XemBaoCao().setVisible(true));
    }
}
