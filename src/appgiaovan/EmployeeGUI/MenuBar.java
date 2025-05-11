// MenuBar.java
package appgiaovan.EmployeeGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class MenuBar extends JPanel {

    public MenuBar() {
        setBackground(new Color(4, 36, 74));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, getHeight()));

        String[] items = {
            "Báo cáo",
            "Quản lý đơn hàng",
            "Quản lý gói hàng",
            "Đăng xuất"
        };

        String[] icons = {
            "report.png",
            "order.png",
            "package.png",
            "logout.png"
        };

        for (int i = 0; i < items.length; i++) {
            JLabel label = createMenuItem(items[i], icons[i]);
            this.add(label);
        }
    }

    private JLabel createMenuItem(String text, String iconName) {
        JLabel label = new JLabel("  " + text);
        label.setForeground(Color.WHITE);
        URL imageUrl = getClass().getResource("/images/icons/" + iconName);
        System.out.println("Image URL: " + imageUrl);
        ImageIcon originalIcon = new ImageIcon(imageUrl);
        Image scaledImage = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH); // 👈 chỉnh size tại đây
        label.setIcon(new ImageIcon(scaledImage));

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
}
