package appgiaovan.ShipperGUI;

import appgiaovan.Controller.QLDonHangController;
import appgiaovan.Entity.DonHang;
import appgiaovan.GUI.Components.TableList;
import appgiaovan.GUI.Components.MenuBar;
import appgiaovan.GUI.Components.RoundedButton;
import appgiaovan.GUI.Components.TimeWeather;
import com.formdev.flatlaf.FlatLightLaf;
import static com.sun.tools.attach.VirtualMachine.list;
import javax.swing.*;
import java.awt.*;
import static java.nio.file.Files.list;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuanLyDonHang extends JPanel {

    public QuanLyDonHang() throws SQLException, ClassNotFoundException {
        /*setTitle("Quản Lý Đơn Hàng");
        setSize(1300, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);*/

        // Panel Menu
        //NVGHMenu menu = new NVGHMenu();
        //add(menu, BorderLayout.WEST);
        setLayout(new BorderLayout());

        // MAIN PANEL: dùng BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 1) Header (thời gian + thời tiết)
        mainPanel.add(new TimeWeather("Hồ Chí Minh 30°C"), BorderLayout.NORTH);

        // 2) Trung tâm gồm filter + bảng, xếp dọc
        JPanel centerPanel = new JPanel(new BorderLayout());

        // 2.1) Thanh lọc lên trên
        NVGHLoc filter = new NVGHLoc();
        filter.setPreferredSize(new Dimension(0, 60));  // để layout manager tự co giãn chiều ngang
        centerPanel.add(filter, BorderLayout.NORTH);

        // 2.2) Bảng danh sách xuống dưới
        // Chuẩn bị header
        String[] columns = {
            "", "Mã đơn hàng", "Tên người nhận", "Địa chỉ", "SĐT nhận",
            "Trạng thái", "Tiền COD", "Thời gian tạo", "SĐT gửi", "Tên người gửi", "Kho tiếp nhận"
        };

        // Chuyển List<DonHang> thành Object[][]
        QLDonHangController dsdh = new QLDonHangController();
        List<DonHang> ds = dsdh.HienThiDSDHChoNVGH();
        Object[][] data = new Object[ds.size()][columns.length];
        for (int i = 0; i < ds.size(); i++) {
            DonHang dh = ds.get(i);
            data[i][0] = false;                                    // checkbox
            data[i][1] = dh.getIdDonHang();
            data[i][2] = dh.getTenNguoiNhan();
            data[i][3] = dh.getDiaChiNhan();
            data[i][4] = dh.getSdtNguoiNhan();
            data[i][5] = dh.getTrangThai();
            data[i][6] = dh.getTienCOD();
            data[i][7] = dh.getThoiGianTao();
            data[i][8] = dh.getSdtNguoiGui();
            data[i][9] = dh.getTenNguoiGui();
            data[i][10] = dh.getIdKhoTiepNhan();
        }
        TableList listOrder = new TableList(columns, data);
        centerPanel.add(listOrder, BorderLayout.CENTER);

        // 3) Gắn centerPanel vào mainPanel
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Thêm mainPanel vào JFrame
        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (Exception ex) {
                System.err.println("Không thể cài đặt FlatLaf");
            }
            try {
                new QuanLyDonHang().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyDonHang.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuanLyDonHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
