package appgiaovan.ShipperGUI;

import appgiaovan.GUI.Components.TimeWeather;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.TitledBorder;

public class ShipperProfile extends JFrame {

    private JTextField nameField;
    private JTextField birthField;
    private JTextField genderField;
    private JTextField startDateField;
    private JTextField idCardField;
    private JTextField phoneField;
    private JTextField emailField;
    private JButton sendBtn;

    public ShipperProfile() {
        setTitle("Thông tin cá nhân - 3P1N");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar menu
        ShipperMenu menu = new ShipperMenu();
        add(menu, BorderLayout.WEST);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Header: time & weather
        mainPanel.add(new TimeWeather("Hồ Chí Minh 30°C"), BorderLayout.NORTH);

        // Content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(600, 400));
        contentPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2), "Thông tin cá nhân",
            TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.BOLD, 20), Color.DARK_GRAY));
        contentPanel.setBackground(Color.WHITE);

        // Form chia làm 2 cột
        JPanel formPanel = new JPanel(new GridLayout(1, 2, 40, 0));
        formPanel.setBackground(Color.WHITE);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        JPanel rightPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(Color.WHITE);
        rightPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        int leftRow = 0;
        nameField = addRow("Họ tên:", leftPanel, gbc, leftRow++);
        birthField = addRow("Ngày sinh:", leftPanel, gbc, leftRow++);
        genderField = addRow("Giới tính:", leftPanel, gbc, leftRow++);
        //startDateField = addRow("Ngày vào làm:", leftPanel, gbc, leftRow++);

        int rightRow = 0;
        idCardField = addRow("CCCD:", rightPanel, gbc, rightRow++);
        phoneField = addRow("SĐT:", rightPanel, gbc, rightRow++);
        emailField = addRow("Email:", rightPanel, gbc, rightRow++);

        formPanel.add(leftPanel);
        formPanel.add(rightPanel);

        setFieldsEditable(false);
        contentPanel.add(formPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton editBtn = new JButton("Sửa");
        sendBtn = new JButton("Gửi");
        sendBtn.setVisible(false);

        Font buttonFont = new Font("Segoe UI", Font.PLAIN, 18);
        editBtn.setFont(buttonFont);
        sendBtn.setFont(buttonFont);

        buttonPanel.add(editBtn);
        buttonPanel.add(sendBtn);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Centering content
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(Color.WHITE);
        centerWrapper.add(contentPanel);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        // Events
        editBtn.addActionListener(e -> {
            setFieldsEditable(true);
            sendBtn.setVisible(true);
        });

        sendBtn.addActionListener(e -> {
            if (validateForm()) {
                setFieldsEditable(false);
                sendBtn.setVisible(false);
                JOptionPane.showMessageDialog(this, "Đã lưu thông tin!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private JTextField addRow(String label, JPanel panel, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panel.add(jLabel, gbc);

        gbc.gridx = 1;
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        field.setPreferredSize(new Dimension(250, 30)); // đủ cho 20-25 ký tự, không bị kéo dài

        panel.add(field, gbc);
        field.setPreferredSize(new Dimension(100, 30));


        return field;
    }



    private void setFieldsEditable(boolean editable) {
        nameField.setEditable(editable);
        birthField.setEditable(editable);
        genderField.setEditable(editable);
        //startDateField.setEditable(editable);
        idCardField.setEditable(editable);
        phoneField.setEditable(editable);
        emailField.setEditable(editable);
    }

    private boolean validateForm() {
        StringBuilder error = new StringBuilder();

        if (!isValidDate(birthField.getText()))
            error.append("Ngày sinh không hợp lệ. Định dạng dd/MM/yyyy\n");

      //  if (!isValidDate(startDateField.getText()))
            //error.append("Ngày vào làm không hợp lệ. Định dạng dd/MM/yyyy\n");

        if (!isValidCCCD(idCardField.getText()))
            error.append("CCCD phải gồm 12 chữ số.\n");

        if (!isValidPhone(phoneField.getText()))
            error.append("Số điện thoại phải gồm 10 chữ số.\n");

        if (!isValidEmail(emailField.getText()))
            error.append("Email không hợp lệ.\n");

        if (error.length() > 0) {
            JOptionPane.showMessageDialog(this, error.toString(), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isValidEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }

    private boolean isValidCCCD(String cccd) {
        return cccd.matches("\\d{12}");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
            } catch (Exception ignored) {
            }
            new ShipperProfile().setVisible(true);
        });
    }
}
