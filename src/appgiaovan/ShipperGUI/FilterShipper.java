/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.ShipperGUI;

import appgiaovan.GUI.Components.*;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class FilterShipper extends JPanel {

    public FilterShipper() {
        setLayout(null);
        setBackground(Color.WHITE);

        // TextField - ID
        JTextField idField = new JTextField("ID");
        idField.setPreferredSize(new Dimension(80, 30));
        idField.setBounds(10, 10, 100, 30);
        add(idField);

        // ComboBox - Trạng thái
        String[] trangThai = { "Đang giao", "Đã giao", "Giao thất bại" };
        JComboBox<String> statusComboBox = new JComboBox<>(trangThai);
        statusComboBox.setPreferredSize(new Dimension(120, 30));
        statusComboBox.setBounds(130, 10, 120, 30);
        add(statusComboBox);

        // TextField - Khách hàng
        JTextField customerField = new JTextField("Họ tên");
        customerField.setPreferredSize(new Dimension(100, 30));
        customerField.setBounds(270, 10, 130, 30);
        add(customerField);

        // Button - Lọc (màu xanh đậm)
        JButton filterButton = new JButton("Lọc");
        filterButton.setPreferredSize(new Dimension(60, 30));
        filterButton.setBackground(new Color(0, 136, 153));
        filterButton.setForeground(Color.WHITE);
        RoundedButton roundedfilterBtn = new RoundedButton(filterButton, 20);
        roundedfilterBtn.setBounds(420, 10, 70, 30);
        add(roundedfilterBtn);

        // Button - Thêm mới (màu xanh lá)
        JButton addButton = new JButton("Đã giao");
        addButton.setPreferredSize(new Dimension(200, 30));
        addButton.setBackground(new Color(0, 123, 255));
        addButton.setForeground(Color.WHITE);
        RoundedButton roundedaddBtn = new RoundedButton(addButton, 20);
        roundedaddBtn.setBounds(800, 10, 90, 30);
        add(roundedaddBtn);

        // JButton - Thao tác (màu xanh dương)
        JButton actionButton = new JButton("Giao thất bại");
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
            frame.add(new FilterShipper());
            frame.setVisible(true);
        });
    }
}
