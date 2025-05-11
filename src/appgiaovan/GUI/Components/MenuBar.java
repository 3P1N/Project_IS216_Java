package appgiaovan.GUI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class MenuBar extends JPanel {

    public MenuBar(List<String> itemNames, List<String> iconNames) {
        setBackground(new Color(4, 36, 74));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, getHeight()));

        if (itemNames.size() != iconNames.size()) {
            throw new IllegalArgumentException("Số lượng tên và icon không khớp.");
        }

        for (int i = 0; i < itemNames.size(); i++) {
            JLabel label = createMenuItem(itemNames.get(i), iconNames.get(i));
            this.add(label);
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

        label.setPreferredSize(new Dimension(180, 40));
        label.setMaximumSize(new Dimension(180, 40));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setOpaque(true);
        label.setBackground(new Color(4, 36, 74));
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        label.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                label.setBackground(new Color(30, 60, 100));
            }

            public void mouseExited(MouseEvent e) {
                label.setBackground(new Color(4, 36, 74));
            }
        });

        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("MenuBar");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 700);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            // Menu bên trái
            MenuBar menuBar = new MenuBar(
                    Arrays.asList("Trang chủ", "Đơn hàng", "Cài đặt", "Đăng xuất"),
                    Arrays.asList("home.png", "order.png", "settings.png", "logout.png")
            );
            frame.add(menuBar, BorderLayout.WEST);
            frame.setVisible(true);
        });
    }

}
