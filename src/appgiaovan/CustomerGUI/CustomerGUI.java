/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appgiaovan.CustomerGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author nhant
 */
public class CustomerGUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    public CustomerGUI(){
        setTitle("Giao diện chính");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        //Thêm sidebar
        CustomerSidebar sidebar = new CustomerSidebar();
        add(sidebar, BorderLayout.WEST);
        // Thêm panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
