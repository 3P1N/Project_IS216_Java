/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appgiaovan.CustomerGUI;

import appgiaovan.Controller.QLDonHangController;
import appgiaovan.GUI.Components.RoundedPanel;
import appgiaovan.GUI.Components.MenuBar;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import appgiaovan.CustomerGUI.ThanhTimKiemDH;
import appgiaovan.EmployeeGUI.QuanLyDonHangPanel;
import appgiaovan.Entity.DonHang;
import appgiaovan.GUI.Components.TableList;
import appgiaovan.GUI.Components.TimeWeather;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TraCuuDonHangPanel extends JPanel {
    private final ThanhTimKiemDH topPanel = new ThanhTimKiemDH();
    private final QLDonHangController controller = new QLDonHangController();
    private final TableList listOrder;
    public TraCuuDonHangPanel() throws SQLException, ClassNotFoundException{
        
        setLayout(new BorderLayout());
        
        //Khu vuc trung tâm
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Thêm vào JFrame
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        
        // Panel danh sách
        String[] columns = DonHang.getTableHeaders();
        Object[][] data = new Object[0][columns.length];
        listOrder = new TableList(columns,data);
        mainPanel.add(listOrder, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
        // gán sự kiện tìm kiếm đơn hàng
        topPanel.getfilterButton().addActionListener(e -> {
            try {
                DonHang dh = topPanel.getDonHang();

                HienThiDanhSach(dh);
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuanLyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Hiển thị danh sách ngay khi mở panel
        HienThiDanhSach();
        listOrder.getTable().addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2 && listOrder.getTable().getSelectedRow() != -1) {
                int selectedRow = listOrder.getTable().getSelectedRow();

                // Lấy dữ liệu từ dòng được chọn
                int rowData = 0;
                
                rowData = (int) listOrder.getTable().getValueAt(selectedRow, 1);
                // Chuyển dữ liệu sang đối tượng DonHang (giả sử bạn có hàm khởi tạo phù hợp)
                System.out.println(rowData);
                

                try {
                    // Mở JFrame chi tiết
                    new ThongTinDonHang(rowData).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TraCuuDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TraCuuDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });

        
    }
  public final void HienThiDanhSach() throws SQLException, ClassNotFoundException {
        java.util.List<DonHang> dsDonHang = controller.LayDSDonHang();
        String[] columns = DonHang.getTableHeaders();
        Object[][] data = new Object[dsDonHang.size()][columns.length];

        for (int i = 0; i < dsDonHang.size(); i++) {
            data[i] = dsDonHang.get(i).toTableRow();
        }

        listOrder.setTableData(data);
    }

    public final void HienThiDanhSach(DonHang dh) throws SQLException, ClassNotFoundException {
        java.util.List<DonHang> dsDonHang = controller.LayDSDonHang(dh);
        String[] columns = DonHang.getTableHeaders();
        Object[][] data = new Object[dsDonHang.size()][columns.length];

        for (int i = 0; i < dsDonHang.size(); i++) {
            data[i] = dsDonHang.get(i).toTableRow();
        }

        listOrder.setTableData(data);
    } 
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Employee Main Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            try {
                frame.add(new TraCuuDonHangPanel(), BorderLayout.CENTER);
            } catch (SQLException ex) {
                Logger.getLogger(TraCuuDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TraCuuDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.setVisible(true);
        });
    }
    
}
