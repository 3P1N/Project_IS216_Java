package appgiaovan.ShipperGUI;

import appgiaovan.GUI.Components.TimeWeather;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;

/**
 * Giao diện báo cáo cho Shipper:
 * - Hiển thị Thời gian báo cáo, Đã nhận, Đã giao, Giao thất bại, Tiền đã nhận
 * - Các ô ban đầu không cho nhập
 * - Nút Sửa để bật chế độ nhập, Gửi để khóa lại và xử lý gửi
 * - Thêm khung viền bao quanh thông tin và font chữ size 20
 */
public class NVGHBaoCao extends JFrame {

    private JTextField reportTimeField;
    private JTextField receivedField;
    private JTextField deliveredField;
    private JTextField failedField;
    private JTextField revenueField;

    public NVGHBaoCao() {
        setTitle("Báo cáo Shipper - 3P1N");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar menu
        NVGHMenu menu = new NVGHMenu();
        add(menu, BorderLayout.WEST);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Header: time & weather
        mainPanel.add(new TimeWeather("Hồ Chí Minh 30°C"), BorderLayout.NORTH);

        // Content panel with titled border
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(600, 400));
        contentPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2), "Thông tin báo cáo",
            TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.BOLD, 20), Color.DARK_GRAY));
        contentPanel.setBackground(Color.WHITE);

        // Form inside content
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Row 1: Thời gian báo cáo
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createLabel("Thời gian báo cáo:"), gbc);
        reportTimeField = createTextField(20);
        reportTimeField.setText(java.time.LocalDateTime.now()
            .format(java.time.format.DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")));
        reportTimeField.setEditable(false);
        gbc.gridx = 1;
        formPanel.add(reportTimeField, gbc);

        // Row 2: Đã nhận
        gbc.gridy++; gbc.gridx = 0;
        formPanel.add(createLabel("Đã nhận:"), gbc);
        receivedField = createTextField(20);
        receivedField.setEditable(false);
        gbc.gridx = 1;
        formPanel.add(receivedField, gbc);

        // Row 3: Đã giao
        gbc.gridy++; gbc.gridx = 0;
        formPanel.add(createLabel("Đã giao:"), gbc);
        deliveredField = createTextField(20);
        deliveredField.setEditable(false);
        gbc.gridx = 1;
        formPanel.add(deliveredField, gbc);

        // Row 4: Giao thất bại
        gbc.gridy++; gbc.gridx = 0;
        formPanel.add(createLabel("Giao thất bại:"), gbc);
        failedField = createTextField(20);
        failedField.setEditable(false);
        gbc.gridx = 1;
        formPanel.add(failedField, gbc);

        // Row 5: Tiền đã nhận
        gbc.gridy++; gbc.gridx = 0;
        formPanel.add(createLabel("Tiền đã nhận:"), gbc);
        revenueField = createTextField(20);
        revenueField.setEditable(false);
        gbc.gridx = 1;
        formPanel.add(revenueField, gbc);

        contentPanel.add(formPanel, BorderLayout.CENTER);

        // Center wrapper
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(Color.WHITE);
        centerWrapper.add(contentPanel);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        // Button panel outside form, aligned right
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        JButton editBtn = new JButton("Sửa");
        JButton sendBtn = new JButton("Gửi");
        editBtn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        sendBtn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        buttonPanel.add(editBtn);
        buttonPanel.add(sendBtn);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

       add(mainPanel, BorderLayout.CENTER);

        // Action listeners
        editBtn.addActionListener(e -> setFieldsEditable(true));
        sendBtn.addActionListener(e -> {
            setFieldsEditable(false);
            JOptionPane.showMessageDialog(this, "Đã gửi báo cáo!", "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        return label;
    }

    private JTextField createTextField(int cols) {
        JTextField tf = new JTextField(cols);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        return tf;
    }

    private void setFieldsEditable(boolean editable) {
        receivedField.setEditable(editable);
        deliveredField.setEditable(editable);
        failedField.setEditable(editable);
        revenueField.setEditable(editable);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf()); } catch (Exception ignored) {}
            new NVGHBaoCao().setVisible(true);
        });
    }
}
