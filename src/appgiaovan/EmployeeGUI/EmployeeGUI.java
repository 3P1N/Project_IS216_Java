
package appgiaovan.EmployeeGUI;

import appgiaovan.ConnectDB.ConnectionUtils;
import appgiaovan.Controller.TokenController;
import appgiaovan.CustomerGUI.CustomerGUI;
import appgiaovan.CustomerGUI.ThongTinCaNhanPanel;
import appgiaovan.DAO.NhanVienKhoDAO;
import appgiaovan.Entity.NhanVienKho;
import appgiaovan.Entity.TaiKhoan;
import appgiaovan.GUI.Components.ThongTinCaNhan;
import appgiaovan.GUI.LOGIN;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class EmployeeGUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private TaiKhoan taiKhoan;
    private NhanVienKho nhanVienKho;
    private NhanVienKhoDAO dao = new NhanVienKhoDAO();
    public EmployeeGUI(TaiKhoan tk, int idToken) throws SQLException, ClassNotFoundException, Exception {
        
        
        taiKhoan = tk;
        
        nhanVienKho = dao.layThongTinNhanVienKho( dao.layID_NhanVienKho(tk.getIdTaiKhoan()));
        
        setTitle("Giao diện chính");
        setSize(1400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        
        // Danh sách tên và icon menu
        
        // Tạo menu
        EmployeeSidebar sidebar = new EmployeeSidebar();
        add(sidebar, BorderLayout.WEST);

        // Panel trung tâm hiển thị nội dung
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Thêm các trang nội dung
        contentPanel.add(new ThongTinCaNhanPanel(taiKhoan),"Profile");
        contentPanel.add(new EmployeeMainPanel(),"Trang chủ");
        contentPanel.add(new BaoCaoKhoFrame(nhanVienKho), "Báo cáo");
        contentPanel.add(new QuanLyDonHangPanel(nhanVienKho),"Quản lý đơn hàng");

        contentPanel.add(new QuanLyGoiHang(nhanVienKho), "Quản lý gói hàng");

        add(contentPanel, BorderLayout.CENTER);

        // Khi chọn mục trong MenuBar thì đổi trang
        sidebar.addMenuClickListener((selectedName) -> {
            if (selectedName.equals("Đăng xuất")) {
                int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn đăng xuất không?",
                    "Xác nhận đăng xuất",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                    try {
                        try {
                            new TokenController().capNhatToken(idToken);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(EmployeeGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmployeeGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    SwingUtilities.invokeLater(() -> new LOGIN().setVisible(true));
                }
            } else {
                cardLayout.show(contentPanel, selectedName);
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Không thể cài đặt FlatLaf");
        }
        SwingUtilities.invokeLater(() -> {
            try {
                TaiKhoan tk = new TaiKhoan();
                tk.setIdTaiKhoan(64);
                new EmployeeGUI(tk,1).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EmployeeGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(EmployeeGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
