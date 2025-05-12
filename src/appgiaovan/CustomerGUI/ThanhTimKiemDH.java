/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appgiaovan.CustomerGUI;

import appgiaovan.EmployeeGUI.TopPanelQLGH;
import appgiaovan.GUI.Components.RoundedButton;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author nhant
 */
public class ThanhTimKiemDH extends JPanel {

     public ThanhTimKiemDH() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBackground(Color.WHITE);

        

        // TextField - ID
        JTextField idField = new JTextField("ID");
        idField.setPreferredSize(new Dimension(500, 30));
        add(idField);

        // ComboBox - Trạng thái
        JComboBox<String> statusComboBox = new JComboBox<>(new String[]{"Trạng thái","Đã giao","Đang vận chuyển"});
        statusComboBox.setPreferredSize(new Dimension(120, 30));
        add(statusComboBox);
        
        
        // Button - Lọc (màu xanh đậm)
        JButton filterButton = new JButton("Lọc");
        filterButton.setPreferredSize(new Dimension(60, 30));
        filterButton.setBackground(new Color(0, 136, 153));
        filterButton.setForeground(Color.WHITE);
        RoundedButton roundedfilterBtn = new RoundedButton(filterButton, 20);
        add(roundedfilterBtn);
        
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
