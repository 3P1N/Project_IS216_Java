/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.EmployeeGUI;
import appgiaovan.GUI.Components.RoundedButton;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class TopPanelTGH extends JPanel {
    public TopPanelTGH() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBackground(Color.WHITE);

        

        // TextField - ID
        JTextField idField = new JTextField("ID");
        idField.setPreferredSize(new Dimension(80, 30));
        add(idField);

        // ComboBox - Trạng thái
        JComboBox<String> statusComboBox = new JComboBox<>(new String[]{"Kho A", "Kho B"});
        statusComboBox.setPreferredSize(new Dimension(120, 30));
        add(statusComboBox);

        // TextField - Khách hàng
        JTextField customerField = new JTextField("Sản phẩm A");
        customerField.setPreferredSize(new Dimension(100, 30));
        add(customerField);


        // Button - Lọc (màu xanh đậm)
        JButton filterButton = new JButton("Lọc");
        filterButton.setPreferredSize(new Dimension(60, 30));
        filterButton.setBackground(new Color(0, 136, 153));
        filterButton.setForeground(Color.WHITE);
        RoundedButton roundedfilterBtn = new RoundedButton(filterButton, 20);
        add(roundedfilterBtn);

        // Button - Thêm mới (màu xanh lá)
        JButton addButton = new JButton("Đóng gói");
        addButton.setPreferredSize(new Dimension(100, 30));
        addButton.setBackground(new Color(0, 153, 76));
        addButton.setForeground(Color.WHITE);
        RoundedButton roundedaddBtn = new RoundedButton(addButton, 20);
        add(roundedaddBtn);

        
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
            frame.add(new TopPanelTGH());
            frame.setVisible(true);
        });
    }
}
