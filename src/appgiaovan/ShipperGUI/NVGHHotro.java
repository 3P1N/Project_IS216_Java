package appgiaovan.ShipperGUI;

import appgiaovan.GUI.Components.RoundedPanel;
import appgiaovan.GUI.Components.MenuBar;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NVGHHotro extends JFrame {

    public NVGHHotro() {
        setTitle("Hỗ Trợ - 3P1N đơn vị giao vận");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Khai báo danh sách tiêu đề và icon cho Sidebar
        java.util.List<String> items = Arrays.asList("Trang chủ", "Quản lý đơn hàng", "Báo cáo", "Hỗ trợ", "Đăng xuất");
        java.util.List<String> icons = Arrays.asList("home.jpg", "order.png", "report.png", "support.jpg", "logout.png");

        // Thiết lập layout cho Sidebar
        setLayout(new BorderLayout());

        // Tạo MenuBar và add vào chính JPanel này
        MenuBar menu = new MenuBar(items, icons);
        add(menu, BorderLayout.EAST);

        // Khu vực trung tâm (dashboard)
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Tạo topPanel để chứa ngày giờ và thời tiết
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        topPanel.setBackground(Color.WHITE);

        // Nhãn hiển thị ngày giờ
        JLabel timeLabel = new JLabel(); // sẽ cập nhật thời gian sau
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        // Nhãn hiển thị thời tiết
        JLabel weatherLabel = new JLabel("Hà Nội: 30°C *"); // gắn API sau
        weatherLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        weatherLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Gắn vào topPanel
        topPanel.add(timeLabel, BorderLayout.WEST);
        topPanel.add(weatherLabel, BorderLayout.EAST);

        // Gắn topPanel vào mainPanel
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Cập nhật thời gian hiện tại liên tục
        Timer timer = new Timer(1000, e -> {
            timeLabel.setText(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy")));
        });
        timer.start();

        // Tạo Panel hỗ trợ
        JPanel supportPanel = new JPanel(null);
        //supportPanel.setLayout(new BoxLayout);
       // supportPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        //JPanel supportPanel = new JPanel(null); // layout thủ công
        supportPanel.setPreferredSize(new Dimension(800, 600)); // kích thước tổng
        supportPanel.setBackground(Color.WHITE); // cùng màu với main GUI

        // Tiêu đề
        JLabel titleLabel = new JLabel("Hỗ trợ nhân viên");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setBounds(50, 30, 300, 30);
        supportPanel.add(titleLabel);

        // Vùng hướng dẫn
        JTextArea instructionsArea = new JTextArea("Hướng dẫn sử dụng:\n1. Đăng nhập vào hệ thống.\n2. Quản lý đơn hàng.\n3. Báo cáo kết quả.");
        instructionsArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        instructionsArea.setLineWrap(true);
        instructionsArea.setWrapStyleWord(true);
        instructionsArea.setEditable(false);
        instructionsArea.setBounds(50, 80, 400, 120);
        instructionsArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        supportPanel.add(instructionsArea);

        // Số hotline
        JLabel hotlineLabel = new JLabel("Hotline: 1800-1234-5678");
        hotlineLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        hotlineLabel.setBounds(50, 220, 300, 30);
        supportPanel.add(hotlineLabel);
        
        JLabel tbaoLabel = new JLabel("Hãy liên hệ hotline để được hỗ trợ");
        tbaoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tbaoLabel.setBounds(50, 250, 300, 30);
        supportPanel.add(tbaoLabel);

        // Gắn supportPanel vào giữa
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(Color.WHITE);
        centerWrapper.add(supportPanel);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);


        // Thêm vào JFrame
        add(menu, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NVGHHotro().setVisible(true);
        });
    }
}
