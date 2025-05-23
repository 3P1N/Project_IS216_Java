package appgiaovan.ShipperGUI;

import appgiaovan.EmployeeGUI.EmployeeSidebar;
import appgiaovan.GUI.Components.RoundedPanel;
import appgiaovan.GUI.Components.MenuBar;
import appgiaovan.GUI.Components.TimeWeather;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import appgiaovan.ShipperGUI.NVGHMenu;

public class NVGHHomeGUI extends JPanel {

    public NVGHHomeGUI() {
       /* setTitle("Shipper - 3P1N đơn vị giao vận");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());*/

           //tao menu
        //NVGHMenu menu = new NVGHMenu();
        setLayout(new BorderLayout());
        // Khu vực trung tâm (dashboard)
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(new TimeWeather("Hồ Chí Minh 30°C"), BorderLayout.NORTH );
        // Các ô thống kê - dùng lưới 2x2
        JPanel statPanel = new JPanel(new GridLayout(2, 2, 40, 40));
        statPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        statPanel.setPreferredSize(new Dimension(500, 500)); // tăng kích thước các ô thống kê
        
        statPanel.add(RoundedPanel.createStatBox("ĐANG GIAO", "0", "↓ 100%", new Color(76, 175, 80)));
        statPanel.add(RoundedPanel.createStatBox("GIAO THÀNH CÔNG", "0", "↓ 100% (0 hóa đơn)", new Color(33, 150, 243)));
        statPanel.add(RoundedPanel.createStatBox("GIAO THẤT BẠI", "0", "↓ 100% (0 đơn)", new Color(255, 152, 0)));
        statPanel.add(RoundedPanel.createStatBox("DOANH THU", "0", "", new Color(121, 85, 72)));

        // Wrapper để căn giữa statPanel
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(statPanel);

        // Đưa wrapper vào giữa mainPanel
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        // Thêm vào JFrame
        //add(menu, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NVGHHomeGUI().setVisible(true);
        });
    }
}
