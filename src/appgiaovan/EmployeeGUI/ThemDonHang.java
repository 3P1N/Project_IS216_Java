
package appgiaovan.EmployeeGUI;

import appgiaovan.GUI.Components.RoundedComboBox;
import appgiaovan.GUI.Components.RoundedPanel;
import appgiaovan.GUI.Components.RoundedTextField;
import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class ThemDonHang extends JFrame {

    public ThemDonHang() {
        setTitle("Tạo Đơn Hàng");
        setSize(880, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);

        // Header border image (simulated with a line)
        JPanel border = new JPanel();
        border.setBounds(0, 0, 880, 10);
        border.setBackground(new Color(255, 102, 102)); // red/blue stripe effect not directly possible
        mainPanel.add(border);

        // Bên gửi
        JLabel lblBenGui = new JLabel("Bên gửi");
        lblBenGui.setFont(new Font("Arial", Font.BOLD, 14));
        lblBenGui.setBounds(20, 20, 100, 25);
        mainPanel.add(lblBenGui);
        

        JLabel lblPhone1 = new JLabel("0377984157");
        lblPhone1.setForeground(new Color(255, 102, 0));
        lblPhone1.setFont(new Font("Arial", Font.BOLD, 13));
        lblPhone1.setBounds(20, 45, 100, 20);
        mainPanel.add(lblPhone1);

        JLabel lblPhone2 = new JLabel("📞 0377984157");
        lblPhone2.setForeground(new Color(255, 102, 0));
        lblPhone2.setFont(new Font("Arial", Font.PLAIN, 13));
        lblPhone2.setBounds(120, 45, 150, 20);
        mainPanel.add(lblPhone2);

        JLabel lblSuaDiaChi = new JLabel("<html><u>Sửa địa chỉ gửi và trả hàng</u></html>");
        lblSuaDiaChi.setForeground(new Color(255, 102, 0));
        lblSuaDiaChi.setFont(new Font("Arial", Font.ITALIC, 12));
        lblSuaDiaChi.setBounds(20, 65, 200, 20);
        mainPanel.add(lblSuaDiaChi);

        JRadioButton rbtnLayHang = new JRadioButton("Lấy hàng tận nơi");
        rbtnLayHang.setBounds(250, 45, 150, 25);
        rbtnLayHang.setSelected(true);
        rbtnLayHang.setBackground(Color.WHITE);
        rbtnLayHang.setForeground(new Color(255, 102, 0));

        JRadioButton rbtnGuiTaiBuuCuc = new JRadioButton("Gửi hàng tại bưu cục");
        rbtnGuiTaiBuuCuc.setBounds(420, 45, 170, 25);
        rbtnGuiTaiBuuCuc.setBackground(Color.WHITE);

        ButtonGroup group = new ButtonGroup();
        group.add(rbtnLayHang);
        group.add(rbtnGuiTaiBuuCuc);

        mainPanel.add(rbtnLayHang);
        mainPanel.add(rbtnGuiTaiBuuCuc);

        JComboBox<String> cbCaLayHang = new JComboBox<>(new String[]{
            "Chọn ca lấy hàng", "Sáng", "Chiều", "Tối"
        });
        cbCaLayHang.setBounds(250, 75, 200, 30);
        mainPanel.add(cbCaLayHang);

        // Separator
        JSeparator separator = new JSeparator();
        separator.setBounds(20, 110, 820, 10);
        mainPanel.add(separator);

        // Bên nhận
        JLabel lblBenNhan = new JLabel("Bên nhận");
        lblBenNhan.setFont(new Font("Arial", Font.BOLD, 14));
        lblBenNhan.setBounds(20, 120, 100, 25);
        mainPanel.add(lblBenNhan);

        RoundedTextField txtSoDienThoai = new RoundedTextField("Nhập số điện thoại");
        txtSoDienThoai.setBorder(BorderFactory.createTitledBorder("Số điện thoại *"));
        txtSoDienThoai.setBounds(20, 150, 200, 50);
        mainPanel.add(txtSoDienThoai);

        RoundedTextField txtDiaChi = new RoundedTextField("Nhập địa chỉ");
        txtDiaChi.setBorder(BorderFactory.createTitledBorder("Địa chỉ *"));
        txtDiaChi.setBounds(240, 150, 300, 50);
        mainPanel.add(txtDiaChi);

        RoundedTextField txtHoTen = new RoundedTextField("Nhập họ tên");
        txtHoTen.setBorder(BorderFactory.createTitledBorder("Họ tên *"));
        txtHoTen.setBounds(20, 220, 200, 50);
        mainPanel.add(txtHoTen);

        RoundedComboBox cbQuanHuyen = new RoundedComboBox(new String[]{
            "Quận 1", "Quận 2", "Quận 3"});

        cbQuanHuyen.setBounds(240, 220, 200, 50);
        mainPanel.add(cbQuanHuyen);

        RoundedComboBox cbPhuongXa = new RoundedComboBox(new String[]{
            "Phường 1", "Phường 2", "Phường 3"
        });
        cbPhuongXa.setBounds(460, 220, 200, 50);
        mainPanel.add(cbPhuongXa);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThemDonHang());
    }
}
