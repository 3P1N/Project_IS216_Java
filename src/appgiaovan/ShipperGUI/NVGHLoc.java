/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.ShipperGUI;

import appgiaovan.Entity.DonHang;
import appgiaovan.GUI.Components.*;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

import appgiaovan.DAO.DonHangDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NVGHLoc extends JPanel {

    private JTextField idField = new JTextField("ID");
    private JTextField customerField = new JTextField("Họ tên");
    private JButton filterButton = new JButton("Lọc");
    private JButton addButton = new JButton("Đã giao");
    private JButton actionButton = new JButton("Giao thất bại");
    //private final JTextField idField = new JTextField("");
    private final JTextField nullField = new JTextField("");
    private final DonHangDAO donHangDAO  = new DonHangDAO();
    private final JComboBox<String> statusComboBox ;
    public NVGHLoc() {
        setLayout(null);
        setBackground(Color.WHITE);

        // TextField - ID
        
        idField.setPreferredSize(new Dimension(80, 30));
        idField.setBounds(10, 10, 100, 30);
        add(idField);

        // ComboBox - Trạng thái
        String[] trangThai = { "Đang giao", "Đã giao", "Giao thất bại" };
        statusComboBox = new JComboBox<>(trangThai);
        statusComboBox.setPreferredSize(new Dimension(120, 30));
        statusComboBox.setBounds(130, 10, 120, 30);
        add(statusComboBox);

        // TextField - Khách hàng
        
        customerField.setPreferredSize(new Dimension(100, 30));
        customerField.setBounds(270, 10, 130, 30);
        add(customerField);

        // Button - Lọc (màu xanh đậm)
        
        filterButton.setPreferredSize(new Dimension(60, 30));
        filterButton.setBackground(new Color(0, 136, 153));
        filterButton.setForeground(Color.WHITE);
        RoundedButton roundedfilterBtn = new RoundedButton(filterButton, 20);
        roundedfilterBtn.setBounds(420, 10, 70, 30);
        add(roundedfilterBtn);

        // Button - Trạng thái đã giao (màu xanh lá)
        
        addButton.setPreferredSize(new Dimension(200, 30));
        addButton.setBackground(new Color(0, 123, 255));
        addButton.setForeground(Color.WHITE);
        RoundedButton roundedaddBtn = new RoundedButton(addButton, 20);
        roundedaddBtn.setBounds(800, 10, 90, 30);
        add(roundedaddBtn);

        // JButton - Trạng thái giao thất bại (màu xanh dương)
        
        actionButton.setPreferredSize(new Dimension(240, 30));
        actionButton.setBackground(new Color(204, 0, 0));
        actionButton.setForeground(Color.WHITE);
        RoundedButton roundedactionBtn = new RoundedButton(actionButton, 20);
        roundedactionBtn.setBounds(900, 10, 110, 30);
        add(roundedactionBtn);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (Exception ex) {
                System.err.println("Không thể cài đặt FlatLaf");
            }
            JFrame frame = new JFrame("");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 120);
            frame.setLocationRelativeTo(null);
            frame.add(new NVGHLoc());
            frame.setVisible(true);
        });
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
        dh.setTrangThai(selected != null ? selected.toString() : null);

        // Xử lý tên người gửi: nếu để trống thì là chuỗi rỗng hoặc null tùy bạn
        String name = customerField.getText().trim();
        dh.setTenNguoiGui(name.isEmpty() ? null : name);
       System.out.println(dh.getTrangThai());
        return dh;
    }
}
