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
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 700));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(DEFAULT_BG);

        // Thêm logo vào đầu
        mainPanel.add(createLogoSection());
        mainPanel.add(createSeparator());
        //
        // Thêm profile
        mainPanel.add(setupProfileSection());
        //
        // Separator giữa profile và menu
        mainPanel.add(createSeparator());

        // Thêm menu item
        for (int i = 0; i < itemNames.size(); i++) {
            JLabel label = createMenuItem(itemNames.get(i), iconNames.get(i));
            labels.add(label);
            mainPanel.add(label);
        }

        // Tùy chọn separator sau cùng nếu cần
        mainPanel.add(Box.createVerticalGlue()); // Đẩy menu lên trên
        add(mainPanel, BorderLayout.CENTER);

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

    private void setActiveLabel(JLabel label) {
        if (activeLabel != null) {
            activeLabel.setFont(activeLabel.getFont().deriveFont(Font.PLAIN));
            activeLabel.setForeground(Color.WHITE);
            activeLabel.setBackground(DEFAULT_BG);
        }

        activeLabel = label;
        activeLabel.setFont(label.getFont().deriveFont(Font.BOLD));
        activeLabel.setForeground(Color.YELLOW);
        activeLabel.setBackground(HOVER_BG);
    }

    private JPanel setupProfileSection() {
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBackground(DEFAULT_BG);
        profilePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        profilePanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0)); // lề

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

        return profilePanel;
    }

    private Component createSeparator() {
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(185, 1));  // Đủ rộng để khớp sidebar
        separator.setAlignmentX(Component.LEFT_ALIGNMENT);
        separator.setForeground(new Color(200, 200, 200));  // Màu xám nhẹ hơn
        return separator;
    }

    private Component createLogoSection() {
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(DEFAULT_BG);
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        logoPanel.setMaximumSize(new Dimension(200, 80));
        logoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel logoLabel = new JLabel();
        logoLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // căn trái tuyệt đối
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); // thêm lề trái 15px (tùy chỉnh)

        URL logoUrl = getClass().getResource("/images/Logo3P1N.png");
        if (logoUrl != null) {
            ImageIcon originalIcon = new ImageIcon(logoUrl);
            Image scaledImage = originalIcon.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            logoLabel.setText("LOGO");
            logoLabel.setForeground(Color.WHITE);
            logoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        }

        logoPanel.add(Box.createVerticalStrut(20));
        logoPanel.add(logoLabel);
        logoPanel.add(Box.createVerticalStrut(10));

        return logoPanel;
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
