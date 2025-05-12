package appgiaovan.EmployeeGUI;

import appgiaovan.GUI.Components.RoundedButton;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class BaoCao extends JPanel {

    public BaoCao() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240)); // nền ngoài

        // Panel chứa nội dung chính
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // padding trong

        // Tiêu đề
        JLabel titleLabel = new JLabel("Báo cáo công việc");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(titleLabel);

        JLabel descLabel = new JLabel("Nhập thông tin báo cáo.");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descLabel.setForeground(Color.GRAY);
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(descLabel);

        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Các trường - Nhân viên (đưa lên đầu)
        formPanel.add(createField("ID Nhân viên", new JTextField()));
        formPanel.add(createField("Tên nhân viên", new JTextField()));

        // Trường khác
        formPanel.add(createField("Ngày báo cáo", new JTextField()));
        formPanel.add(createField("Số lượng đơn hàng", new JTextField()));
        formPanel.add(createField("Gói hàng đã tạo", new JTextField()));

        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Nút gửi
        JButton submitButton = new JButton("Gửi");
        submitButton.setBackground(new Color(0, 102, 204));
        submitButton.setForeground(Color.WHITE);
        submitButton.setPreferredSize(new Dimension(150, 40));
        RoundedButton roundedSubmit = new RoundedButton(submitButton, 20);
        roundedSubmit.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(roundedSubmit);

        // Bọc form trong panel để dễ chèn sidebar sau này
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(245, 245, 245));
        wrapper.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // padding ngoài
        wrapper.add(formPanel, BorderLayout.CENTER);

        EmployeeSidebar sidebar = new EmployeeSidebar();
        add(sidebar, BorderLayout.WEST);
        add(wrapper, BorderLayout.CENTER);
    }

    private JPanel createField(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel(labelText + " *");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(Color.DARK_GRAY);

        textField.setPreferredSize(new Dimension(300, 35));
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0)); // khoảng cách dưới mỗi ô

        return panel;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Báo cáo nhân viên");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new BaoCao());
            frame.setVisible(true);
        });
    }
}
