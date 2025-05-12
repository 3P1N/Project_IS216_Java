package appgiaovan.GUI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuBar extends JPanel {

    private JLabel activeLabel = null;
    private final Color DEFAULT_BG = new Color(4, 36, 74);
    private final Color HOVER_BG = new Color(30, 60, 100);
    private final List<JLabel> labels = new ArrayList<>();

    public MenuBar(List<String> itemNames, List<String> iconNames) {
        setBackground(DEFAULT_BG);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(180, 700));  // Đặt kích thước panel menu theo yêu cầu

        if (itemNames.size() != iconNames.size()) {
            throw new IllegalArgumentException("Số lượng tên và icon không khớp.");
        }

        setupProfileSection();
        this.add(createSeparator()); 
        for (int i = 0; i < itemNames.size(); i++) {
            JLabel label = createMenuItem(itemNames.get(i), iconNames.get(i));
            labels.add(label);
            if(i==itemNames.size()-1){
                this.add(createSeparator());  

            }
            this.add(label);
        }

        // Mục mặc định là mục đầu tiên
        if (!labels.isEmpty()) {
            setActiveLabel(labels.get(0));
        }
    }

    private JLabel createMenuItem(String text, String iconName) {
        JLabel label = new JLabel("  " + text);
        label.setForeground(Color.WHITE);

        URL imageUrl = getClass().getResource("/images/icons/" + iconName);
        if (imageUrl != null) {
            ImageIcon originalIcon = new ImageIcon(imageUrl);
            Image scaledImage = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaledImage));
        } else {
            System.err.println("Không tìm thấy icon: " + iconName);
        }

        label.setPreferredSize(new Dimension(180, 40));  // Đặt kích thước cho từng mục menu
        label.setMaximumSize(new Dimension(180, 40));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setOpaque(true);
        label.setBackground(DEFAULT_BG);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); // top, left, bottom, right

        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                setActiveLabel(label);
            }

            public void mouseEntered(MouseEvent e) {
                if (label != activeLabel) {
                    label.setBackground(HOVER_BG);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (label != activeLabel) {
                    label.setBackground(DEFAULT_BG);
                }
            }
        });

        return label;
    }
    
    private void animateBackground(JLabel label, Color start, Color end) {
    final int steps = 15;
    final int delay = 20; // milliseconds
    Timer timer = new Timer(delay, null);
    final int[] count = {0};

    timer.addActionListener(e -> {
        float ratio = count[0] / (float) steps;

        int r = (int) (start.getRed() + ratio * (end.getRed() - start.getRed()));
        int g = (int) (start.getGreen() + ratio * (end.getGreen() - start.getGreen()));
        int b = (int) (start.getBlue() + ratio * (end.getBlue() - start.getBlue()));

        label.setBackground(new Color(r, g, b));
        count[0]++;

        if (count[0] > steps) {
            label.setBackground(end); // Đảm bảo kết quả chính xác ở cuối
            ((Timer) e.getSource()).stop();
        }
    });

    timer.start();
}

    
    private void setActiveLabel(JLabel label) {
    if (activeLabel != null) {
        activeLabel.setFont(activeLabel.getFont().deriveFont(Font.PLAIN));
        activeLabel.setForeground(Color.WHITE);
        animateBackground(activeLabel, activeLabel.getBackground(), DEFAULT_BG);
    }

    activeLabel = label;
    activeLabel.setFont(label.getFont().deriveFont(Font.BOLD));
    activeLabel.setForeground(Color.WHITE);

    Color targetColor = new Color(255, 140, 0); // Cam đậm
    animateBackground(activeLabel, activeLabel.getBackground(), targetColor);
}

    
    

    private void setupProfileSection() {
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBackground(DEFAULT_BG);
        profilePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 0)); // lề trái khớp menu

        // Avatar
        JLabel avatarLabel = new JLabel();
        URL imageUrl = getClass().getResource("/images/avatar.png");
        if (imageUrl != null) {
            ImageIcon originalIcon = new ImageIcon(imageUrl);
            Image scaledImage = originalIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            avatarLabel.setIcon(new ImageIcon(scaledImage));
            avatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        } else {
            avatarLabel.setText("Ảnh");
            avatarLabel.setForeground(Color.WHITE);
        }

        // Tên và chức danh
        JLabel nameLabel = new JLabel("John Smith");
        nameLabel.setForeground(Color.WHITE);
        JLabel titleLabel = new JLabel("Software Engineer");
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        profilePanel.add(avatarLabel);
        profilePanel.add(Box.createVerticalStrut(5));
        profilePanel.add(nameLabel);
        profilePanel.add(titleLabel);
        profilePanel.add(Box.createVerticalStrut(10));
        

        add(profilePanel);
    }

    private Component createSeparator() {
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(185, 1));  // Đủ rộng để khớp sidebar
        separator.setAlignmentX(Component.LEFT_ALIGNMENT);
        separator.setForeground(new Color(200, 200, 200));  // Màu xám nhẹ hơn
        return separator;
    }

    // ✅ Hàm main để test trực tiếp MenuBar với kích thước như bạn yêu cầu
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test MenuBar");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 700); // Kích thước của JFrame
            frame.setLocationRelativeTo(null);

            List<String> items = Arrays.asList("Quản lý đơn hàng", "Báo cáo", "Hỗ trợ", "Đăng xuất");
            List<String> icons = Arrays.asList("order.png", "report.png", "support.png", "logout.png");

            MenuBar menu = new MenuBar(items, icons);

            frame.setLayout(new BorderLayout());
            frame.add(menu, BorderLayout.WEST);  // Menu sẽ được đặt ở bên trái

            frame.setVisible(true);
        });
    }
}
