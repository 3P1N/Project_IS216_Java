/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.GUI;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import com.toedter.calendar.JDateChooser;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
/**
 *
 * @author ASUS
 */
public class RegisterGUI extends JFrame{
    public RegisterGUI() {
        setTitle("Đăng ký - Đơn vị giao vận 3P1N");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Tạo một JPanel chính để chứa background và LoginPanel
        JPanel mainPanel = new JPanel(null);
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 900, 600); // Kích thước của toàn bộ frame
        add(mainPanel);

        // Load hình nền
        JLabel background = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/images/warehouse_11zon.jpg")));// Thay bằng hình bạn muốn
        URL imageUrl = getClass().getResource("/images/warehouse_11zon.jpg");
        System.out.println("Image URL: " + imageUrl);

        background.setBounds(0, 0, 900, 600);
       // mainPanel.add(background);

        // Panel chính để nhập register
        JPanel registerPanel = new JPanel();
        registerPanel.setBounds(275, 10, 350, 540);
        registerPanel.setBackground(Color.WHITE);
        registerPanel.setLayout(null);
        registerPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
       // mainPanel.add(registerPanel);

        JLabel logo = new JLabel("Đăng ký tài khoản mới");
        logo.setFont(new Font("Arial", Font.BOLD, 20));
        logo.setForeground(new Color(0, 90, 204));
        logo.setBounds(70, 10, 250, 25);
        registerPanel.add(logo);
        
        //nhập họ tên
        JTextField hoTenField = new JTextField();
        hoTenField.setBounds(30, 50, 290, 35);
        hoTenField.setBorder(BorderFactory.createTitledBorder("Họ Tên"));
        registerPanel.add(hoTenField);

        // nhập tên đăng nhập
        JTextField userField = new JTextField();
        userField.setBounds(30, 95, 290, 35);
        userField.setBorder(BorderFactory.createTitledBorder("Tên đăng nhập"));
        registerPanel.add(userField);
        
        //nhập pass
        JPasswordField passField = new JPasswordField();
        passField.setBounds(30, 145, 290, 35);
        passField.setBorder(BorderFactory.createTitledBorder("Mật khẩu"));
        registerPanel.add(passField);

        // nhập lại pass
        JPasswordField passRepeat = new JPasswordField();
        passRepeat.setBounds(30, 195, 290, 35);
        passRepeat.setBorder(BorderFactory.createTitledBorder("Nhập lại mật khẩu"));
        registerPanel.add(passRepeat);
        
        //nhập CCCD
        JTextField cccdField = new JTextField();
        cccdField.setBounds(30, 245, 290, 35);
        cccdField.setBorder(BorderFactory.createTitledBorder("CCCD"));
        registerPanel.add(cccdField);
        
        //nhập Email
        JTextField emailField = new JTextField();
        emailField.setBounds(30, 295, 290, 35);
        emailField.setBorder(BorderFactory.createTitledBorder("Gmail"));
        registerPanel.add(emailField);
        
        //nhập SĐT
        JTextField sdtField = new JTextField();
        sdtField.setBounds(30, 345, 290, 35);
        sdtField.setBorder(BorderFactory.createTitledBorder("Số điện thoại"));
        registerPanel.add(sdtField);
        
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setBounds(30, 390, 290, 40); // vị trí và kích thước
        dateChooser.setBorder(BorderFactory.createTitledBorder("Ngày sinh"));
        registerPanel.add(dateChooser);
        
        //nhập GT
        String[] genders = {"Nam", "Nữ"};
        JComboBox<String> genderComboBox = new JComboBox<>(genders);
        genderComboBox.setBounds(30, 435, 290, 40);
        genderComboBox.setBorder(BorderFactory.createTitledBorder("Giới tính"));
        registerPanel.add(genderComboBox);

        
        JButton dkyButton = new JButton("Đăng ký");
        dkyButton.setBounds(30, 485, 290, 25);
        dkyButton.setBackground(new Color(0, 123, 255));
        dkyButton.setForeground(Color.WHITE);
        dkyButton.setFocusPainted(false);
        registerPanel.add(dkyButton);
        
        
        // Label "Bạn chưa có tài khoản?" 
        JLabel infoLabel = new JLabel("Bạn đã có tài khoản?");
        infoLabel.setBounds(70, 515, 150, 20);
        registerPanel.add(infoLabel);

        // Label "Đăng nhap ngay" - có thể click chuyển giao diện
        JLabel loginLabel = new JLabel("Đăng nhập ngay");
        loginLabel.setBounds(190, 515, 100, 20);
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerPanel.add(loginLabel);

        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Hiển thị form Đăng NHẬP
                SwingUtilities.invokeLater(() -> {
                    LOGIN fp = new LOGIN();
                    fp.setVisible(true);
                });
            }
        });

       
        
        mainPanel.add(registerPanel);mainPanel.add(background);

    }
   
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Không thể cài đặt FlatLaf");
        }
        SwingUtilities.invokeLater(() -> {
            RegisterGUI frame = new RegisterGUI();
            frame.setVisible(true);
        });
    }
}
