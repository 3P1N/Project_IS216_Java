/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.CustomerGUI;

import appgiaovan.Entity.DonHang;
import appgiaovan.GUI.Components.RoundedButton;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class ThanhTimKiemDH extends JPanel {

    private JButton addButton = new JButton("Thêm mới");
    private JButton filterButton = new JButton("Lọc");
    private final JTextField idField = new JTextField("");
    private final JComboBox<String> statusComboBox = new JComboBox<>(new String[]{"Đang xử lý"});
    private final JTextField customerField = new JTextField("");

    public ThanhTimKiemDH() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBackground(Color.WHITE);

        // TextField - ID
        idField.setPreferredSize(new Dimension(80, 40));
        idField.setBorder(BorderFactory.createTitledBorder("ID"));
        add(idField);

        // ComboBox - Trạng thái
        statusComboBox.setPreferredSize(new Dimension(120, 40));
        statusComboBox.setBorder(BorderFactory.createTitledBorder("Trạng thái"));
        add(statusComboBox);

        // TextField - Khách hàng
        customerField.setPreferredSize(new Dimension(120, 40));
        customerField.setBorder(BorderFactory.createTitledBorder("Tên khách hàng"));
        add(customerField);

        // Button - Lọc (màu xanh đậm)
        filterButton.setPreferredSize(new Dimension(60, 30));
        filterButton.setBackground(new Color(0, 136, 153));
        filterButton.setForeground(Color.WHITE);
        filterButton = new RoundedButton(filterButton, 20);
        add(filterButton);

        

    }


    public JButton getfilterButton() {
        return this.filterButton;
    }

    public DonHang getDonHang() {
        DonHang dh = new DonHang();

        // Xử lý ID: Nếu trống thì không set hoặc gán null (nếu bạn dùng Integer thay vì int)
        String idText = idField.getText().trim();
        if (!idText.isEmpty()) {
            dh.setIdDonHang(Integer.parseInt(idText));
        } else {
            dh.setIdDonHang(null); // Cần đổi kiểu idDonHang sang Integer
        }

        // Xử lý combobox: nếu không chọn gì thì là null
        Object selected = statusComboBox.getSelectedItem();
        dh.setDichVu(selected != null ? selected.toString() : null);

        // Xử lý tên người gửi: nếu để trống thì là chuỗi rỗng hoặc null tùy bạn
        String name = customerField.getText().trim();
        dh.setTenNguoiGui(name.isEmpty() ? null : name);

        return dh;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Không thể cài đặt FlatLaf");
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Lọc đơn hàng");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 120);
            frame.setLocationRelativeTo(null);
            frame.add(new ThanhTimKiemDH());
            frame.setVisible(true);
        });
    }
}
