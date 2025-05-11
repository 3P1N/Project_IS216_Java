package appgiaovan.EmployeeGUI;


import appgiaovan.GUI.Components.TableList;
import appgiaovan.GUI.Components.MenuBar;
import appgiaovan.GUI.Components.FilterPanel;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class QuanLyDonHang extends JFrame {

    public QuanLyDonHang() {
        setTitle("Quản Lý Đơn Hàng");
        setSize(1300, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {    
        //Panel Menu
        MenuBar menubar = new MenuBar();
        add(menubar, BorderLayout.WEST);
        
        //main
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout()); 
        
        //thanh filter
        FilterPanel topPanel = new FilterPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH); 
      
       // Panel danh sách
       TableList listOrder = new TableList("", "ID", "Khách hàng", "Sản phẩm", "ĐVT", "Giá", "SL");
       mainPanel.add(listOrder, BorderLayout.CENTER);
       add(mainPanel, BorderLayout.CENTER);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuanLyDonHang().setVisible(true));
    }
}
