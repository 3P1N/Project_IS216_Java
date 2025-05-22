/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.EmployeeGUI;

import appgiaovan.DAO.KhoHangDAO;
import appgiaovan.Entity.KhoHang;
import appgiaovan.GUI.Components.RoundedButton;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TopPanelPCGH extends JPanel {

    private JButton btnSelectShipper = new JButton("Chọn nhân viên giao hàng");

    public TopPanelPCGH() throws SQLException, ClassNotFoundException {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBackground(Color.WHITE);

        // TextField - ID
        

        // TextField - Khách hàng
        // Button - Lọc (màu xanh đậm)
        // Button - Thêm mới (màu xanh lá)
        btnSelectShipper.setPreferredSize(new Dimension(200, 30));
        btnSelectShipper.setBackground(new Color(0, 153, 76));
        btnSelectShipper.setForeground(Color.WHITE);
        btnSelectShipper = new RoundedButton(btnSelectShipper, 20);
        add(btnSelectShipper);

    }

 

    public JButton getBtnSelectShipper() {
        System.out.println("hello world");
        return this.btnSelectShipper;
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
            try {
                frame.add(new TopPanelPCGH());
            } catch (SQLException ex) {
                Logger.getLogger(TopPanelPCGH.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TopPanelPCGH.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.setVisible(true);
        });
    }
}
