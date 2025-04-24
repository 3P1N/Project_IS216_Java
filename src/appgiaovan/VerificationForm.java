
package appgiaovan;

import javax.swing.*;

public class VerificationForm extends JFrame {
    private final JTextField codeField;
    private final JButton verifyButton;
    private final String realCode;

    public VerificationForm(String realCode) {
        this.realCode = realCode;

        setTitle("Xác nhận đăng ký");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel codeLabel = new JLabel("Nhập mã:");
        codeLabel.setBounds(20, 20, 100, 20);
        add(codeLabel);

        codeField = new JTextField();
        codeField.setBounds(100, 20, 150, 20);
        add(codeField);

        verifyButton = new JButton("Xác nhận");
        verifyButton.setBounds(100, 60, 100, 30);
        add(verifyButton);

        verifyButton.addActionListener(e -> verifyCode());
    }

    private void verifyCode() {
        if (codeField.getText().equals(realCode)) {
            JOptionPane.showMessageDialog(this, "Xác nhận thành công!");
            dispose(); // đóng form xác nhận
        } else {
            JOptionPane.showMessageDialog(this, "Sai mã xác nhận.");
        }
    }
}

